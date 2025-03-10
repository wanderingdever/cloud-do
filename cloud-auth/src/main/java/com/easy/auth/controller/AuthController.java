package com.easy.auth.controller;

import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import com.easy.auth.bean.PwdLogin;
import com.easy.auth.bean.TokenInfo;
import com.easy.auth.service.LoginService;
import com.easy.common.core.enums.REnum;
import com.easy.common.core.exception.CustomizeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录接口
 * </p>
 *
 * @author Matt
 */
@RestController
@Tag(name = "登录")
@AllArgsConstructor
public class AuthController {

    private final LoginService loginService;
    /**
     * SSO-Server端：处理所有SSO相关请求
     */
    @RequestMapping("/sso/*")
    @Operation(summary = "处理所有SSO相关请求")
    public Object ssoRequest() {
        // return SaSsoServerProcessor.instance.dister();
        return null;
    }

    @PostMapping("/pwd_login")
    @Operation(summary = "账号密码登录")
    @SaIgnore
    public TokenInfo pwdLogin(@Valid @RequestBody PwdLogin pwdLogin, HttpServletRequest request) {
        // 校验 sign 是否一致
        if (!pwdLogin.validateSign(pwdLogin.getSign())) {
            throw new CustomizeException(REnum.TOKEN_EXCEPTION);
        }
        return loginService.pwdLogin(pwdLogin, request);
    }

    @PostMapping(value = "/logout")
    @Operation(summary = "退出登录")
    @SaIgnore
    public void logout() {
        StpUtil.logout();
    }

}