
package com.easy.server.service;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.OSSObject;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.util.file.FileUtils;
import com.easy.common.util.lang.DateUtils;
import com.easy.server.bean.entity.FileRecord;
import com.easy.server.bean.vo.FileVO;
import com.easy.server.config.AliOssConfig;
import lombok.SneakyThrows;
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
import java.util.zip.ZipOutputStream;

/**
 * 阿里云oss
 * </p>
 *
 * @author Matt
 */
@Service
@ConditionalOnProperty(name = "file.storage", havingValue = "ali")
public class AliOssService implements FileService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AliOssService.class);

    private final AliOssConfig aliOssConfig;

    public AliOssService(AliOssConfig aliOssConfig) {
        this.aliOssConfig = aliOssConfig;
    }

    @SneakyThrows
    @Override
    public FileVO upload(MultipartFile file) {
        // 创建文件输入流
        InputStream inputStream = file.getInputStream();
        String fileName = file.getOriginalFilename();
        String upload = uploadToOss(inputStream, fileName);
        // 保存记录
        return new FileVO(fileName, upload, aliOssConfig.getEndpoint(), file.getSize());
    }

    @Override
    public FileVO upload(InputStream inputStream, String fileName) throws IOException {
        String upload = uploadToOss(inputStream, fileName);
        // 保存记录
        return new FileVO(fileName, upload, aliOssConfig.getEndpoint(), FileUtils.getInputStreamSize(inputStream));
    }


    @Override
    public List<FileVO> uploadBatch(MultipartFile[] files) {
        return List.of();
    }

    @Override
    public ResponseEntity<Resource> download(List<FileRecord> fileList) throws IOException {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(aliOssConfig.getEndpoint(), aliOssConfig.getAccessKey(), aliOssConfig.getSecretKey());
        // 创建一个字节输出流来捕获压缩包的字节
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        String downloadFileName;
        if (fileList.size() == 1) {
            FileRecord fileRecord = fileList.getFirst();
            // 直接下载到文件
            String filePath = fileRecord.getFile();
            String fileName = fileRecord.getFileName();
            OSSObject object = ossClient.getObject(new GetObjectRequest(aliOssConfig.getBucketName(), filePath));
            InputStream objectContent = object.getObjectContent();
            return FileUtils.getResourceResponseEntity(fileName, objectContent);
        } else {
            downloadFileName = DateUtils.datePath() + "/" + DateUtils.timeNum() + ".zip";
            try (ZipOutputStream zipOutputStream = new ZipOutputStream(byteArrayOutputStream)) {
                for (FileRecord file : fileList) {
                    // 下载文件
                    OSSObject ossObject = ossClient.getObject(aliOssConfig.getBucketName(), file.getFile());
                    InputStream inputStream = ossObject.getObjectContent();
                    // 将文件添加到压缩包
                    FileUtils.zipFile(zipOutputStream, file.getFileName(), inputStream);
                }
            } catch (Exception e) {
                throw new CustomizeException("文件下载失败");
            } finally {
                // 关闭OSSClient
                ossClient.shutdown();
            }
        }
        return FileUtils.getResourceResponseEntity(downloadFileName, byteArrayOutputStream);
    }


    @Override
    public String deleteFile(List<FileRecord> fileList) {
        // 创建OSSClient实例
        OSS ossClient = new OSSClientBuilder().build(aliOssConfig.getEndpoint(), aliOssConfig.getAccessKey(), aliOssConfig.getSecretKey());
        fileList.forEach(fileRecord -> {
            // 删除文件
            ossClient.deleteObject(aliOssConfig.getBucketName(), fileRecord.getFile());
        });
        ossClient.shutdown();
        return "";

    }

    /**
     * 上传文件到阿里云OSS
     *
     * @param inputStream 文件流
     * @param fileName    文件名
     * @return 文件路径
     */
    private String uploadToOss(InputStream inputStream, String fileName) {
        OSS ossClient = null;
        fileName = DateUtils.datePath() + "/" + DateUtils.timeNum() + "_" + fileName;
        try {
            // 创建OSSClient实例
            ossClient = new OSSClientBuilder().build(aliOssConfig.getEndpoint(), aliOssConfig.getAccessKey(), aliOssConfig.getSecretKey());
            // 执行文件上传
            ossClient.putObject(aliOssConfig.getBucketName(), fileName, inputStream);
            return fileName;
        } catch (Exception e) {
            LOGGER.error("阿里云OSS文件上传失败：{}", e.getMessage());
            throw new CustomizeException("文件上传失败");
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
    }
}