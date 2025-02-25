package com.easy.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.easy.auth.bean.PwdLogin;
import com.easy.auth.bean.TokenInfo;
import com.easy.auth.bean.dto.ChangePwd;
import com.easy.auth.service.LoginService;
import com.easy.common.core.enums.REnum;
import com.easy.common.core.exception.CustomizeException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
    public Object ssoRequest() {
        // return SaSsoServerProcessor.instance.dister();
        return null;
    }

    @PostMapping("/pwd_login")
    @Operation(summary = "账号密码登录")
    public TokenInfo pwdLogin(@Valid @RequestBody PwdLogin pwdLogin, HttpServletRequest request) {
        // 校验 sign 是否一致
        if (!pwdLogin.validateSign(pwdLogin.getSign())) {
            throw new CustomizeException(REnum.TOKEN_EXCEPTION);
        }
        return loginService.pwdLogin(pwdLogin, request);
    }

    @GetMapping(value = "/logout")
    @Operation(summary = "退出登录")
    public void logout() {
        StpUtil.logout();
    }


    /**
     * 修改密码
     */
    @PostMapping(value = "/update_pwd")
    @Operation(summary = "退出登录")
    public void updatePwd(@RequestBody ChangePwd changePwd) {
        loginService.updatePassword(changePwd);
    }
}