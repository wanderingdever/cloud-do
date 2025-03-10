package com.easy.auth.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseIdEntity;
import com.easy.common.core.enums.AccountClient;
import com.easy.common.core.enums.Sex;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户基本信息
 * </p>
 *
 * @author Matt
 * @since 2025-01-20
 */
@Getter
@Setter
@TableName("user_info")
@Schema(name = "UserInfo", description = "用户基本信息")
public class UserInfo extends BaseIdEntity {

    @Schema(description = "用户信息ID")
    @TableField("user_id")
    private String userId;

    @Schema(description = "归属客户端")
    @TableField("client")
    private AccountClient client;

    @Schema(description = "账号昵称")
    @TableField("nickname")
    private String nickname;

    @Schema(description = "头像")
    @TableField("avatar")
    private String avatar;

    @Schema(description = "性别")
    @TableField("sex")
    private Sex sex;
}