package com.easy.auth.bean.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
@Schema(title = "用户名密码-入参")
public class UserAndPwd {

    @Schema(title = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(title = "密码")
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$", message = "密码必须包含数字和字母，且长度在6-16位之间")
    private String password;

}
