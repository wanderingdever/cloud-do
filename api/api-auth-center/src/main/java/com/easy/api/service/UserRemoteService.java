package com.easy.api.service;


import com.easy.api.vo.UserInfoAddVO;
import com.easy.api.vo.UserInfoVO;
import com.easy.api.vo.UserPwdVO;

import java.util.List;

public interface UserRemoteService {

    /**
     * 判断用户是否存在
     *
     * @param username 用户名/邮箱/手机号
     * @return true/false
     */
    boolean userIsExist(String username);

    /**
     * 新增账号
     *
     * @param userAccount 新增账号信息
     * @return 账号 id
     */
    String addUserAccount(UserInfoAddVO userAccount);

    /**
     * 更新用户信息
     *
     * @param userInfo 用户信息
     * @return true/false
     */
    String updateUserInfo(UserInfoVO userInfo);

    /**
     * 根据账号 id 获取账号信息
     *
     * @param id 账号 id
     * @return {@link UserInfoVO}
     */
    UserInfoVO getUserInfo(String id);

    /**
     * 获取用户信息
     *
     * @param ids id集合
     * @return 信息集合
     */
    List<UserInfoVO> getUserInfoList(List<String> ids);


    /**
     * 根据账号 id 删除
     *
     * @param id 账号id
     */
    void deleteByUserId(String id);

    void resetPwd(UserPwdVO pwd);
}