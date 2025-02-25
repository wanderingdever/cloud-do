package com.easy.common.core.constant;

/**
 * HTTP响应码
 * </p>
 *
 * @author Matt
 */
public interface HttpCodes {

    /**
     * 成功
     */
    Integer SUCCESS = 200;

    /**
     * 错误请求
     */
    Integer BAD_REQUEST = 400;

    /**
     * 未授权
     */
    Integer UNAUTHORIZED = 401;

    /**
     * 禁止访问
     */
    Integer FORBIDDEN = 403;

    /**
     * 方法不允许
     */
    Integer METHOD_NOT_ALLOWED = 405;

    /**
     * 失败
     */
    Integer ERROR = 500;

    /**
     * 重复提交响应码
     */
    Integer DUPLICATE_SUBMIT = 4001;

}