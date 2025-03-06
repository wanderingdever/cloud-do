package com.easy.server.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.common.core.constant.Constants;
import com.easy.common.core.dto.IdDTO;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.util.lang.BeanUtils;
import com.easy.common.util.lang.StringUtils;
import com.easy.server.bean.dto.menu.MenuAddDTO;
import com.easy.server.bean.dto.menu.MenuEditDTO;
import com.easy.server.bean.dto.menu.MenuListDTO;
import com.easy.server.bean.dto.router.MetaVo;
import com.easy.server.bean.dto.router.RouterVO;
import com.easy.server.bean.entity.Menu;
import com.easy.server.bean.entity.RoleMenu;
import com.easy.server.bean.vo.MenuTreeVO;
import com.easy.server.dao.MenuMapper;
import com.easy.server.enums.MenuType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * ${desc}
 * </p>
 *
 * @author Matt
 */
@Service
public class MenuService extends ServiceImpl<MenuMapper, Menu> {
    private final RoleMenuService roleMenuService;

    public MenuService(RoleMenuService roleMenuService) {
        this.roleMenuService = roleMenuService;
    }

    /**
     * 获取菜单树
     *
     * @param dto {@link MenuListDTO}}
     * @return {@link List< MenuTreeVO >}
     */
    public List<MenuTreeVO> getTreeMenu(MenuListDTO dto) {
        dto.setUserId(StpUtil.getLoginIdAsString());
        List<MenuTreeVO> treeList;
        if (StpUtil.getRoleList().contains(Constants.ADMIN_ROLE)) {
            treeList = this.baseMapper.getAllMenuList(dto);
        } else {
            treeList = this.baseMapper.getMenuList(dto);
        }
        return buildMenuTree(treeList);
    }

    /**
     * 构建菜单树
     *
     * @param menus {@link List<MenuTreeVO>}
     * @return {@link List<MenuTreeVO>}
     */
    public List<MenuTreeVO> buildMenuTree(List<MenuTreeVO> menus) {
        List<MenuTreeVO> returnList = new ArrayList<MenuTreeVO>();
        List<String> tempList = new ArrayList<String>();
        for (MenuTreeVO dept : menus) {
            tempList.add(dept.getId());
        }
        for (MenuTreeVO menu : menus) {
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getParentId())) {
                recursionFn(menus, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty()) {
            returnList = menus;
        }
        return returnList;
    }

    /**
     * 更新菜单
     *
     * @param dto {@link MenuEditDTO}
     */
    @Transactional(rollbackFor = Exception.class)
    public void updateMenu(MenuEditDTO dto) {
        Menu menu = BeanUtils.copyProperties(dto, Menu.class);
        this.updateById(menu);
    }

    /**
     * 新增菜单
     *
     * @param dto {@link MenuAddDTO}
     */
    @Transactional(rollbackFor = Exception.class)
    public void addMenu(MenuAddDTO dto) {
        Menu menu = BeanUtils.copyProperties(dto, Menu.class);
        this.save(menu);
    }

    /**
     * 删除菜单
     *
     * @param id id
     */
    @Transactional(rollbackFor = Exception.class)
    public void delMenu(IdDTO id) {
        if (roleMenuService.lambdaQuery().eq(RoleMenu::getMenuId, id.getId()).count() > 0) {
            throw new CustomizeException("菜单已被分配,无法删除");
        }
        if (lambdaQuery().eq(Menu::getParentId, id.getId()).count() > 0) {
            throw new CustomizeException("请先删除子菜单");
        }
        this.removeById(id.getId());
    }

    /**
     * 获取登录用户的菜单列表
     *
     * @return {@link  List< RouterVO >}
     */
    public List<RouterVO> getUserRouter() {
        String userId = StpUtil.getLoginIdAsString();
        List<MenuTreeVO> menuList;
        if (StpUtil.getRoleList().contains(Constants.ADMIN_ROLE)) {
            menuList = this.baseMapper.getAllMenuList(new MenuListDTO()).stream().filter(menu -> menu.getMenuType() != MenuType.BUTTON).toList();
        } else {
            menuList = this.baseMapper.getMenuList(new MenuListDTO(userId, null)).stream().filter(menu -> menu.getMenuType() != MenuType.BUTTON).toList();
        }
        List<MenuTreeVO> list = getChildPerms(menuList);
        return buildRouter(list);
    }

    /**
     * 构建前端路由所需要的数据
     *
     * @param menuList 菜单数据
     * @return {@link List<RouterVO>}
     */
    public List<RouterVO> buildRouter(List<MenuTreeVO> menuList) {
        List<RouterVO> routers = new LinkedList<RouterVO>();
        for (MenuTreeVO menu : menuList) {
            RouterVO router = new RouterVO();
            router.setPath(getRouterPath(menu));
            router.setName(getRouterName(menu.getPath()));
            router.setComponent(menu.getComponent());
            router.setRedirect(menu.getRedirect());
            router.setMeta(new MetaVo(menu.getMenuName(), menu.getIcon(), menu.getIsHide(), menu.getIsKeepAlive(),
                    menu.getIsIframe(), menu.getIsAffix(), menu.getLink()));
            List<MenuTreeVO> cMenus = menu.getChildren();
            // 目录&&有子菜单
            if (StringUtils.isNotEmpty(cMenus)) {
                router.setChildren(buildRouter(cMenus));
            }
            routers.add(router);
        }
        return routers;
    }

    /**
     * 获取路由地址
     *
     * @param menu 菜单信息
     * @return 路由地址
     */
    private String getRouterPath(MenuTreeVO menu) {
        return "/" + menu.getPath();
    }

    /**
     * 获取路由名称
     *
     * @param path path
     * @return 路由名称
     */
    private String getRouterName(String path) {
        String replace = path.replace("/", "_");
        return StringUtils.toCamelCase(replace);
    }

    /**
     * 根据父节点的ID获取所有子节点
     *
     * @param list 分类表
     * @return String
     */
    private List<MenuTreeVO> getChildPerms(List<MenuTreeVO> list) {
        List<MenuTreeVO> returnList = new ArrayList<MenuTreeVO>();
        for (MenuTreeVO t : list) {
            // 一、根据传入的某个父节点ID,遍历该父节点的所有子节点
            if (t.getParentId().equals(Constants.ROOT)) {
                recursionFn(list, t);
                returnList.add(t);
            }
        }
        return returnList;
    }

    /**
     * 递归列表
     */
    private void recursionFn(List<MenuTreeVO> list, MenuTreeVO parentMenu) {
        // 得到子节点列表
        List<MenuTreeVO> childList = getChildList(list, parentMenu);
        parentMenu.setChildren(childList);
        for (MenuTreeVO tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<MenuTreeVO> getChildList(List<MenuTreeVO> list, MenuTreeVO parentMenu) {
        List<MenuTreeVO> childList = new ArrayList<MenuTreeVO>();
        for (MenuTreeVO childMenu : list) {
            if (childMenu.getParentId().equals(parentMenu.getId())) {
                childList.add(childMenu);
            }
        }
        return childList;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<MenuTreeVO> list, MenuTreeVO t) {
        return !getChildList(list, t).isEmpty();
    }

}