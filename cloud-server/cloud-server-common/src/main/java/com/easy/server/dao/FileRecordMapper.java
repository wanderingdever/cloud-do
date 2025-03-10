package com.easy.server.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.easy.server.bean.entity.FileRecord;
import org.apache.ibatis.annotations.Mapper;

/**
 * 文件记录
 * </p>
 *
 * @author hk
 */
@Mapper
public interface FileRecordMapper extends BaseMapper<FileRecord> {

}