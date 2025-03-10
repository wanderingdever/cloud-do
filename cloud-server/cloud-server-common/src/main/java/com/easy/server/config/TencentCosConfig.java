package com.easy.server.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 腾讯云cos配置
 * </p>
 *
 * @author Matt
 */
@Getter
@Component
@ConditionalOnProperty(name = "file.storage", havingValue = "tencent")
public class TencentCosConfig {

    @Value("${file.tencent.cos.url}")
    private String url;

    @Value("${file.tencent.cos.secret-id}")
    private String secretId;

    @Value("${file.tencent.cos.secret-key}")
    private String secretKey;

    @Value("${file.tencent.cos.region}")
    private String region;

    @Value("${file.tencent.cos.bucket-name}")
    private String bucketName;

}