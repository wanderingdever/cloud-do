package com.easy.common.core.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DelEnum implements EnumInterface<Integer> {

    /**
     * 启用
     */
    ENABLE(0, "启用"),
    DELETE(1, "删除"),
    ;

    @EnumValue
    private final Integer code;
    private final String introduction;
}