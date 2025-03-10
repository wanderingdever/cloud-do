package com.easy.server.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.server.bean.entity.UserOrg;
import com.easy.server.dao.UserOrgMapper;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class UserOrgService extends ServiceImpl<UserOrgMapper, UserOrg> {

    public List<UserOrg> getList(List<String> userIdList, List<String> orgList) {
        List<UserOrg> list = new LinkedList<>();
        for (String userId : userIdList) {
            for (String org : orgList) {
                list.add(new UserOrg(userId, org));
            }
        }
        return list;
    }
}