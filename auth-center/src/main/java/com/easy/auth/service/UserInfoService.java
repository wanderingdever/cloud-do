package com.easy.auth.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.auth.bean.entity.UserInfo;
import com.easy.auth.dao.UserInfoMapper;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户基本信息 服务实现类
 * </p>
 *
 * @author Matt
 * @since 2025-01-20
 */
@Service
public class UserInfoService extends ServiceImpl<UserInfoMapper, UserInfo> {

}
