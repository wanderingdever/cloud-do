package com.easy.server.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.common.core.dto.IdDTO;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.datasource.utils.PageUtils;
import com.easy.common.util.lang.BeanUtils;
import com.easy.common.util.lang.StringUtils;
import com.easy.common.util.tree.TreeUtils;
import com.easy.server.bean.dto.org.OrgDTO;
import com.easy.server.bean.dto.org.OrgEditDTO;
import com.easy.server.bean.dto.org.OrgPageDTO;
import com.easy.server.bean.entity.Org;
import com.easy.server.bean.entity.UserOrg;
import com.easy.server.bean.vo.org.OrgSimpleTreeVO;
import com.easy.server.bean.vo.org.OrgTreeVO;
import com.easy.server.bean.vo.org.OrgVO;
import com.easy.server.dao.OrgMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * </p>
 *
 * @author Matt
 */
@Service
@AllArgsConstructor
public class OrgService extends ServiceImpl<OrgMapper, Org> {


    private final UserOrgService userOrgService;

    public OrgVO info(IdDTO dto) {
        Org org = getOrg(dto.getId());
        return toOrgVO(org);
    }

    public Org getOrg(String id) {
        return getOrg(List.of(id)).getFirst();
    }

    public List<Org> getOrg(List<String> idList) {
        HashSet<String> idSet = new HashSet<>(idList);
        List<Org> list = lambdaQuery()
                .in(Org::getId, idSet)
                .list();
        if (list.size() != idSet.size()) {
            throw new CustomizeException("组织不存在");
        }
        return list;
    }


    public Page<OrgVO> page(OrgPageDTO dto) {
        Page<Org> page = lambdaQuery()
                .eq(StringUtils.isNotBlank(dto.getOrgParentId()), Org::getOrgParentId, dto.getOrgParentId())
                .like(StringUtils.isNotBlank(dto.getOrgName()), Org::getOrgName, dto.getOrgName())
                .page(PageUtils.getPage(dto));
        return PageUtils.getPage(page, OrgVO.class);
    }

    @Transactional(rollbackFor = Exception.class, timeout = 5)
    public void add(OrgDTO dto) {
        Org org = BeanUtils.copyProperties(dto, Org.class);
        save(org);
    }

    @Transactional(rollbackFor = Exception.class, timeout = 5)
    public void update(OrgEditDTO dto) {
        Org org = BeanUtils.copyProperties(dto, Org.class);
        updateById(org);
    }

    @Transactional(rollbackFor = Exception.class, timeout = 5)
    public void del(IdDTO dto) {
        Long child = lambdaQuery().in(Org::getOrgParentId, dto.getId()).count();
        if (child > 0) {
            throw new CustomizeException("所选组织存在下级组织,无法被删除");
        }
        Long user = userOrgService.lambdaQuery().in(UserOrg::getOrgId, dto.getId()).count();
        if (user > 0) {
            throw new CustomizeException("所选组织已与用户关联,无法被删除");
        }
        removeById(dto.getId());
    }


    /**
     * 构建详细数据的机构树形
     *
     * @return {@link List< OrgTreeVO >}
     */
    public List<OrgTreeVO> tree() {
        LambdaQueryWrapper<Org> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Org> list = list(lambdaQueryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<OrgTreeVO> tree = BeanUtils.copyToList(list, OrgTreeVO.class);
        return TreeUtils.makeTree(tree, x -> "0".equals(x.getOrgParentId()), (x, y) -> x.getId().equals(y.getOrgParentId()), OrgTreeVO::setChildren);
    }

    /**
     * 构建简单数据的机构树形
     *
     * @return {@link List<  OrgSimpleTreeVO  >}
     */
    public List<OrgSimpleTreeVO> simpleTree() {
        LambdaQueryWrapper<Org> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        List<Org> list = list(lambdaQueryWrapper);
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList<>();
        }
        List<OrgSimpleTreeVO> tree = BeanUtils.copyToList(list, OrgSimpleTreeVO.class);
        return TreeUtils.makeTree(tree, x -> "0".equals(x.getOrgParentId()), (x, y) -> x.getId().equals(y.getOrgParentId()), OrgSimpleTreeVO::setChildren);
    }

    private OrgVO toOrgVO(Org org) {
        return BeanUtils.copyProperties(org, OrgVO.class);
    }
}