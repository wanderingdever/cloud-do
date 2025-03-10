package com.easy.common.core.enums;

import com.easy.common.core.constant.HttpCodes;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 响应结果枚举
 * </p>
 *
 * @author Matt
 */
@Getter
@AllArgsConstructor
public enum REnum implements EnumInterface<Integer> {

    /**
     * 成功
     */
    SUCCESS(HttpCodes.SUCCESS, "SUCCESS", "成功"),
    /**
     * 运行时异常
     */
    RUNTIME_EXCEPTION(HttpCodes.ERROR, "ERROR", "错误"),

    /**
     * 认证失败
     */
    AUTHENTICATION_FAILURE(HttpCodes.ERROR, "AUTHENTICATION FAILED", "授权失败"),
    /**
     * 未授权
     */
    UNAUTHORIZED(HttpCodes.UNAUTHORIZED, "UNAUTHORIZED ACCESS", "未经授权的访问"),
    /**
     * 未登录
     */
    NOT_LOGIN(HttpCodes.UNAUTHORIZED, "NOT LOGIN", "未登录"),
    /**
     * 客户端信息错误
     */
    BAD_CREDENTIAL(HttpCodes.BAD_REQUEST, "CLIENT CREDENTIAL ERROR", "客户端信息错误"),
    /**
     * 不支持的授权类型
     */
    UNSUPPORTED_GRANT_TYPE(HttpCodes.BAD_REQUEST, "UNSUPPORTED AUTHORIZATION TYPE", "不支持的授权类型"),
    /**
     * 禁止访问
     */
    FORBIDDEN(HttpCodes.FORBIDDEN, "FORBIDDEN", "禁止访问"),
    /**
     * 未知异常
     */
    UNKNOWN_EXCEPTION(HttpCodes.ERROR, "UNKNOWN EXCEPTION", "未知异常"),
    /**
     * 未获得签名
     */
    NOT_FUND_EXCEPTION(HttpCodes.ERROR, "NO SIGNATURE OBTAINED", "未获得签名"),
    /**
     * 签名异常
     */
    TOKEN_EXCEPTION(HttpCodes.UNAUTHORIZED, "SIGNATURE ANOMALY", "签名异常"),
    /**
     * 签名不合法
     */
    TOKEN_WRONGFUL(HttpCodes.BAD_REQUEST, "SIGNATURE IS ILLEGAL", "签名不合法"),
    /**
     * 签名过期
     */
    TOKEN_EXPIRED(HttpCodes.FORBIDDEN, "SIGNATURE EXPIRED ACCESS PROHIBITED", "签名过期，禁止访问"),
    /**
     * 请求重复提交
     */
    DUPLICATE_SUBMIT(HttpCodes.DUPLICATE_SUBMIT, "PLEASE DO NOT RESUBMIT", "请不要重新提交"),

    /**
     * 账号不存在
     */
    ACCOUNT_DOES_NOT_EXIST(HttpCodes.ERROR, "ACCOUNT DOES NOT EXIST", "账号不存在"),
    ;

    private final Integer code;
    private final String introduction;
    private final String desc;
}