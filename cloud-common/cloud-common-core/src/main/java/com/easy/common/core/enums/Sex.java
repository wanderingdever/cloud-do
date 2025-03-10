package com.easy.common.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 性别枚举
 * </p>
 *
 * @author Matt
 */
@Getter
@AllArgsConstructor
public enum Sex {

    /**
     * 性别
     */
    WOMAN(0, "女"),
    MAN(1, "男"),
    UNKNOWN(2, "未知");

    @EnumValue
    private final Integer code;
    private final String name;

}