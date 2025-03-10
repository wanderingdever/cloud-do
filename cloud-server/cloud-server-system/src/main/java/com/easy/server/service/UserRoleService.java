package com.easy.server.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.server.bean.entity.UserRole;
import com.easy.server.dao.UserRoleMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserRoleService extends ServiceImpl<UserRoleMapper, UserRole> {

    public List<UserRole> getList(List<String> userIdList, List<String> roleList) {
        List<UserRole> list = new LinkedList<>();
        for (String userId : userIdList) {
            for (String roleId : roleList) {
                list.add(new UserRole(userId, roleId));
            }
        }
        return list;
    }
}