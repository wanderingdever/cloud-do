package com.easy.server.service;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.easy.common.datasource.utils.PageUtils;
import com.easy.common.util.file.FileMd5Utils;
import com.easy.common.util.file.FileUtils;
import com.easy.common.util.lang.BeanUtils;
import com.easy.common.util.lang.StringUtils;
import com.easy.server.bean.dto.FileQueryDTO;
import com.easy.server.bean.entity.FileRecord;
import com.easy.server.bean.vo.FileBaseVO;
import com.easy.server.bean.vo.FileVO;
import com.easy.server.dao.FileRecordMapper;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import org.dromara.hutool.core.collection.CollUtil;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * minio文件服务
 * </p>
 *
 * @author Matt
 */
@EqualsAndHashCode(callSuper = true)
@Service
@AllArgsConstructor
public class FileRecordService extends ServiceImpl<FileRecordMapper, FileRecord> {

    private final FileService fileService;

    /**
     * 分页查询
     *
     * @param dto 查询条件
     * @return Page<FileRecord>
     */
    public Page<FileRecord> filePage(FileQueryDTO dto) {
        return lambdaQuery()
                .like(StringUtils.isNotBlank(dto.getFileName()), FileRecord::getFileName, dto.getFileName())
                .like(StringUtils.isNotBlank(dto.getFilePath()), FileRecord::getFile, dto.getFilePath())
                .like(StringUtils.isNotBlank(dto.getFileType()), FileRecord::getFileType, dto.getFileType())
                .in(CollUtil.isNotEmpty(dto.getIds()), FileRecord::getId, dto.getIds())
                .page(PageUtils.getPage(dto));
    }

    /**
     * 文件集合查询
     *
     * @param dto 查询条件
     * @return List<FileRecord>
     */
    public List<FileRecord> fileQuery(FileQueryDTO dto) {
        return lambdaQuery()
                .like(StringUtils.isNotBlank(dto.getFileName()), FileRecord::getFileName, dto.getFileName())
                .like(StringUtils.isNotBlank(dto.getFilePath()), FileRecord::getFile, dto.getFilePath())
                .like(StringUtils.isNotBlank(dto.getFileType()), FileRecord::getFileType, dto.getFileType())
                .in(CollUtil.isNotEmpty(dto.getIds()), FileRecord::getId, dto.getIds())
                .list();
    }

    /**
     * 单个文件上传
     *
     * @param file 文件
     * @return FileBaseVO
     */
    @Transactional(rollbackFor = Exception.class)
    public FileBaseVO upload(MultipartFile file) throws IOException {
        // 计算文件md5，如果md5存在，则新建一条数据直接返回
        String md5 = FileMd5Utils.calculateMd5(file.getInputStream());
        FileRecord fileRecord = lambdaQuery().eq(FileRecord::getFileMd5, md5).one();
        if (fileRecord != null) {
            FileRecord newFileRecord = BeanUtils.copyProperties(fileRecord, FileRecord.class);
            newFileRecord.setId(null);
            save(newFileRecord);
            return new FileBaseVO(fileRecord.getId(), fileRecord.getFileName(), fileRecord.getFile());
        }
        FileVO upload = fileService.upload(file);
        return saveFile(upload, md5);
    }

    @Transactional(rollbackFor = Exception.class)
    public List<FileBaseVO> uploadBatch(MultipartFile[] files) throws IOException {
        List<FileBaseVO> fileBaseVOList = new ArrayList<>();
        for (MultipartFile file : files) {
            FileBaseVO fileBaseVO = upload(file);
            fileBaseVOList.add(fileBaseVO);
        }
        return fileBaseVOList;
    }

    /**
     * 文件下载
     *
     * @param idList id集合
     * @return ResponseEntity<Resource>
     */
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Resource> download(List<String> idList) throws IOException {
        List<FileRecord> list = lambdaQuery().in(FileRecord::getId, idList).list();
        if (CollUtil.isNotEmpty(list)) {
            updateDownloadCount(idList);
            return fileService.download(list);
        }
        return null;
    }

    /**
     * 删除文件
     *
     * @param idList id集合
     */
    @Transactional(rollbackFor = Exception.class)
    public void deleteFile(List<String> idList) {
    }

    /**
     * 更新下载次数
     *
     * @param ids id 集合
     */
    private void updateDownloadCount(List<String> ids) {
        lambdaUpdate().in(FileRecord::getId, ids).setSql("downloads = downloads + 1").update();
    }

    /**
     * 保存文件
     *
     * @param upload 文件信息
     * @param md5    md5
     * @return FileBaseVO
     */
    @NotNull
    private FileBaseVO saveFile(FileVO upload, String md5) {
        FileRecord fileRecord = new FileRecord();
        fileRecord.setFileName(upload.getOriginalName());
        fileRecord.setFile(upload.getRelativePath());
        fileRecord.setFileType(FileUtils.getFileType(upload.getRelativePath()));
        fileRecord.setFileSize(upload.getFileSize());
        fileRecord.setFileMd5(md5);
        save(fileRecord);
        return new FileBaseVO(fileRecord.getId(), upload.getOriginalName(), upload.getRelativePath());
    }


}