package com.easy.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.server.bean.entity.UserRole;
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
public interface UserRoleMapper extends BaseMapper<UserRole> {

    @Select("DELETE FROM sys_user_role WHERE user_id = #{userId}")
    void removeByUserId(@Param(value = "userId") String userId);
}