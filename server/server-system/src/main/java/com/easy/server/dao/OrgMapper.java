package com.easy.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.server.bean.entity.Org;
import com.easy.server.bean.vo.org.OrgUserTreeVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * ${desc}
 * </p>
 *
 * @author Matt
 */
@Mapper
public interface OrgMapper extends BaseMapper<Org> {

    List<OrgUserTreeVO> selectTheOrgInfo();
}