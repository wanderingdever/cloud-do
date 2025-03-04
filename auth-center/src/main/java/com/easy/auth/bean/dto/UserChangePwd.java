package com.easy.auth.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * 用户重置密码信息
 * </p>
 *
 * @author Matt
 */
@Data
@Schema(title = "用户重置密码信息-入参")
public class UserChangePwd {

    @Schema(description = "原密码", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "请输入原密码")
    private String oldPassword;

    @Schema(description = "新密码")
    @NotBlank(message = "请输入新密码")
    private String password;

    @Schema(description = "新密码")
    @NotBlank(message = "请确认新密码")
    private String confirmPassword;
}