package com.easy.api.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.io.Serializable;

/**
 * 用户重置密码信息
 * </p>
 *
 * @author Matt
 */
@Data
@Schema(title = "用户重置密码信息-入参")
public class UserPwdVO implements Serializable {


    @Schema(description = "用户ID", requiredMode = Schema.RequiredMode.REQUIRED)
    @NotBlank(message = "用户ID")
    private String id;

    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空")
    private String password;

    @Schema(description = "新密码")
    @NotBlank(message = "请确认新密码")
    private String confirmPassword;
}