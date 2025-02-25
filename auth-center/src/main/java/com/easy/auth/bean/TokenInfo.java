package com.easy.auth.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 登录信息
 * </p>
 *
 * @author Matt
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Schema(title = "登录信息")
public class TokenInfo implements Serializable {

    @Schema(name = "token")
    private String accessToken;

    @Schema(name = "过期时间")
    private Long timeOut;

    @Schema(name = "设备信息")
    private String device;
}