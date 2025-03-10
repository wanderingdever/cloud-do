package com.easy.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.server.bean.entity.Org;
import com.easy.server.bean.vo.org.OrgUserTreeVO;
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
public interface OrgMapper extends BaseMapper<Org> {

    @Select({
            "SELECT id AS id,",
            "       org_parent_id AS parentId,",
            "       org_name AS `name`,",
            "       'org' AS `type`,",
            "       (EXISTS (SELECT 1 FROM sys_org o WHERE o.org_parent_id = sys_org.id)",
            "        OR EXISTS (SELECT 1 FROM sys_user_org uo WHERE uo.org_id = sys_org.id)) AS hasChildren",
            "FROM sys_org",
            "WHERE org_parent_id = #{id}"
    })
    List<OrgUserTreeVO> selectOrgUserTree(@Param("id") String id);
}