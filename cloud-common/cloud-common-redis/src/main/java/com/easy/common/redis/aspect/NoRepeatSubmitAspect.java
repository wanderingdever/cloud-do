package com.easy.common.redis.aspect;

import com.alibaba.fastjson2.JSONObject;
import com.easy.common.core.constant.Constants;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.redis.annotation.RepeatSubmit;
import com.easy.common.redis.utils.RedisUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.dromara.hutool.crypto.SecureUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.lang.reflect.Method;
import java.time.Duration;
import java.util.Objects;

import static com.easy.common.core.enums.REnum.DUPLICATE_SUBMIT;


/**
 * 防止重复提交切面
 * </p>
 *
 * @author Matt
 */
@Component
@Aspect
public class NoRepeatSubmitAspect {

    @Value("${sa-token.token-name:Easy-Token}")
    private String tokenName;

    /**
     * 定义切点
     */
    @Pointcut("@annotation(com.easy.common.redis.annotation.RepeatSubmit)")
    public void preventDuplication() {
    }

    @Around("preventDuplication()")
    public Object around(ProceedingJoinPoint joinPoint) {
        // 获取请求信息
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes();
        HttpServletRequest request = null;
        if (attributes != null) {
            request = attributes.getRequest();
        }
        // 获取执行方法
        Method method = ((MethodSignature) joinPoint.getSignature()).getMethod();
        // 获取防重复提交注解
        RepeatSubmit annotation = method.getAnnotation(RepeatSubmit.class);
        // 获取token以及方法标记，生成redisKey和redisValue
        String token = null;
        if (request != null) {
            token = request.getHeader(tokenName);
        }

        String url = request.getRequestURI();
        // 通过前缀 + url + token + 函数参数签名 来生成redis上的 key
        String redisKey = Constants.REPEAT_SUBMIT_KEY
                .concat(url)
                .concat(token)
                .concat(getMethodSign(method, joinPoint.getArgs()));
        // 这个值只是为了标记，不重要
        String redisValue = redisKey.concat("submit duplication");
        if (!RedisUtils.hasKey(redisKey)) {
            // 设置防重复操作限时标记（前置通知）
            RedisUtils.setCacheObject(redisKey, redisValue, Duration.ofMillis(annotation.interval()));
            try {
                // 正常执行方法并返回
                // ProceedingJoinPoint类型参数可以决定是否执行目标方法，
                // 且环绕通知必须要有返回值，返回值即为目标方法的返回值
                return joinPoint.proceed();
            } catch (Throwable throwable) {
                // 确保方法执行异常实时释放限时标记(异常后置通知)
                RedisUtils.deleteObject(redisKey);
                throw new RuntimeException(throwable);
            }
        } else {
            // 重复提交了抛出异常，如果是在项目中，根据具体情况处理。
            throw new CustomizeException(DUPLICATE_SUBMIT);
        }


    }

    /**
     * 生成方法标记：采用数字签名算法SHA1对方法签名字符串加签
     *
     * @param method 方法
     * @param args   参数
     * @return String
     */
    private String getMethodSign(Method method, Object... args) {
        StringBuilder sb = new StringBuilder(method.toString());
        for (Object arg : args) {
            sb.append(toString(arg));
        }
        return SecureUtil.sha1(sb.toString());
    }

    private String toString(Object arg) {
        if (Objects.isNull(arg)) {
            return "null";
        }
        if (arg instanceof Number) {
            return arg.toString();
        }
        return JSONObject.toJSONString(arg);
    }
}