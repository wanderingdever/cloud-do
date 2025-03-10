package com.easy.auth.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.easy.api.vo.UserInfoVO;
import com.easy.auth.bean.dto.UserAccountDTO;
import com.easy.auth.bean.dto.UserChangePwdDTO;
import com.easy.auth.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户数据
 * </p>
 *
 * @author Matt
 */
@RestController("/user")
@Tag(name = "用户数据")
@AllArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * 获取用户信息
     *
     * @return UserInfoVO
     */
    @PostMapping(value = "/login_info")
    @Operation(summary = "获取登录用户信息")
    public UserInfoVO loginInfo(HttpServletRequest request) {
        String userId = StpUtil.getLoginIdAsString();
        return userService.getUserInfo(userId);
    }


    /**
     * 修改密码
     *
     * @param dto 账号
     */
    @PostMapping("/change_pwd")
    @Operation(summary = "修改密码")
    public void changePwd(@Valid @RequestBody UserChangePwdDTO dto) {
        userService.changePwd(dto);
    }

    /**
     * 忘记密码
     *
     * @param userAccount 账号
     */
    @PostMapping("/forget_pwd")
    @Operation(summary = "忘记密码")
    public void forgetPwd(@Valid @RequestBody UserAccountDTO userAccount) {
        // userService.forgetPwd(userAccount);
    }
}