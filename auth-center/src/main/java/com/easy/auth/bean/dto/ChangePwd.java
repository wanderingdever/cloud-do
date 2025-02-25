package com.easy.auth.bean.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * </p>
 *
 * @author Matt
 */
@Data
@Schema(title = "修改密码")
public class ChangePwd {

    @Schema(description = "旧密码")
    @NotBlank(message = "旧密码不能为空")
    private String oldPwd;

    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空")
    @Pattern(regexp = "^(?=.*[a-zA-Z])(?=.*\\\\d)(?=.*[!@#])[A-Za-z\\\\d!@#]{8,20}$", message = "密码格式不正确")
    private String newPwd;
}