package com.easy.server.service;

import com.easy.common.core.exception.CustomizeException;
import com.easy.common.util.file.FileUtils;
import com.easy.common.util.lang.DateUtils;
import com.easy.common.util.lang.StringUtils;
import com.easy.server.bean.entity.FileRecord;
import com.easy.server.bean.vo.FileVO;
import com.easy.server.config.TencentCosConfig;
import com.qcloud.cos.COS;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.COSObjectInputStream;
import com.qcloud.cos.model.GetObjectRequest;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import com.qcloud.cos.transfer.TransferManager;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.zip.ZipOutputStream;


/**
 * 腾讯云cos
 * </p>
 *
 * @author Matt
 */
@Service
@AllArgsConstructor
@ConditionalOnProperty(name = "file.storage", havingValue = "tencent")
public class TencentCosService implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TencentCosService.class);

    private final TencentCosConfig cosConfig;


    @Override
    public FileVO upload(MultipartFile file) throws IOException {
        String originalFilename = file.getOriginalFilename();
        return upload(file.getInputStream(), originalFilename);
    }

    @Override
    public FileVO upload(InputStream inputStream, String fileName) throws IOException {
        long size = FileUtils.getInputStreamSize(inputStream);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(size);
        String folderFileName = FileUtils.getTimePathFileName(fileName);
        PutObjectResult pubResult = createTransferManager().getCOSClient().putObject(cosConfig.getBucketName(), folderFileName, inputStream, metadata);
        if (pubResult == null || StringUtils.isBlank(pubResult.getETag())) {
            LOGGER.error("上传到cos错误，put对象结果为null或etag为空,文件名: {}", folderFileName);
            throw new CustomizeException("文件上传失败");
        }
        return new FileVO(fileName, folderFileName, cosConfig.getUrl(), size);
    }

    public List<FileVO> uploadBatch(MultipartFile[] files) {
        return null;
    }

    @Override
    public ResponseEntity<Resource> download(List<FileRecord> fileList) throws IOException {
        String downloadFileName;
        TransferManager transferManager = createTransferManager();
        COS cosClient = transferManager.getCOSClient();
        // 创建一个字节输出流来捕获压缩包的字节
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        // 如果只有一个文件,则不压缩成zip,直接下载
        if (fileList.size() == 1) {
            FileRecord fileRecord = fileList.getFirst();
            downloadFileName = fileRecord.getFileName();
            GetObjectRequest getObjectRequest = new GetObjectRequest(cosConfig.getBucketName(), fileRecord.getFile());
            COSObjectInputStream objectContent = cosClient.getObject(getObjectRequest).getObjectContent();
            return FileUtils.getResourceResponseEntity(downloadFileName, objectContent);
        } else {
            downloadFileName = DateUtils.datePath() + "/" + DateUtils.timeNum() + ".zip";
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
                for (FileRecord file : fileList) {
                    // 下载文件
                    GetObjectRequest getObjectRequest = new GetObjectRequest(cosConfig.getBucketName(), file.getFile());
                    COSObjectInputStream objectContent = cosClient.getObject(getObjectRequest).getObjectContent();
                    // 将文件添加到压缩包
                    FileUtils.zipFile(zipOutputStream, file.getFileName(), objectContent);
                }
            } catch (Exception e) {
                throw new CustomizeException("文件下载失败");
            } finally {
                // 关闭OSSClient
                transferManager.shutdownNow();
            }
        }
        return FileUtils.getResourceResponseEntity(downloadFileName, byteArrayOutputStream);
    }

    @Override
    public String deleteFile(List<FileRecord> fileList) {
        COS cosClient = createTransferManager().getCOSClient();
        for (FileRecord file : fileList) {
            cosClient.deleteObject(cosConfig.getBucketName(), file.getFile());
        }
        createTransferManager().shutdownNow();
        return null;
    }


    /**
     * 创建 TransferManager 实例，这个实例用来后续调用高级接口
     *
     * @return TransferManager
     */
    TransferManager createTransferManager() {
        // 创建一个 COSClient 实例，这是访问 COS 服务的基础实例。
        COSClient cosClient = createCOSClient();
        // 自定义线程池大小，建议在客户端与 COS 网络充足（例如使用腾讯云的 CVM，同地域上传 COS）的情况下，设置成16或32即可，可较充分的利用网络资源
        // 对于使用公网传输且网络带宽质量不高的情况，建议减小该值，避免因网速过慢，造成请求超时。
        ExecutorService threadPool = Executors.newFixedThreadPool(16);
        // 传入一个 threadpool, 若不传入线程池，默认 TransferManager 中会生成一个单线程的线程池。
        return new TransferManager(cosClient, threadPool);
    }

    /**
     * 创建 COSClient 实例，这个实例用来后续调用请求
     *
     * @return COSClient
     */
    COSClient createCOSClient() {
        // 用户的 SecretId，建议使用子账号密钥，授权遵循最小权限指引，降低使用风险。
        String secretId = cosConfig.getSecretId();
        // 用户的 SecretKey，建议使用子账号密钥，授权遵循最小权限指引，降低使用风险。
        String secretKey = cosConfig.getSecretKey();
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // ClientConfig 中包含了后续请求 COS 的客户端设置：
        ClientConfig clientConfig = new ClientConfig();
        // 设置 bucket 的地域
        clientConfig.setRegion(new Region(cosConfig.getRegion()));
        // 设置请求协议, http 或者 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 以下的设置，是可选的：
        // 设置 socket 读取超时，默认 30s
        clientConfig.setSocketTimeout(30 * 1000);
        // 设置建立连接超时，默认 30s
        clientConfig.setConnectionTimeout(30 * 1000);
        // 生成 cos 客户端。
        return new COSClient(cred, clientConfig);
    }

}