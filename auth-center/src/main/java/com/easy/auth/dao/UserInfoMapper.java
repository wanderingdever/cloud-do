package com.easy.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.auth.bean.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 用户基本信息 Mapper 接口
 * </p>
 *
 * @author Matt
 * @since 2025-01-20
 */
@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {

}
