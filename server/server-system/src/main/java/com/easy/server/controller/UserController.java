package com.easy.server.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.api.vo.UserPwdVO;
import com.easy.common.core.dto.IdDTO;
import com.easy.common.core.enums.AccountStatus;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.util.lang.StringUtils;
import com.easy.server.bean.dto.user.UserEditDTO;
import com.easy.server.bean.dto.user.UserInfoDTO;
import com.easy.server.bean.dto.user.UserSearchDTO;
import com.easy.server.bean.vo.user.UserExpandVO;
import com.easy.server.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/user")
@Tag(name = "用户管理")
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
    public UserExpandVO loginInfo(HttpServletRequest request) {
        return userService.getLoginUserInfo(request);
    }

    @PostMapping("/add")
    @Operation(summary = "新增用户")
    @SaCheckPermission(value = "system.user.add")
    public void add(@Valid @RequestBody UserInfoDTO add) {
        // 判断用户名、邮箱、手机号三者必须有一个不为空
        if (StringUtils.isBlank(add.getUsername()) && StringUtils.isBlank(add.getEmail()) && StringUtils.isBlank(add.getPhone())) {
            throw new CustomizeException("用户名/邮箱/手机号三者必须有一个不为空");
        }
        // 默认为未激活
        if (add.getStatus() == null) {
            add.setStatus(AccountStatus.INACTIVATED);
        }
        userService.add(add);
    }

    @PostMapping("/org_user_page")
    @Operation(summary = "分页查询机构下用户")
    @SaCheckPermission(value = "system.user.page")
    public Page<UserExpandVO> orgUserPage(@RequestBody UserSearchDTO dto) {
        return userService.orgUserPage(dto);
    }


    @PostMapping("/update")
    @Operation(summary = "更新用户")
    @SaCheckPermission(value = "system.user.update")
    public void update(@Valid @RequestBody UserEditDTO user) {
        userService.update(user);
    }


    @PostMapping("/info")
    @Operation(summary = "获取用户信息")
    public UserExpandVO getUserInfoById(@RequestBody IdDTO dto) {
        return userService.getUserInfoById(dto.getId());
    }

    /**
     * 删除
     *
     * @param dto 主键集合
     */
    @PostMapping("/del")
    @Operation(summary = "删除用户")
    @SaCheckPermission(value = "system.user.del")
    public void del(@RequestBody IdDTO dto) {
        userService.del(dto.getId());
    }

    /**
     * 重置密码
     *
     * @param dto 账号
     */
    @PostMapping("/reset_pwd")
    @Operation(summary = "重置密码")
    @SaCheckPermission(value = "system.user.update")
    public void resetPwd(@RequestBody UserPwdVO dto) {
        userService.resetPwd(dto);
    }

}