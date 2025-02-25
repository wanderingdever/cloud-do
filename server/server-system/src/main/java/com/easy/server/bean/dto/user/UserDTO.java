package com.easy.server.bean.dto.user;

import com.easy.common.core.enums.AccountStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * </p>
 *
 * @author Matt
 */
@Data
@Schema(title = "用户信息-入参")
public class UserDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "账号状态")
    private AccountStatus status;

}