package com.easy.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.api.vo.UserInfoVO;
import com.easy.auth.bean.entity.UserAccount;
import com.easy.auth.dao.provider.UserMapperProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * <p>
 * 账号信息 Mapper 接口
 * </p>
 *
 * @author Matt
 * @since 2025-01-10
 */
@Mapper
public interface UserMapper extends BaseMapper<UserAccount> {

    @SelectProvider(type = UserMapperProvider.class, method = "selectUserInfoList")
    List<UserInfoVO> selectUserInfoList(@Param("ids") List<String> ids);

    @Select("UPDATE user_account SET password = #{newPwd} WHERE id = #{userId}")
    void updatePwdById(@Param("userId") String userId, @Param("newPwd") String newPwd);
}