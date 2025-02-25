package com.easy.common.util.file;

import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 计算 MD5 值和校验 MD5 值
 *
 * </p>
 *
 * @author Matt
 */
public class FileMd5Utils {
    /**
     * 计算文件InputStream流的MD5值
     *
     * @param inputStream 文件输入流
     * @return MD5值的十六进制字符串
     * @throws IOException 读取文件流时可能抛出的异常
     */
    public static String calculateMd5(InputStream inputStream) throws IOException {
        try {
            // 获取MD5消息摘要实例
            MessageDigest digest = MessageDigest.getInstance("MD5");
            byte[] buffer = new byte[8192];
            int bytesRead;
            // 从输入流中读取数据并更新消息摘要
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                digest.update(buffer, 0, bytesRead);
            }
            // 完成哈希计算，得到MD5字节数组
            byte[] hash = digest.digest();
            // 将字节数组转换为十六进制字符串
            StringBuilder hexString = new StringBuilder();
            for (byte b : hash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            // 理论上不会出现该异常，因为MD5是标准算法
            throw new RuntimeException(e);
        } finally {
            // 关闭输入流
            if (inputStream != null) {
                inputStream.close();
            }
        }
    }

    /**
     * 校验文件InputStream流的MD5值
     *
     * @param inputStream 文件输入流
     * @param expectedMd5 期望的MD5值
     * @return 如果计算得到的MD5值与期望的MD5值相同，则返回true，否则返回false
     * @throws IOException 读取文件流时可能抛出的异常
     */
    public static boolean verifyMd5(InputStream inputStream, String expectedMd5) throws IOException {
        // 计算文件的MD5值
        String calculatedMd5 = calculateMd5(inputStream);
        // 比较计算得到的MD5值与期望的MD5值
        return calculatedMd5.equalsIgnoreCase(expectedMd5);
    }
}