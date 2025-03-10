package com.easy.api.service;

import com.easy.api.vo.UserRoleAndPermissionVO;

/**
 * 角色服务
 * </p>
 *
 * @author Matt
 */
public interface RoleRemoteService {

    UserRoleAndPermissionVO getUserRoleKeyList(String userId);
}