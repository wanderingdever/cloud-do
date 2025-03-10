package com.easy.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.server.bean.dto.notice.NoticeSearchDTO;
import com.easy.server.bean.entity.Notice;
import com.easy.server.bean.vo.notice.UserNoticeVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ${desc}
 * </p>
 *
 * @author Matt
 */
@Mapper
public interface NoticeMapper extends BaseMapper<Notice> {

    @Delete("DELETE FROM sys_notice WHERE id = #{id}")
    void removeById(@Param("id") String id);

    /**
     * 分页查询用户消息通知
     *
     * @param page 分页
     * @param dto  查询参数
     * @return List<Notice>
     */
    List<UserNoticeVO> userPageNotice(@Param("page") Page<UserNoticeVO> page, @Param("dto") NoticeSearchDTO dto);
}