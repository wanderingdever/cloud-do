package com.easy.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.api.vo.RoleVO;
import com.easy.server.bean.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * ${desc}
 * </p>
 *
 * @author Matt
 */
@Mapper
public interface RoleMapper extends BaseMapper<Role> {

    @Select("SELECT r.* FROM sys_role r JOIN sys_user_role ur ON r.id = ur.role_id WHERE ur.user_id = #{userId} AND ur.del = 0")
    List<RoleVO> getAuthRoleList(@Param("userId") String userId);

    List<RoleVO> selectRoleVoByIds(@Param("roleIds") List<String> roleIds);
}