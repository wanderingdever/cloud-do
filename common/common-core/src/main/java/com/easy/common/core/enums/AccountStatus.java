package com.easy.common.core.enums;


import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 账号状态枚举
 * </p>
 *
 * @author Matt
 */
@Getter
@AllArgsConstructor
public enum AccountStatus {
    /**
     * 账号状态
     */
    INACTIVATED("INACTIVATED", "账号未激活"),
    NORMAL("NORMAL", "正常"),
    STOP("STOP", "账号已被停用"),
    AUTHORIZE_EXPIRED("AUTHORIZE_EXPIRED", "账号授权过期"),
    ;
    @EnumValue
    private final String value;

    private final String desc;

}