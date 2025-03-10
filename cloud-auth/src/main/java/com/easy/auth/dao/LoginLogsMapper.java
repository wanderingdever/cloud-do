package com.easy.auth.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.auth.bean.entity.LoginLogs;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 登录记录 Mapper 接口
 * </p>
 *
 * @author Matt
 * @since 2025-01-20
 */
@Mapper
public interface LoginLogsMapper extends BaseMapper<LoginLogs> {

}
