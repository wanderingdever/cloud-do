package com.easy.server.service;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.api.service.UserRemoteService;
import com.easy.api.vo.UserInfoVO;
import com.easy.common.core.dto.IdDTO;
import com.easy.common.datasource.utils.PageUtils;
import com.easy.common.util.lang.StringUtils;
import com.easy.server.bean.dto.notice.NoticeAddDTO;
import com.easy.server.bean.dto.notice.NoticeEditDTO;
import com.easy.server.bean.dto.notice.NoticeSearchDTO;
import com.easy.server.bean.entity.Notice;
import com.easy.server.bean.entity.UserNotice;
import com.easy.server.bean.vo.notice.NoticeVO;
import com.easy.server.bean.vo.notice.UserNoticeVO;
import com.easy.server.dao.NoticeMapper;
import com.easy.server.enums.ArticleStatus;
import lombok.AllArgsConstructor;
import org.apache.dubbo.config.annotation.DubboReference;
import org.dromara.hutool.core.bean.BeanUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
public class NoticeService extends ServiceImpl<NoticeMapper, Notice> {

    private final UserNoticeService userNoticeService;
    @DubboReference
    private UserRemoteService userRemoteService;


    public Page<Notice> pageNotice(NoticeSearchDTO dto) {
        return lambdaQuery().like(StringUtils.isNotBlank(dto.getTitle()), Notice::getTitle, dto.getTitle())
                .eq(StringUtils.isNotBlank(dto.getType()), Notice::getType, dto.getType())
                .eq(StringUtils.isNotNull(dto.getStatus()), Notice::getStatus, dto.getStatus())
                .page(PageUtils.getPage(dto));
    }

    @Transactional(rollbackFor = Exception.class)
    public void addNotice(NoticeAddDTO dto) {
        // 核实ID
        List<UserInfoVO> userInfoList = userRemoteService.getUserInfoList(dto.getUserIds());
        Notice notice = new Notice();
        notice.setTitle(dto.getTitle());
        notice.setType(dto.getType());
        notice.setContent(dto.getContent());
        notice.setContentText(dto.getContentText());
        notice.setStatus(dto.getStatus());
        save(notice);
        if (!userInfoList.isEmpty()) {
            userNoticeService.saveBatch(userInfoList.stream().map(user -> new UserNotice(user.getId(), notice.getId(), notice.getStatus())).collect(Collectors.toSet()));
        }
    }

    public NoticeVO get(IdDTO dto) {
        NoticeVO noticeVO = BeanUtil.copyProperties(getById(dto.getId()), NoticeVO.class);
        List<UserNotice> userList = userNoticeService.lambdaQuery().eq(UserNotice::getNoticeId, dto.getId()).list();
        if (!userList.isEmpty()) {
            noticeVO.setUserIds(userList.stream().map(UserNotice::getUserId).collect(Collectors.toList()));
        }
        return noticeVO;
    }

    public void editNotice(NoticeEditDTO dto) {
        // 核实ID
        List<UserInfoVO> userInfoList = userRemoteService.getUserInfoList(dto.getUserIds());
        Notice notice = new Notice();
        notice.setId(dto.getId());
        notice.setTitle(dto.getTitle());
        notice.setType(dto.getType());
        notice.setContent(dto.getContent());
        notice.setContentText(dto.getContentText());
        notice.setStatus(dto.getStatus());
        updateById(notice);
        if (!userInfoList.isEmpty()) {
            // 删除原来的
            userNoticeService.getBaseMapper().deleteByNoticeId(dto.getId());
            // 插入新数据
            userNoticeService.saveBatch(userInfoList.stream().map(user -> new UserNotice(user.getId(), notice.getId(), notice.getStatus())).collect(Collectors.toSet()));
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void del(IdDTO dto) {
        // 删除记录
        this.getBaseMapper().removeById(dto.getId());
        // 删除原来的
        userNoticeService.getBaseMapper().deleteByNoticeId(dto.getId());
    }

    public Page<UserNoticeVO> userPageNotice(NoticeSearchDTO dto) {
        dto.setUserId(StpUtil.getLoginId().toString());
        Page<UserNoticeVO> page = PageUtils.getPage(dto);
        page.setOptimizeCountSql(false);
        page.setRecords(getBaseMapper().userPageNotice(page, dto));
        return page;
    }

    public void userRead(IdDTO dto) {
        if ("0".equals(dto.getId())) {
            userNoticeService.lambdaUpdate().eq(UserNotice::getUserId, StpUtil.getLoginId()).set(UserNotice::getStatus, ArticleStatus.READ).update();
        } else {
            userNoticeService.lambdaUpdate().eq(UserNotice::getId, dto.getId()).set(UserNotice::getStatus, ArticleStatus.READ).update();
        }
    }
}