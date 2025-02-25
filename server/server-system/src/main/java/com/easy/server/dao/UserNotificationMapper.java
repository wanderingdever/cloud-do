package com.easy.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.server.bean.entity.UserNotice;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * ${desc}
 * </p>
 *
 * @author Matt
 */
@Mapper
public interface UserNotificationMapper extends BaseMapper<UserNotice> {

    @Delete("DELETE FROM sys_user_notice WHERE notice_id = #{id}")
    void deleteByNoticeId(@Param("id") String id);
}