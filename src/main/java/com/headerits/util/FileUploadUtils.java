package com.headerits.util;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

/**
 * <p>Description: 文件上传工具类 </p>
 * <p>Title: FileUploadUtils </p>
 * <p>Create Time: 2018/7/18 14:04</p>
 *
 * @author: zhongzhipeng
 * @version: 1.0
 */
public class FileUploadUtils {


    /**
     * 防止文件名一致出现文件覆盖情况生成唯一的文件名
     *
     * @param originalFilename 原始文件名
     * @return
     */
    public static String generateFilename(String originalFilename) {
        return UUID.randomUUID().toString().replace("-", "") + getExtTiny(originalFilename);
    }

    /**
     * 防止将图片存储在同一目录,图片多造成访问速度慢生成随机目录
     *
     * @param savePath 文件保存的真实目录
     * @param filename 文件名
     * @return
     */
    public String generateSavePath(String savePath, String filename) {
        int hashcode = filename.hashCode();
        int dir1 = hashcode & 15;
        int dir2 = (hashcode >> 4) & 0xf;

        String savepath = savePath + File.separator + dir1 + File.separator + dir2;
        File file = new File(savepath);
        if (!file.exists()) {
            file.mkdirs();
        }
        return savepath;
    }

    /**
     * 返回文件存储目录在服务器的真实路径
     *
     * @param request HttpServletRequest
     * @param folder  文件夹名
     * @return
     */
    public static String getFileStorage(HttpServletRequest request, String folder) {
        return request.getSession().getServletContext().getRealPath(folder);
    }

    /**
     * 获取名件名的后缀不带.
     *
     * @param filename
     * @return
     */
    public static String getExt(String filename) {
        return filename.substring(filename.lastIndexOf('.') + 1).toLowerCase();
    }

    /**
     * 获取名件名的后缀带.
     *
     * @param filename
     * @return
     */
    public static String getExtTiny(String filename) {
        return filename.substring(filename.lastIndexOf('.'));
    }

    /**
     * 判断支持文件类型 支持返回true,不支持返回false
     *
     * @param originalFilename 原始文件名
     * @return
     */
    public static boolean supportFileType(String originalFilename, List<String> fileTypes) {
        for (String fileType : fileTypes) {
            if (fileType.equalsIgnoreCase(getExt(originalFilename))) {
                return true;
            }
        }
        return false;
    }

    /**
     * 判断上传文件是否是图片类型,支持返回true、不知处返回false
     *
     * @param originalFilename 原始文件名
     * @return
     */
    public static boolean supportImgageType(String originalFilename) {
        return supportFileType(originalFilename, Arrays.asList("jpg", "png", "gif", "bmp", "jpeg"));
    }
}
