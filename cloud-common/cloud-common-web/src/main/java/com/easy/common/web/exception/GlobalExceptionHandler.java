package com.easy.common.web.exception;

import com.easy.common.core.base.R;
import com.easy.common.core.enums.REnum;
import com.easy.common.core.exception.CustomizeException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Set;

/**
 * 全局异常处理
 * </p>
 *
 * @author Matt
 */
@Slf4j
@RestControllerAdvice(basePackages = "com.easy")
public class GlobalExceptionHandler {

    /**
     * 全局异常拦截
     */
    @ExceptionHandler(value = Exception.class)
    public R<String> handleCustom(Exception e) {
        log.error("全局异常拦截-> e={}", e.getMessage());
        e.printStackTrace();
        return R.fail(REnum.RUNTIME_EXCEPTION);
    }


    /**
     * 自定义异常
     */
    @ExceptionHandler(value = CustomizeException.class)
    public R<String> handleCustom(CustomizeException e) {
        log.error("自定义异常拦截-> e={}", e.getMessage());
        e.printStackTrace();
        return e.getResult();
    }

    /**
     * 参数校验异常拦截
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R<String> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {

        // 从异常对象中拿到 ObjectError 对象
        ObjectError objectError = e.getBindingResult().getAllErrors().get(0);
        log.error("参数校验异常拦截 --> e={}", objectError.getDefaultMessage());
        // 然后提取错误提示信息进行返回
        return R.fail(objectError.getDefaultMessage());
    }

    /**
     * 参数异常拦截 （方法上的）
     **/
    @ExceptionHandler(ConstraintViolationException.class)
    public R<String> constraintViolationException(ConstraintViolationException e, HttpServletRequest request) {
        StringBuilder builder = new StringBuilder();
        Set<ConstraintViolation<?>> constraintViolations = e.getConstraintViolations();
        for (ConstraintViolation<?> constraintViolation : constraintViolations) {
            String message = constraintViolation.getMessage();
            builder.append(message);
        }
        log.error("接口:{},error:{}", request.getServletPath(), e.toString());
        e.printStackTrace();
        return R.fail(builder.toString());
    }
}