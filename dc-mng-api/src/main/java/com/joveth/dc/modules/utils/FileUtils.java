package com.joveth.dc.modules.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Arrays;

/**
 * @author QiaoWei
 * @date 2022-01-24 17:13
 */
@Slf4j
public class FileUtils {

    private static final String[] suffixWhiteList = {"PNG", "JPEG", "JPG", "GIF", "BMP", "ICO", "XLS", "XLSX"};

    /**
     * 获取springboot所在目录
     */
    public static String getPath() {
        try {
            ApplicationHome h = new ApplicationHome(FileUtils.class);
            File jarF = h.getSource();
            return jarF.getParentFile().getAbsolutePath();
        } catch (Exception e) {
            log.error("文件路径获取失败:{}", e.getMessage());
        }
        return null;
    }

    public static String getPath(String dir) {
        String path = "";
        try {
            ApplicationHome h = new ApplicationHome(FileUtils.class);
            File jarF = h.getSource();
            path = jarF.getParentFile().getAbsolutePath().concat(File.separator).concat(dir);
        } catch (Exception e) {
            log.error("文件路径获取失败:{}", e.getMessage());
        }
        File p = new File(path);
        if (!p.exists()) {
            // 如果目录不存在
            p.mkdir();
        }
        return path;
    }

    /**
     * 判断是否是安全的文件
     * 防止恶意文件上传
     */
    public static boolean isSafeFile(String fileName) {
        int lastIndex = fileName.lastIndexOf(".");
        if (lastIndex < 0) {
            return true;
        }
        String suffix = fileName.substring(lastIndex + 1);
        return Arrays.stream(suffixWhiteList).anyMatch(x -> x.equalsIgnoreCase(suffix));
    }

    public static boolean isSafeFile(File file) {
        return isSafeFile(file.getName());
    }

    public static boolean isSafeFile(MultipartFile multipartFile) {
        return isSafeFile(multipartFile.getName());
    }
}
