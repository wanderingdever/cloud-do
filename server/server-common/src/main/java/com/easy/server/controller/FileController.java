package com.easy.server.controller;


import cn.dev33.satoken.annotation.SaCheckPermission;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.common.core.dto.IdListDTO;
import com.easy.common.core.exception.CustomizeException;
import com.easy.common.web.annotation.Wrap;
import com.easy.server.bean.dto.FileQueryDTO;
import com.easy.server.bean.entity.FileRecord;
import com.easy.server.bean.vo.FileBaseVO;
import com.easy.server.service.FileRecordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.dromara.hutool.core.collection.CollUtil;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 文件服务
 * </p>
 *
 * @author Matt
 */
@RestController
@RequestMapping("/file")
@Tag(name = "文件服务")
public class FileController {

    private final FileRecordService fileRecordService;

    public FileController(FileRecordService fileRecordService) {
        this.fileRecordService = fileRecordService;
    }

    /**
     * 获取文件分页
     */
    @PostMapping(value = "/page")
    @Operation(summary = "获取文件分页")
    @SaCheckPermission(value = "system.file.page")
    public Page<FileRecord> page(@RequestBody FileQueryDTO dto) {
        return fileRecordService.filePage(dto);
    }

    /**
     * 文件查询
     */
    @PostMapping(value = "/query")
    @Operation(summary = "文件查询")
    public List<FileRecord> fileQuery(@RequestBody FileQueryDTO vo) {
        return fileRecordService.fileQuery(vo);
    }


    /**
     * 上传文件
     *
     * @param file 文件
     * @return 文件相对路径+名字
     */
    @PostMapping(value = "/upload")
    @Operation(summary = "单个文件上传")
    public FileBaseVO upload(@RequestPart(name = "file") MultipartFile file) throws IOException {
        return fileRecordService.upload(file);
    }

    /**
     * 多文件上传
     *
     * @param files 文件列表
     * @return 文件相对路径+名字列表
     */
    @PostMapping(value = "/upload_batch")
    @Operation(summary = "多个文件上传")
    public List<FileBaseVO> uploadBatch(@RequestPart(name = "files") MultipartFile[] files) throws IOException {
        return fileRecordService.uploadBatch(files);
    }

    /**
     * 文件下载
     */
    @PostMapping(value = "/download")
    @Operation(summary = "文件下载")
    @Wrap(disabled = true)
    public ResponseEntity<Resource> download(@RequestBody IdListDTO dto) throws IOException {
        if (CollUtil.isEmpty(dto.getIdList())) {
            throw new CustomizeException("请选择文件");
        }
        return fileRecordService.download(dto.getIdList());
    }


    /**
     * 删除文件
     *
     * @param dto 文件名字集合
     */
    @PostMapping(value = "/delete")
    @Operation(summary = "删除文件")
    public String delete(@RequestBody IdListDTO dto) {
        fileRecordService.deleteFile(dto.getIdList());
        return "";
    }

}