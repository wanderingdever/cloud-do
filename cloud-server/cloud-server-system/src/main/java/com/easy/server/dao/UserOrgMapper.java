package com.easy.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.server.bean.entity.UserOrg;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ${desc}
 * </p>
 *
 * @author Matt
 */
@Mapper
public interface UserOrgMapper extends BaseMapper<UserOrg> {

    @Select("DELETE FROM sys_user_org WHERE user_id = #{userId}")
    void removeByUserId(@Param(value = "userId") String userId);
}