package com.easy.auth.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseIdEntity;
import com.easy.common.core.enums.AccountClient;
import com.easy.common.core.enums.AccountStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 账号信息
 * </p>
 *
 * @author Matt
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("user_account")
@Schema(name = "UserAccount", description = "账号信息")
public class UserAccount extends BaseIdEntity {

    @Schema(description = "账号")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "手机号")
    @TableField("phone")
    private String phone;

    @Schema(description = "所属客户端")
    @TableField("client")
    private AccountClient client;

    @Schema(description = "账号状态")
    @TableField("status")
    private AccountStatus status;
}