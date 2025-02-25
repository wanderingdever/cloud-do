package com.easy.api.vo;

import com.easy.common.core.enums.AccountClient;
import com.easy.common.core.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * </p>
 *
 * @author Matt
 */
@Getter
@Setter
@Schema(description = "用户账号")
public class UserAccountVO implements Serializable {

    @Schema(description = "账号")
    private String username;

    @Schema(description = "密码")
    private String password;

    @Schema(description = "邮箱")
    private String email;

    @Schema(description = "手机号")
    private String phone;

    @Schema(description = "所属客户端")
    private AccountClient client;

    @Schema(description = "账号状态")
    private AccountStatus status;

    @Schema(description = "创建时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String createTime;
}