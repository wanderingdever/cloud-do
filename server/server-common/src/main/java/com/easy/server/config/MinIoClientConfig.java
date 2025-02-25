package com.easy.server.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * minio配置类
 * </p>
 *
 * @author Matt
 */
@Getter
@Component
@ConditionalOnProperty(name = "file.storage", havingValue = "minio")
public class MinIoClientConfig {

    /**
     * 服务地址
     */
    @Value("${file.minio.endpoint}")
    private String endpoint;
    /**
     * 用户名
     */
    @Value("${file.minio.access-key}")
    private String accessKey;
    /**
     * 密码
     */
    @Value("${file.minio.secret-key}")
    private String secretKey;

    /**
     * 存储桶名称
     */
    @Value("${file.minio.bucket-name}")
    private String bucketName;

    /**
     * 预览到期时间（分钟）
     */
    @Value("${file.minio.preview-expiry}")
    private Integer previewExpiry;

}