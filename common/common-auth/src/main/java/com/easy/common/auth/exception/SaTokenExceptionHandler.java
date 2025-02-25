package com.easy.common.auth.exception;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.exception.NotPermissionException;
import com.easy.common.core.base.R;
import com.easy.common.core.enums.REnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * satoken异常处理
 * </p>
 *
 * @author Matt
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.easy")
public class SaTokenExceptionHandler {

    /**
     * 登录异常拦截
     */
    @ExceptionHandler(value = NotLoginException.class)
    public R<String> handleNotLoginException(NotLoginException e) {
        log.error("satoken异常拦截-> e={}", e.getMessage());
        return R.fail(e.getCode(), e.getMessage());
    }

    @ExceptionHandler(value = NotPermissionException.class)
    public R<String> handleException(NotPermissionException e) {
        log.error("satoken异常拦截-> e={}", e.getMessage());
        return R.fail(REnum.UNAUTHORIZED);
    }

}