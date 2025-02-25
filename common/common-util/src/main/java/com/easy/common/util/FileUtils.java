package com.easy.common.util.file;


import com.easy.common.util.lang.DateUtils;
import com.easy.common.util.lang.StringUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import org.dromara.hutool.core.data.id.IdUtil;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 * 文件工具
 * </p>
 *
 * @author Matt
 */
public class FileUtils {

    public static final String IMAGE_PNG = "image/png";
    public static final String IMAGE_JPG = "image/jpg";
    public static final String IMAGE_JPEG = "image/jpeg";
    public static final String IMAGE_BMP = "image/bmp";
    public static final String IMAGE_GIF = "image/gif";
    public static final String[] IMAGE_EXTENSION = {"bmp", "gif", "jpg", "jpeg", "png"};
    public static final String[] FLASH_EXTENSION = {"swf", "flv"};
    public static final String[] OFFICE_EXTENSION = {"doc", "docx", "xls", "xlsx", "ppt", "pptx"};
    public static final String[] MEDIA_EXTENSION = {"swf", "flv", "mp3", "wav", "wma", "wmv", "mid", "avi", "mpg",
            "asf", "rm", "rmvb"};
    public static final String[] VIDEO_EXTENSION = {"mp4", "avi", "rmvb"};
    public static final String[] DEFAULT_ALLOWED_EXTENSION = {
            // 图片
            "bmp", "gif", "jpg", "jpeg", "png",
            // word excel powerpoint
            "doc", "docx", "xls", "xlsx", "ppt", "pptx", "html", "htm", "txt",
            // 压缩文件
            "rar", "zip", "gz", "bz2",
            // 视频格式
            "mp4", "avi", "rmvb",
            // pdf
            "pdf"};

    /**
     * 获取文件类型
     * <p>
     * 例如: .txt, 返回: txt
     *
     * @param file 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(File file) {
        if (null == file) {
            return StringUtils.EMPTY;
        }
        return getFileType(file.getName());
    }

    /**
     * 获取文件类型
     * <p>
     * 例如: a.txt, 返回: txt
     *
     * @param fileName 文件名
     * @return 后缀（不含".")
     */
    public static String getFileType(String fileName) {
        int separatorIndex = fileName.lastIndexOf(".");
        if (separatorIndex < 0) {
            return "";
        }
        return fileName.substring(separatorIndex + 1).toLowerCase();
    }

    /**
     * 按照时间格式 组装文件路径
     * eg: 2022/08/11/15151532222_测试图片.png
     *
     * @param file 文件
     * @return 文件路径+文件名全名
     */
    public static String getTimePathFileName(MultipartFile file) {
        // 获取文件名字
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            fileName = getFileName(fileName);
        }
        // 拆分文件名字
        fileName = DateUtils.datePath() + "/" + DateUtils.timeNum() + "_" + fileName;
        return fileName;
    }

    /**
     * 按照时间格式 组装文件路径
     * eg: 2022/08/11/15151532222_测试图片.png
     *
     * @param fileName 文件名字
     * @return 文件路径+文件名全名
     */
    public static String getTimePathFileName(String fileName) {
        // 拆分文件名字
        return DateUtils.datePath() + "/" + DateUtils.timeNum() + "_" + fileName;
    }

    public static String getFileName(String fileName) {
        int i = fileName.lastIndexOf('.');
        if (i > 0) {
            fileName = IdUtil.fastSimpleUUID() + "." + fileName.substring(i + 1);
        }
        return fileName;
    }

    /**
     * 获取 InputStream 的文件大小。
     *
     * @param inputStream 输入流
     * @return 文件大小（字节）
     * @throws IOException 如果读取流时发生错误
     */
    public static long getInputStreamSize(InputStream inputStream) throws IOException {
        long size = 0;
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            size += bytesRead;
        }

        return size;
    }

    /**
     * 获取文件扩展
     *
     * @param contentType 内容类型
     * @return String
     */
    public static String getExtension(String contentType) {
        return switch (contentType) {
            case IMAGE_PNG -> "png";
            case IMAGE_JPG -> "jpg";
            case IMAGE_JPEG -> "jpeg";
            case IMAGE_BMP -> "bmp";
            case IMAGE_GIF -> "gif";
            default -> "";
        };
    }

    /**
     * Description: 判断Cos服务文件上传时文件的contentType
     *
     * @param filename 文件名字
     * @return String
     */
    public static String getContentType(String filename) {
        String filenameExtension = getExtension(filename);
        String bmp = "bmp";
        if (bmp.equalsIgnoreCase(filenameExtension)) {
            return "image/bmp";
        }
        String gif = "gif";
        if (gif.equalsIgnoreCase(filenameExtension)) {
            return "image/gif";
        }
        String jpeg = "jpeg";
        String jpg = "jpg";
        String png = "png";
        if (jpeg.equalsIgnoreCase(filenameExtension) || jpg.equalsIgnoreCase(filenameExtension)
                || png.equalsIgnoreCase(filenameExtension)) {
            return "image/jpeg";
        }
        String html = "html";
        if (html.equalsIgnoreCase(filenameExtension)) {
            return "text/html";
        }
        String txt = "txt";
        if (txt.equalsIgnoreCase(filenameExtension)) {
            return "text/plain";
        }
        String vsd = "vsd";
        if (vsd.equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.visio";
        }
        String pptx = "pptx";
        String ppt = "ppt";
        if (pptx.equalsIgnoreCase(filenameExtension) || ppt.equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.ms-powerpoint";
        }
        String docx = ".docx";
        String doc = ".doc";
        if (docx.equalsIgnoreCase(filenameExtension) || doc.equalsIgnoreCase(filenameExtension)) {
            return "application/msword";
        }
        String xml = "xml";
        if (xml.equalsIgnoreCase(filenameExtension)) {
            return "text/xml";
        }
        String mp4 = ".mp4";
        if (mp4.equalsIgnoreCase(filenameExtension)) {
            return "application/octet-stream";
        }
        String pdf = ".pdf";
        if (pdf.equalsIgnoreCase(filenameExtension)) {
            // 使用流的形式进行上传，防止下载文件的时候访问url会预览而不是下载。  return "application/pdf";
            return "application/octet-stream";
        }
        String xls = ".xls";
        String xlsx = ".xlsx";
        if (xls.equalsIgnoreCase(filenameExtension) || xlsx.equalsIgnoreCase(filenameExtension)) {
            return "application/vnd.ms-excel";
        }
        String mp3 = ".mp3";
        if (mp3.equalsIgnoreCase(filenameExtension)) {
            return "audio/mp3";
        }
        String wav = ".wav";
        if (wav.equalsIgnoreCase(filenameExtension)) {
            return "audio/wav";
        }
        return "image/jpeg";
    }


    /**
     * 获取文件流
     *
     * @param inStream
     * @return
     * @throws IOException
     */
    private static byte[] readInputStream(InputStream inStream) throws IOException {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        // 每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len;
        // 使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            // 用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        // 关闭输入流
        inStream.close();
        // 把outStream里的数据写入内存
        return outStream.toByteArray();
    }

    /**
     * 流转文件
     *
     * @param ins  文件流
     * @param file 文件
     */
    public static void inputStreamToFile(InputStream ins, File file) throws Exception {
        OutputStream os = new FileOutputStream(file);
        int bytesRead;
        byte[] buffer = new byte[8192];
        while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
            os.write(buffer, 0, bytesRead);
        }
        os.close();
        ins.close();
    }

    public static byte[] convertPngToPdf(byte[] pngBytes, String pngName) throws IOException {
        // 创建PDF文档
        PDDocument document = new PDDocument();
        // 加载PNG图片
        PDImageXObject pdImage = PDImageXObject.createFromByteArray(document, pngBytes, pngName);

        PDPage page = new PDPage(PDRectangle.A4);
        document.addPage(page);
        // 计算图片在页面上的居中位置
        float imgWidth = pdImage.getWidth();
        float imgHeight = pdImage.getHeight();
        float pageWidth = page.getMediaBox().getWidth();
        float pageHeight = page.getMediaBox().getHeight();

        // 计算缩放比例
        float scaleX = pageWidth / imgWidth;
        float scaleY = pageHeight / imgHeight;
        // 等比缩放
        float scale = Math.min(scaleX, scaleY);

        // 计算绘制起始位置（居中）
        float x = (pageWidth - imgWidth * scale) / 2;
        float y = (pageHeight - imgHeight * scale) / 2;
        // 添加图片到PDF页面
        PDPageContentStream contentStream = new PDPageContentStream(document, page);
        // 获取图片对象
        contentStream.drawImage(pdImage, x, y, imgWidth * scale, imgHeight * scale);
        contentStream.close();
        // 将PDF文档写入到ByteArrayOutputStream
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        document.save(byteArrayOutputStream);
        document.close();
        // 返回PDF文档的字节数组
        return byteArrayOutputStream.toByteArray();
    }

    /**
     * 封装文件响应实体
     *
     * @param filename    文件名
     * @param inputStream 文件字节流
     * @return ResponseEntity<Resource>
     */
    public static ResponseEntity<Resource> getResourceResponseEntity(String filename, InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, bytesRead);
        }
        // 解决下载文件时文件名乱码问题
        String downloadFileName = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        // 创建ByteArrayResource对象
        Resource resource = new ByteArrayResource(baos.toByteArray()) {
            @Override
            public String getFilename() {
                return downloadFileName; // 提供文件名
            }
        };
        // 设置HTTP响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFileName);
        // 创建ResponseEntity对象并返回
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(baos.toByteArray().length)
                .body(resource);
    }

    /**
     * 封装文件响应实体
     *
     * @param filename 文件名
     * @param baos     文件字节数组输出流
     * @return
     */
    public static ResponseEntity<Resource> getResourceResponseEntity(String filename, ByteArrayOutputStream baos) {
        // 解决下载文件时文件名乱码问题
        String downloadFileName = URLEncoder.encode(filename, StandardCharsets.UTF_8);
        // 创建ByteArrayResource对象
        Resource resource = new ByteArrayResource(baos.toByteArray()) {
            @Override
            public String getFilename() {
                return downloadFileName; // 提供文件名
            }
        };
        // 设置HTTP响应头
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + downloadFileName);
        // 创建ResponseEntity对象并返回
        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(baos.toByteArray().length)
                .body(resource);
    }

    /**
     * 压缩文件
     *
     * @param zipOutputStream 压缩文件输出流
     * @param fileName        文件名
     * @param inputStream     文件输入流
     */
    public static void zipFile(ZipOutputStream zipOutputStream, String fileName, InputStream inputStream) throws IOException {
        ZipEntry zipEntry = new ZipEntry(fileName);
        zipOutputStream.putNextEntry(zipEntry);
        byte[] bytes = new byte[1024];
        int length;
        while ((length = inputStream.read(bytes)) >= 0) {
            zipOutputStream.write(bytes, 0, length);
        }
        zipOutputStream.closeEntry();
        inputStream.close();
    }

    /**
     * 将 流 转换为byte
     *
     * @param inStream 文件流
     * @return byte[]
     */
    public static byte[] input2byte(InputStream inStream) {
        try (ByteArrayOutputStream swapStream = new ByteArrayOutputStream()) {
            byte[] buff = new byte[inStream.available()];
            while (inStream.read(buff) != -1) {
                swapStream.write(buff);
            }
            return swapStream.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}