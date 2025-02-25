package com.easy.server.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.api.vo.RoleVO;
import com.easy.api.vo.UserRoleAndPermissionVO;
import com.easy.common.core.dto.IdDTO;
import com.easy.common.core.dto.IdListDTO;
import com.easy.common.core.enums.DelEnum;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.datasource.utils.PageUtils;
import com.easy.common.util.lang.BeanUtils;
import com.easy.common.util.lang.StringUtils;
import com.easy.server.bean.dto.role.RoleDTO;
import com.easy.server.bean.dto.role.RoleEditDTO;
import com.easy.server.bean.dto.role.RoleSearchDTO;
import com.easy.server.bean.entity.Role;
import com.easy.server.bean.entity.RoleMenu;
import com.easy.server.bean.entity.UserRole;
import com.easy.server.bean.vo.RoleInfoVO;
import com.easy.server.dao.RoleMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * ${desc}
 * </p>
 *
 * @author Matt
 */
@Service
@AllArgsConstructor
public class RoleService extends ServiceImpl<RoleMapper, Role> {

    private final static Logger LOGGER = LoggerFactory.getLogger(RoleService.class);

    private final UserRoleService userRoleService;
    private final RoleMenuService roleMenuService;

    /**
     * 根据用户ID获取用户的角色列表KEY
     *
     * @param userId 用户ID
     * @return {@link  List<String>} 用户的角色key
     */
    public UserRoleAndPermissionVO getUserRoleKeyList(String userId) {
        // 查询用户角色关联
        List<UserRole> userRoleList = userRoleService.lambdaQuery().eq(UserRole::getUserId, userId).list();
        if (userRoleList.isEmpty()) {
            LOGGER.error("未查询到用户角色关联");
            return null;
        }
        // 查询role id
        List<String> roleIdList = userRoleList.stream().map(UserRole::getRoleId).toList();
        // 查询权限集合
        List<String> permissions = roleMenuService.getBaseMapper().selectRolePermissions(roleIdList);
        // 查询角色key集合
        List<RoleVO> roles = baseMapper.selectRoleVoByIds(roleIdList);
        return new UserRoleAndPermissionVO(roles, permissions);
    }

    public Page<RoleInfoVO> page(RoleSearchDTO dto) {
        Page<Role> page = lambdaQuery()
                .like(StringUtils.isNotBlank(dto.getRoleName()), Role::getRoleName, dto.getRoleName())
                .eq(StringUtils.isNotBlank(dto.getRoleKey()), Role::getRoleKey, dto.getRoleKey())
                .eq(StringUtils.isNotNull(dto.getAuthorityLevel()), Role::getAuthorityLevel, dto.getAuthorityLevel())
                .eq(StringUtils.isNotNull(dto.getEnable()), Role::getEnable, dto.getEnable())
                .eq(StringUtils.isNotBlank(dto.getOrgId()), Role::getOrgId, dto.getOrgId())
                .page(PageUtils.getPage(dto));
        return PageUtils.getPage(page, RoleInfoVO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    public void addRole(RoleDTO dto) {
        Role role = BeanUtils.copyProperties(dto, Role.class);
        // 保存角色信息
        this.save(role);
        // 保存角色菜单关联信息
        saveRoleMenu(role.getId(), dto.getMenuList());
    }

    /**
     * 构建并保存角色菜单关联信息
     *
     * @param roleId  角色id
     * @param menuIdS 菜单ID数组
     */
    @Transactional(rollbackFor = Exception.class, timeout = 5)
    public void saveRoleMenu(String roleId, List<String> menuIdS) {
        List<RoleMenu> roleMenuList = getRoleMenuList(roleId, menuIdS);
        roleMenuService.saveBatch(roleMenuList);
    }

    /**
     * 构建角色菜单关联实体集合
     *
     * @param roleId  角色ID
     * @param menuIdS 菜单ID数组
     * @return List<RoleMenu>
     */
    public List<RoleMenu> getRoleMenuList(String roleId, List<String> menuIdS) {
        if (CollectionUtils.isEmpty(menuIdS)) {
            return null;
        }
        List<RoleMenu> roleMenuList = new ArrayList<>();
        for (String menuId : menuIdS) {
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuList.add(roleMenu);
        }
        return roleMenuList;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleEditDTO dto) {
        Role role = lambdaQuery().eq(Role::getId, dto.getId()).one();
        if (!role.getOrgId().equals(dto.getOrgId())) {
            long userRole = userRoleService.lambdaQuery().eq(UserRole::getRoleId, dto.getId()).count();
            if (userRole > 0) {
                throw new CustomizeException("所选角色已被分配,无法删除");
            }
        }
        // 更新角色信息
        BeanUtils.copyProperties(dto, role);
        this.updateById(role);
        // 删除原有菜单
        roleMenuService.remove(new QueryWrapper<RoleMenu>().lambda().eq(RoleMenu::getRoleId, dto.getId()));
        // 保存角色菜单关联信息
        saveRoleMenu(dto.getId(), dto.getMenuList());
    }

    @Transactional(rollbackFor = Exception.class)
    public void delRole(IdListDTO dto) {
        for (String id : dto.getIdList()) {
            long userRole = userRoleService.lambdaQuery().eq(UserRole::getRoleId, id).count();
            if (userRole > 0) {
                throw new CustomizeException("所选角色已被分配,无法删除");
            } else {
                // 删除角色
                this.removeById(id);
                // 删除角色菜单关联
                roleMenuService.lambdaUpdate()
                        .eq(RoleMenu::getRoleId, id)
                        .set(RoleMenu::getDel, DelEnum.DELETE.getCode())
                        .update();
            }
        }
    }

    public List<String> roleMenuIds(IdDTO dto) {
        List<RoleMenu> list = roleMenuService.lambdaQuery().eq(RoleMenu::getRoleId, dto.getId()).list();
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.stream().map(RoleMenu::getMenuId).collect(Collectors.toList());
    }
}