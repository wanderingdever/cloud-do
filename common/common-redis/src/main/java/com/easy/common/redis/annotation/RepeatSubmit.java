package com.easy.common.redis.annotation;

import java.lang.annotation.*;

/**
 * 防止重复提交注解
 * </p>
 *
 * @author Matt
 */
@Inherited
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RepeatSubmit {

    /**
     * 间隔时间(ms)，小于此时间视为重复提交
     *
     * @return interval
     */
    int interval() default 3000;

    /**
     * 提示信息
     *
     * @return message
     */
    String message() default "不允许重复提交，请稍后再试";
}