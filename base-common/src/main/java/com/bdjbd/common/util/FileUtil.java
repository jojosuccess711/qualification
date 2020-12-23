package com.bdjbd.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.channels.FileChannel;

/**
  * @className FileUtil
  * @description 文件操作工具类
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public final class FileUtil {

    private static Logger logger = LoggerFactory.getLogger(FileUtil.class);

    public static String DEFAULT_FILE_ENCODING = "utf-8";


    public static boolean fileIsExists(String path) {
        File f = new File(path);
        if (f.isFile()) {
            if (f.exists()) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


    public static boolean createFile(String destFileName) {
        File file = new File(destFileName);
        if (file.exists()) {
            logger.info("创建单个文件" + destFileName + "失败，目标文件已存在");
            return false;
        }
        if (destFileName.endsWith(File.separator)) {
            logger.info("创建单个文件" + destFileName + "失败，目标文件不能为目录");
            return false;
        }
        if (!file.getParentFile().exists()) {
            logger.info("目标文件所在目录不存在，准备创建它。");
            if (!file.getParentFile().mkdirs()) {
                logger.info("创建目标文件所在目录失败！");
                return false;
            }
        }
        // 创建目标文件
        try {
            if (file.createNewFile()) {
                logger.info("创建单个文件" + destFileName + "成功。");
                return true;
            } else {
                logger.info("创建单个文件" + destFileName + "失败。");
                return false;
            }
        } catch (IOException e) {
            logger.info("创建单个文件" + destFileName + "失败。", e);
            return false;
        }
    }

    public static boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (dir.exists()) {
            return false;
        }
        if (!destDirName.endsWith(File.separator)) {
            destDirName = destDirName + File.separator;
        }
        // 创建目录
        if (dir.mkdirs()) {
            logger.info("创建目录" + destDirName + "成功。");
            return true;
        } else {
            logger.info("创建目录" + destDirName + "失败。");
            return false;
        }
    }

    public static String createTempFile(String prefix, String suffix,
                                        String dirName) {
        File tempFile = null;
        if (dirName == null) {
            try {
                tempFile = File.createTempFile(prefix, suffix);
                return tempFile.getCanonicalPath();
            } catch (IOException e) {

                logger.info("创建临时文件失败。" + e.getMessage());
                return null;
            }
        } else {
            File dir = new File(dirName);
            if (!dir.exists()) {
                if (!FileUtil.createDir(dirName)) {
                    logger.info("创建临时文件失败，不能创建临时文件所在的目录。");
                    return null;
                }
            }
            try {
                // 在指定目录下创建临时文件
                tempFile = File.createTempFile(prefix, suffix, dir);
                return tempFile.getCanonicalPath();
            } catch (IOException e) {
                logger.info("创建临时文件失败。" + e.getMessage());
                return null;
            }
        }
    }

    // }}

    // {{file copy

    /**
     * 使用文件通道的方式复制文件
     *
     * @param s_name 源文件
     * @param t_name 复制到的新文件
     */
    public static void fileChannelCopy(String s_name, String t_name) {
        FileInputStream fi = null;
        FileOutputStream fo = null;
        FileChannel in = null;
        FileChannel out = null;
        try {

            File s = new File(s_name);
            File t = new File(t_name);
            if (!s.exists()) {
                logger.error("源文件不存在：" + s_name);
                throw new IllegalStateException("源文件不存在：" + s_name);
            }

            fi = new FileInputStream(s);
            fo = new FileOutputStream(t);
            in = fi.getChannel();// 得到对应的文件通道
            out = fo.getChannel();// 得到对应的文件通道
            in.transferTo(0, in.size(), out);// 连接两个通道，并且从in通道读取，然后写入out通道
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                fi.close();
                in.close();
                fo.close();
                out.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }
    }


    public static String read(String file_name) {
        return read(file_name, DEFAULT_FILE_ENCODING);
    }

    public static String read(String file_name, String encoding) {
        InputStreamReader inputReader = null;
        BufferedReader bufferReader = null;
        InputStream inputStream = null;
        StringBuffer strBuffer = null;

        try {
            inputStream = new FileInputStream(file_name);
            inputReader = new InputStreamReader(inputStream, encoding);
            bufferReader = new BufferedReader(inputReader);

            String line = null;
            strBuffer = new StringBuffer();

            while ((line = bufferReader.readLine()) != null) {
                strBuffer.append(line + "\n");
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            try {
                bufferReader.close();
                inputReader.close();
                inputStream.close();
            } catch (IOException e) {
                logger.error(e.getMessage());
            }
        }

        return strBuffer.toString();
    }

    // 写文件
    public static void write(String name, String content) {
        write(name, content, DEFAULT_FILE_ENCODING);
        logger.info("写入成功");
    }

    public static void write(String name, String content, String encoding) {
        BufferedWriter fw = null;
        try {
            File file = new File(name);
            fw = new BufferedWriter(new OutputStreamWriter(
                    new FileOutputStream(file), encoding)); // 指定编码格式，以免读取时中文字符异常
            fw.write(content);
            fw.flush(); // 全部写入缓存中的内容
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
    }

    /**
     * 下载文件
     *
     * @param request
     * @param response
     * @param storePath 存储路径
     * @param realName  原文件名
     * @throws Exception
     */
    public static void download(HttpServletRequest request, HttpServletResponse response, String storePath, String realName) throws Exception {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        BufferedInputStream bis = null;
        BufferedOutputStream bos = null;
        String contentType = "application/octet-stream";

        String ctxPath = request.getSession().getServletContext().getRealPath("/");

        String downLoadPath = ctxPath + "\\" + storePath;

        long fileLength = new File(downLoadPath).length();

        response.setContentType(contentType);
        response.setHeader("Content-disposition", "attachment; filename="
                + new String(realName.getBytes("utf-8"), "ISO8859-1"));
        response.setHeader("Content-Length", String.valueOf(fileLength));
        bis = new BufferedInputStream(new FileInputStream(downLoadPath));
        bos = new BufferedOutputStream(response.getOutputStream());
        byte[] buff = new byte[2048];
        int bytesRead;
        while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
            bos.write(buff, 0, bytesRead);
        }
        bis.close();
        bos.close();
    }

    /**
     * 文件上传
     * @param file
     * @param filePath
     * @param fileName
     */
    public static void uploadFile(byte[] file, String filePath, String fileName) {
        int index = fileName.lastIndexOf("/");
        String dirPath = filePath;
        if(index > 0){
            dirPath += fileName.substring(0, index);
        }
        File targetFile = new File(dirPath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(filePath + fileName);
            out.write(file);
        } catch (FileNotFoundException e) {
            logger.warn("文件上传异常 FileNotFoundException", e);
        } catch (IOException e) {
            logger.warn("文件上传异常 IOException", e);
        }finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                logger.warn("文件上传异常 IOException", e);
            }
        }
    }
}
