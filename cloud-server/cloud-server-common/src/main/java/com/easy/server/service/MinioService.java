package com.easy.server.service;


import com.easy.common.core.constant.Constants;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.util.file.FileUtils;
import com.easy.server.bean.entity.FileRecord;
import com.easy.server.bean.vo.FileVO;
import com.easy.server.config.MinIoClientConfig;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.Result;
import io.minio.messages.DeleteError;
import io.minio.messages.DeleteObject;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

/**
 * minio文件
 * </p>
 *
 * @author Matt
 */
@Service
@AllArgsConstructor
@ConditionalOnProperty(name = "file.storage", havingValue = "minio")
public class MinioService implements FileService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MinioService.class);

    private final MinIoClientConfig minIoClientConfig;


    @Override
    public FileVO upload(MultipartFile file) throws IOException {
        // 获取文件原名称
        String fileName = file.getOriginalFilename();
        // 上传文件
        String relativePath = minioUp(file.getInputStream(), fileName);
        // 保存记录
        return new FileVO(fileName, relativePath, minIoClientConfig.getEndpoint(), file.getSize());
    }

    @Override
    public FileVO upload(InputStream inputStream, String fileName) throws IOException {
        // 上传文件
        String relativePath = minioUp(inputStream, fileName);
        // 保存记录
        return new FileVO(fileName, relativePath, minIoClientConfig.getEndpoint(), FileUtils.getInputStreamSize(inputStream));
    }

    @Override
    public List<FileVO> uploadBatch(MultipartFile[] files) {
        return List.of();
    }

    @Override
    public ResponseEntity<Resource> download(List<FileRecord> fileList) throws IOException {
        return null;
    }

    @Override
    public String deleteFile(List<FileRecord> fileList) {
        List<String> list = fileList.stream().map(FileRecord::getFile).toList();
        batchDeleteFiles(list);
        return Constants.SUCCESS_STR;
    }

    private void batchDeleteFiles(List<String> fileNames) {
        List<DeleteObject> dos = fileNames.stream().map(DeleteObject::new).collect(Collectors.toList());
        Iterable<Result<DeleteError>> results = minioClient().removeObjects(RemoveObjectsArgs.builder().bucket(minIoClientConfig.getBucketName()).objects(dos).build());
        for (Result<DeleteError> result : results) {
            DeleteError error = null;
            try {
                error = result.get();
            } catch (Exception e) {
                throw new CustomizeException("Minio Delete Error");
            }
            LOGGER.error("Minio错误: 删除文件 {} 失败 {}", error.objectName(), error.message());
        }
    }


    /**
     * minio上传
     *
     * @param fileName    文件名字
     * @param inputStream 文件流
     * @return 文件名
     */
    private String minioUp(InputStream inputStream, String fileName) {
        fileName = FileUtils.getTimePathFileName(fileName);
        try {
            minioClient().putObject(PutObjectArgs.builder()
                    .bucket(minIoClientConfig.getBucketName())
                    .object(fileName)
                    .stream(inputStream, inputStream.available(), -1)
                    .contentType(FileUtils.getContentType(fileName))
                    .build()
            );
        } catch (Exception e) {
            LOGGER.error("Minio错误: 上传文件 {} 失败：{}", fileName, e.getMessage());
        }
        return fileName;
    }

    /**
     * 注入minio 客户端
     */
    private MinioClient minioClient() {
        return MinioClient.builder()
                .endpoint(minIoClientConfig.getEndpoint())
                .credentials(minIoClientConfig.getAccessKey(), minIoClientConfig.getSecretKey())
                .build();
    }
}