package com.easy.auth.bean.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(title = "用户")
public class UserAccountDTO {

    @Schema(title = "用户名")
    @NotBlank(message = "用户名不能为空")
    private String username;

}