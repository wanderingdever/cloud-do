package com.easy.common.web.annotation;


import com.easy.common.core.base.R;

import java.lang.annotation.*;

/**
 * 用于将 controller 返回值用 {@link R R} 包裹
 * </p>
 *
 * @author Matt
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Wrap {

    /**
     * 是否禁用
     */
    boolean disabled() default false;
}