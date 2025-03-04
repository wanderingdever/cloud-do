package com.easy.server.service;


import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.api.service.UserRemoteService;
import com.easy.api.vo.*;
import com.easy.common.core.constant.Constants;
import com.easy.common.core.enums.AccountClient;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.datasource.utils.PageUtils;
import com.easy.common.util.http.IpLocation;
import com.easy.common.util.http.IpUtils;
import com.easy.common.util.lang.BeanUtils;
import com.easy.common.util.lang.DateUtils;
import com.easy.common.util.lang.StringUtils;
import com.easy.server.bean.dto.user.UserEditDTO;
import com.easy.server.bean.dto.user.UserInfoDTO;
import com.easy.server.bean.dto.user.UserSearchDTO;
import com.easy.server.bean.entity.UserOrg;
import com.easy.server.bean.entity.UserRole;
import com.easy.server.bean.vo.user.UserExpandVO;
import com.easy.server.dao.OrgMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * ${desc}
 * </p>
 *
 * @author Matt
 */
@Service
@AllArgsConstructor
public class UserService {

    @DubboReference
    private UserRemoteService userRemoteService;

    private final UserOrgService userOrgService;
    private final UserRoleService userRoleService;
    private final OrgMapper orgMapper;
    private final RoleService roleService;

    @Transactional(rollbackFor = Exception.class)
    public void add(@Valid UserInfoDTO dto) {
        // 调用增加用户接口
        UserInfoAddVO addInfo = BeanUtils.copyProperties(dto, UserInfoAddVO.class);
        addInfo.setClient(AccountClient.SYSTEM);
        addInfo.setPassword(Constants.INIT_PASSWORD);
        String userId = userRemoteService.addUserAccount(addInfo);
        // 构建用户其他信息
        addOtherInfo(dto, userId);
    }

    public UserExpandVO getLoginUserInfo(HttpServletRequest request) {
        UserInfoVO userInfo = userRemoteService.getUserInfo(StpUtil.getLoginId().toString());
        UserExpandVO userExpand = BeanUtils.copyProperties(userInfo, UserExpandVO.class);
        UserRoleAndPermissionVO userRoleList = roleService.getUserRoleKeyList(userInfo.getId());
        userExpand.setRoleList(userRoleList.getRoles().stream().map(RoleVO::getRoleKey).toList());
        userExpand.setRoles(userRoleList.getRoles());
        userExpand.setPermissionList(userRoleList.getPermissions());
        // 设置登录的IP和属地
        IpLocation location = IpUtils.getLocation(request);
        userExpand.setIp(location.getIp());
        userExpand.setIpLocation(location.getCountry() + location.getProvince() + location.getCity());
        userExpand.setLoginTime(DateUtils.nowDateTime());
        return userExpand;
    }

    public Page<UserExpandVO> orgUserPage(UserSearchDTO dto) {
        // 使用机构和用户关联表做分页
        Page<UserOrg> page = PageUtils.getPage(dto);
        List<String> userIdList = userOrgService.lambdaQuery().eq(UserOrg::getOrgId, dto.getOrgId()).page(page).getRecords().stream().map(UserOrg::getUserId).toList();
        // 联动查询用户信息
        if (CollUtil.isNotEmpty(userIdList)) {
            List<UserInfoVO> userInfoList = userRemoteService.getUserInfoList(userIdList);
            List<UserExpandVO> userExpandVOList = userInfoList.stream().map(userInfo -> BeanUtils.copyProperties(userInfo, UserExpandVO.class)).toList();
            Page<UserExpandVO> userExpandVOPage = PageUtils.getPage(page, userExpandVOList);
            page.setRecords(null);
            page.setTotal(userOrgService.lambdaQuery().eq(UserOrg::getOrgId, dto.getOrgId()).count());
            return userExpandVOPage;
        }
        return PageUtils.getPage(dto);
    }

    public UserExpandVO getUserInfoById(String id) {
        UserInfoVO userInfo = userRemoteService.getUserInfo(id);
        if (StringUtils.isNotNull(userInfo)) {
            UserExpandVO userExpand = BeanUtils.copyProperties(userInfo, UserExpandVO.class);
            // TODO 岗位、角色、组织关联查询
            List<RoleVO> userRoleList = roleService.getBaseMapper().getAuthRoleList(id);
            userExpand.setRoles(userRoleList);
            userExpand.setRoleList(userRoleList.stream().map(RoleVO::getRoleKey).toList());
            userExpand.setOrgId(userOrgService.lambdaQuery().eq(UserOrg::getUserId, id).one().getOrgId());
            return userExpand;
        }
        return null;
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(@Valid UserEditDTO dto) {
        UserInfoVO userInfo = new UserInfoVO();
        BeanUtils.copyProperties(dto, userInfo);
        String userId = userRemoteService.updateUserInfo(userInfo);
        // 删除岗位、角色、组织关联编辑
        delInfo(userId);
        // 新增岗位、角色、组织关联编辑
        addOtherInfo(dto, userId);
    }

    private void delInfo(String userId) {
        userOrgService.getBaseMapper().removeByUserId(userId);
        userRoleService.getBaseMapper().removeByUserId(userId);
    }


    private void addOtherInfo(UserInfoDTO dto, String userId) {
        // 组织关联新增
        List<UserOrg> orgList = userOrgService.getList(List.of(userId), Collections.singletonList(dto.getOrgId()));
        userOrgService.saveBatch(orgList);
        // 角色关联新增
        if (CollUtil.isNotEmpty(dto.getRoleList())) {
            List<UserRole> userRoles = userRoleService.getList(List.of(userId), dto.getRoleList());
            userRoleService.saveBatch(userRoles);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void del(String id) {
        // 删除账号
        userRemoteService.deleteByUserId(id);
        // 岗位、角色、组织关联删除
        delInfo(id);
    }

    public void resetPwd(UserPwdVO dto) {
        if (!dto.getPassword().equals(dto.getConfirmPassword())) {
            throw new CustomizeException("两次密码不一致");
        }
        userRemoteService.resetPwd(dto);
    }

}