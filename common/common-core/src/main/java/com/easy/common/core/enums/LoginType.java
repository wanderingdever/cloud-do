package com.easy.common.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 登录方式
 * </p>
 *
 * @author Matt
 */
@Getter
@AllArgsConstructor
public enum LoginType {
    /**
     * 密码登录
     */
    PWD("PWD", "密码登录"),
    /**
     * 短信验证码登录
     */
    SMS("SMS", "短信验证码"),
    /**
     * 其他方式
     */
    OTHER("OTHER", "其他方式"),
    ;
    @EnumValue
    private final String value;

    private final String desc;

    public static LoginType getByValue(String value) {
        for (LoginType loginType : values()) {
            if (loginType.getValue().equals(value)) {
                return loginType;
            }
        }
        return null;
    }
}