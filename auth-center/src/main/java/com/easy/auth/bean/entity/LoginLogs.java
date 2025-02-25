package com.easy.auth.bean.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.common.core.base.BaseIdEntity;
import com.easy.common.core.enums.LoginType;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 登录记录
 * </p>
 *
 * @author Matt
 * @since 2025-01-20
 */
@Getter
@Setter
@TableName("login_logs")
@Schema(name = "LoginLogs", description = "登录记录")
public class LoginLogs extends BaseIdEntity {

    @Schema(description = "用户ID")
    @TableField("user_id")
    private String userId;

    @Schema(description = "登录方式")
    @TableField("login_type")
    private LoginType loginType;

    @Schema(description = "登录IP地址")
    @TableField("ip")
    private String ip;

    @Schema(description = "登录地点")
    @TableField("ip_location")
    private String ipLocation;

    @Schema(description = "浏览器类型")
    @TableField("browser")
    private String browser;

    @Schema(description = "访问时间")
    @TableField("login_time")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private String loginTime;
}