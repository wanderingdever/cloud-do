package com.easy.auth.bean;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 密码登录
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Data
@Schema(title = "密码登录-入参")
public class PwdLogin extends LoginTypeAndClient {

    @Schema(title = "用户名/邮箱/手机号")
    @NotBlank(message = "请输入正确的用户名/邮箱/手机号")
    private String username;

    @Schema(title = "密码")
    @NotBlank(message = "请输入密码")
    private String password;

}