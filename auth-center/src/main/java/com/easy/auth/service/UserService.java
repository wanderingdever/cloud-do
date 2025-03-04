package com.easy.auth.service;

import cn.dev33.satoken.secure.BCrypt;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.api.service.UserRemoteService;
import com.easy.api.vo.UserInfoAddVO;
import com.easy.api.vo.UserInfoVO;
import com.easy.api.vo.UserPwdVO;
import com.easy.auth.bean.dto.UserChangePwd;
import com.easy.auth.bean.entity.UserAccount;
import com.easy.auth.bean.entity.UserInfo;
import com.easy.auth.dao.UserMapper;
import com.easy.common.core.enums.AccountClient;
import com.easy.common.core.enums.AccountStatus;
import com.easy.common.core.enums.REnum;
import com.easy.common.core.enums.Sex;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.util.lang.BeanUtils;
import com.easy.common.util.lang.StringUtils;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboService;
import org.dromara.hutool.core.data.id.IdUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 账号信息 服务实现类
 * </p>
 *
 * @author Matt
 * @since 2025-01-10
 */
@Service
@AllArgsConstructor
@DubboService
public class UserService extends ServiceImpl<UserMapper, UserAccount> implements UserRemoteService {

    private final UserInfoService userInfoService;

    /**
     * 查找判断用户是否存在
     *
     * @param username 用户名
     */
    @Override
    public boolean userIsExist(String username) {
        return this.lambdaQuery()
                .eq(UserAccount::getUsername, username)
                .or()
                .eq(UserAccount::getEmail, username)
                .or()
                .eq(UserAccount::getPhone, username)
                .count() > 0;
    }


    /**
     * 根据账号密码查询用户信息
     *
     * @param username 账号
     * @param password 密码
     */
    public UserAccount getUserByUsernameAndPassword(String username, String password) {
        UserAccount userAccount = getUserByUsername(username);
        // 校验用户是否存在
        if (userAccount == null) {
            throw new CustomizeException("用户不存在");
        }
        // 校验密码
        if (!BCrypt.checkpw(password, userAccount.getPassword())) {
            throw new CustomizeException("密码错误");
        }
        // 校验账号状态
        if (userAccount.getStatus() != AccountStatus.NORMAL) {
            throw new CustomizeException(userAccount.getStatus().getDesc());
        }
        return userAccount;
    }

    /**
     * 根据账号查询用户信息
     *
     * @param username 账号
     * @return 用户信息
     */
    public UserAccount getUserByUsername(String username) {
        UserAccount userAccount = this.lambdaQuery()
                .eq(UserAccount::getUsername, username)
                .or()
                .eq(UserAccount::getEmail, username)
                .or()
                .eq(UserAccount::getPhone, username)
                .one();
        if (userAccount == null) {
            throw new CustomizeException(REnum.ACCOUNT_DOES_NOT_EXIST);
        }
        return userAccount;
    }

    /**
     * 新增用户
     *
     * @param username 用户名
     * @param password 密码
     * @param client   客户端
     */
    @Transactional(rollbackFor = Exception.class)
    public void addUserWithUserPwd(String username, String password, AccountClient client) {
        if (userIsExist(username)) {
            throw new RuntimeException("用户名已存在");
        }
        UserAccount userAccount = new UserAccount();
        userAccount.setUsername(username);
        userAccount.setPassword(BCrypt.hashpw(password));
        userAccount.setClient(client);
        userAccount.setStatus(AccountStatus.NORMAL);
        // 默认管理端账号为停用状态
        if (client == AccountClient.SYSTEM) {
            userAccount.setStatus(AccountStatus.INACTIVATED);
        }
        save(userAccount);
        // 构建用户基本信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userAccount.getId());
        userInfo.setClient(client);
        // 默认昵称
        userInfo.setNickname("用户" + IdUtil.fastSimpleUUID().substring(0, 8));
        userInfo.setSex(Sex.UNKNOWN);
        userInfoService.save(userInfo);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String addUserAccount(UserInfoAddVO addInfo) {
        UserAccount userAccount = BeanUtils.copyProperties(addInfo, UserAccount.class);
        // 加密密码
        userAccount.setPassword(BCrypt.hashpw(addInfo.getPassword()));
        save(userAccount);
        // 构建用户基本信息
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(userAccount.getId());
        userInfo.setClient(userAccount.getClient());
        userInfo.setNickname(addInfo.getNickname());
        userInfo.setSex(addInfo.getSex());
        // 默认配置
        if (StringUtils.isBlank(addInfo.getNickname())) {
            userInfo.setNickname("用户" + IdUtil.fastSimpleUUID().substring(0, 8));
        }
        if (StringUtils.isNull(addInfo.getSex())) {
            userInfo.setSex(Sex.UNKNOWN);
        }
        userInfoService.save(userInfo);
        return userAccount.getId();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String updateUserInfo(UserInfoVO userInfo) {
        this.lambdaUpdate().eq(UserAccount::getId, userInfo.getId()).set(UserAccount::getStatus, userInfo.getStatus()).update();
        userInfoService.lambdaUpdate().eq(UserInfo::getUserId, userInfo.getId())
                .set(StringUtils.isNotBlank(userInfo.getAvatar()), UserInfo::getAvatar, userInfo.getAvatar())
                .set(StringUtils.isNotBlank(userInfo.getNickname()), UserInfo::getNickname, userInfo.getNickname())
                .set(StringUtils.isNotNull(userInfo.getSex()), UserInfo::getSex, userInfo.getSex())
                .update();
        return userInfo.getId();
    }

    @Override
    public UserInfoVO getUserInfo(String id) {
        UserAccount userAccount = this.lambdaQuery()
                .eq(UserAccount::getId, id)
                .one();
        if (userAccount == null) {
            throw new CustomizeException(REnum.ACCOUNT_DOES_NOT_EXIST);
        }
        UserInfo userInfo = userInfoService.lambdaQuery()
                .eq(UserInfo::getUserId, id)
                .one();
        UserInfoVO userInfoAddVO = BeanUtils.copyProperties(userAccount, UserInfoVO.class);
        userInfoAddVO.setNickname(userInfo.getNickname());
        userInfoAddVO.setSex(userInfo.getSex());
        userInfoAddVO.setAvatar(userInfo.getAvatar());
        userInfoAddVO.setPassword(null);
        return userInfoAddVO;
    }

    /**
     * 获取用户信息
     *
     * @param ids id集合
     * @return 信息集合
     */
    @Override
    public List<UserInfoVO> getUserInfoList(List<String> ids) {
        return this.getBaseMapper().selectUserInfoList(ids);
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteByUserId(String id) {
        // 删除账号
        this.baseMapper.deleteById(id);
        userInfoService.lambdaUpdate().eq(UserInfo::getUserId, id).remove();
    }

    /**
     * 重置密码
     *
     * @param pwd
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void resetPwd(UserPwdVO pwd) {
        UserAccount userAccount = this.lambdaQuery()
                .eq(UserAccount::getId, pwd.getId())
                .one();
        if (userAccount == null) {
            throw new CustomizeException(REnum.ACCOUNT_DOES_NOT_EXIST);
        }
        this.lambdaUpdate()
                .eq(UserAccount::getId, pwd.getId())
                .set(UserAccount::getPassword, BCrypt.hashpw(pwd.getPassword()))
                .update();
    }

    @Transactional(rollbackFor = Exception.class)
    public void changePwd(UserChangePwd dto) {
        String userId = StpUtil.getLoginId().toString();
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new CustomizeException("两次密码不一致");
        }
        UserAccount userAccount = this.lambdaQuery()
                .eq(UserAccount::getId, userId)
                .one();
        if (!BCrypt.checkpw(dto.getOldPassword(), userAccount.getPassword())) {
            throw new CustomizeException("旧密码错误");
        }
        this.lambdaUpdate()
                .eq(UserAccount::getId, userId)
                .set(UserAccount::getPassword, BCrypt.hashpw(dto.getPassword()))
                .update();

    }
}