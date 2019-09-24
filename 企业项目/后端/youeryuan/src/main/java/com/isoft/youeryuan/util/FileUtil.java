package com.isoft.youeryuan.util;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FileUtil {
    /**
     * 获取文件扩展名，包括"."
     * @param fileName
     * @return
     */
    public static String getExtName(String fileName) {
        return  fileName.substring(fileName.lastIndexOf(".")) ;
    }

    /**
     * 检测给定目录是否存在，不存在，则创建该目录
     */
    public static boolean mkDir(String path) {
        boolean result = false ;
        File f = new File(path);
        if (!f.exists() || ! f.isDirectory())   {
            result = f.mkdirs();
        }
        return result ;
    }

    /**
     * 文件上传
     * @param file   要上传的文件
     * @param id      标识id
     * @param request
     * @return 上传文件，文件存储uri，如果返回null表示上传失败
     */
    public static String uploadFile(MultipartFile file, Integer id, String uploadPath , HttpServletRequest request) {
        boolean result = false ;
        String photoUri = null ;   // 文件保存位置uri

        // 获取文件名
        String fname = file.getOriginalFilename();
        System.out.println("文件名 : " + fname);
        // 获取文件扩展名，扩展名包括 "."
        String extName = fname.substring(fname.lastIndexOf("."));
        // 构造文件新名字
        String idStr = "" ;
        if(null != id) {
            idStr = String.valueOf(id) ;
        }
        String newFileName = new SimpleDateFormat("yyyyMMddHHmmssSSSS").format(new Date()) + "_" + idStr + extName;
        System.out.println("构造文件名" + newFileName);
        // 获得项目的路径
        // 获取ServletContext对象
        ServletContext sc = request.getServletContext();
        // 获取上传位置物理路径
        String path = sc.getRealPath(uploadPath) + "/"; // 设定文件保存的目录

        // 检测目录是否存在，不存在，创建该目录
        File f = new File(path);
        if (!f.exists() || !f.isDirectory()) {
            f.mkdirs();
        }

        if (!file.isEmpty()) {
            try {
                // 保存文件
                file.transferTo(new File(path + newFileName));
                System.out.println(path + newFileName + "--ok");
                // http 获取协议
                String scheme = request.getScheme();
                // localhost 获取ip
                String serverName = request.getServerName();
                //  获取端口
                int serverPort = request.getServerPort();
                // 获取发布后项目名称
                String projectName = request.getContextPath();
                // 获取项目uri
                String s = scheme + "://" + serverName + ":" + serverPort + "/" +  projectName ;
                // 获取文件uri
                photoUri = s + "/"+uploadPath+"/" + newFileName ;
                System.out.println(photoUri);
                result = true ;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return result ? photoUri : null ;
    }
}















