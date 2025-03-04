package com.easy.auth.service;

import cn.dev33.satoken.session.TokenSign;
import cn.dev33.satoken.stp.SaLoginModel;
import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.easy.auth.bean.PwdLogin;
import com.easy.auth.bean.TokenInfo;
import com.easy.auth.bean.entity.UserAccount;
import com.easy.common.core.enums.AccountClient;
import com.easy.common.core.enums.LoginType;
import com.easy.common.core.enums.REnum;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.util.lang.DateUtils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 登录服务
 * </p>
 *
 * @author Matt
 */
@Service
@AllArgsConstructor
public class LoginService {

    private final UserService userService;
    private final LoginLogsService loginLogsService;

    /**
     * 账号密码登录
     */
    @Transactional(rollbackFor = Exception.class)
    public TokenInfo pwdLogin(PwdLogin login, HttpServletRequest request) {
        // 查询并校验账户
        UserAccount userAccount = userService.getUserByUsernameAndPassword(login.getUsername(), login.getPassword());
        // 处理客户端问题
        if (userAccount.getClient() != login.getClient()) {
            throw new CustomizeException(REnum.BAD_CREDENTIAL);
        }
        // 登录
        return loginById(userAccount.getId(), login.getClient(), login.getLoginType(), request, null);
    }

    /**
     * 颁发登录 token
     *
     * @param id        用户 ID
     * @param client    客户端
     * @param loginType 登录方式
     * @param request   请求信息
     * @param extraData 额外数据
     * @return token 信息
     */
    private TokenInfo loginById(String id, AccountClient client, LoginType loginType, HttpServletRequest request, Map<String, Object> extraData) {
        SaLoginModel loginModel = new SaLoginModel().build().setDevice(client.getValue()).setTokenSignTag(DateUtils.nowDateTime());
        loginModel.setExtraData(extraData);
        // 获取当前客户端登录的所有 token，大于三个时，踢掉最老的
        List<TokenSign> tokenSignListByLoginId = StpUtil.getTokenSignListByLoginId(id, client.getValue());
        if (tokenSignListByLoginId.size() >= 3) {
            StpUtil.logoutByTokenValue(tokenSignListByLoginId.getFirst().getValue());
        }
        StpUtil.login(id, loginModel);
        // 获取登录信息
        SaTokenInfo saTokenInfo = StpUtil.getTokenInfo();
        // 记录登录日志
        loginLogsService.recordLoginInfo(id, loginType, request);
        return new TokenInfo(saTokenInfo.getTokenValue(), saTokenInfo.getTokenTimeout(), saTokenInfo.getLoginDevice());
    }
}