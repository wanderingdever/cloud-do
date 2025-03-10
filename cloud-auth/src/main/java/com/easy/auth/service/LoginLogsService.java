package com.easy.auth.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.auth.bean.entity.LoginLogs;
import com.easy.auth.dao.LoginLogsMapper;
import com.easy.common.core.enums.LoginType;
import com.easy.common.util.http.IpLocation;
import com.easy.common.util.http.IpUtils;
import com.easy.common.util.lang.DateUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 登录记录 服务实现类
 * </p>
 *
 * @author Matt
 * @since 2025-01-20
 */
@Service
public class LoginLogsService extends ServiceImpl<LoginLogsMapper, LoginLogs> {


    /**
     * 记录登录信息
     */
    @Transactional(rollbackFor = Exception.class)
    public void recordLoginInfo(String userId, LoginType loginType, HttpServletRequest request) {
        // 保存登录记录
        IpLocation location = IpUtils.getLocation(request);
        LoginLogs loginLogs = new LoginLogs();
        loginLogs.setUserId(userId);
        loginLogs.setLoginType(loginType);
        loginLogs.setIp(location.getIp());
        loginLogs.setBrowser(request.getHeader("User-Agent"));
        loginLogs.setIpLocation(String.join(",", location.getCountry(), location.getProvince(), location.getCity()));
        loginLogs.setLoginTime(DateUtils.nowDateTime());
        this.save(loginLogs);
    }
}