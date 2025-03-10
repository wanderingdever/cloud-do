package com.easy.server.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 阿里云OSS配置
 * </p>
 *
 * @author Matt
 */
@Getter
@Component
@ConditionalOnProperty(name = "file.storage", havingValue = "ali")
public class AliOssConfig {


    /**
     * 服务地址
     */
    @Value("${file.ali.endpoint}")
    private String endpoint;
    /**
     * 用户名
     */
    @Value("${file.ali.access-key-id}")
    private String accessKey;
    /**
     * 密码
     */
    @Value("${file.ali.access-key-secret}")
    private String secretKey;

    /**
     * 存储桶名称
     */
    @Value("${file.ali.bucket-name}")
    private String bucketName;

}