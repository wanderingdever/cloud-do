package com.easy.server.service;

import com.easy.server.bean.entity.FileRecord;
import com.easy.server.bean.vo.FileVO;
import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * 文件接口
 * </p>
 *
 * @author Matt
 */
public interface FileService {

    /**
     * 上传单个文件
     *
     * @param file 文件
     * @return FileVO 上传信息
     */
    FileVO upload(MultipartFile file) throws IOException;


    /**
     * 上传文件
     *
     * @param inputStream 文件流
     * @param fileName    文件名
     * @return FileVO 上传信息
     */
    FileVO upload(InputStream inputStream, String fileName) throws IOException;

    /**
     * 批量上传文件
     *
     * @param files 文件集合
     * @return List<FileVO> 上传信息
     */
    List<FileVO> uploadBatch(MultipartFile[] files);

    /**
     * 下载文件
     *
     * @param fileList 文件集合
     * @return 字节流
     */
    ResponseEntity<Resource> download(List<FileRecord> fileList) throws IOException;

    /**
     * 删除文件
     *
     * @param fileList 文件信息集合
     * @return 操作结果
     */
    String deleteFile(List<FileRecord> fileList);

}