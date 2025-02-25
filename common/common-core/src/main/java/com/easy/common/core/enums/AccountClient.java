package com.easy.common.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 账号类型枚举
 * </p>
 *
 * @author Matt
 */
@Getter
@AllArgsConstructor
public enum AccountClient {

    SYSTEM("SYSTEM", "管理端"),
    USER("USER", "用户端"),
    ;
    @EnumValue
    private final String value;

    private final String desc;

    public static AccountClient getByValue(String value) {
        for (AccountClient accountClient : values()) {
            if (accountClient.getValue().equals(value)) {
                return accountClient;
            }
        }
        return null;
    }
}