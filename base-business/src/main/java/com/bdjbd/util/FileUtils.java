package com.bdjbd.util;

import com.bdjbd.Message;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Random;

@Slf4j
public class FileUtils {

    /**
     * MultipartFile 转 File
     * @throws Exception
     */
    public static File multipartFileToFile(MultipartFile file) throws Exception {
        File toFile = null;
        if ("".equals(file) || file.getSize() <= 0) {
            file = null;
        }
        else {
            InputStream ins = null;
            ins = file.getInputStream();
            toFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
            inputStreamToFile(ins, toFile);
            ins.close();
        }
        return toFile;
    }

    /**
     * 保存文件到指定的路径
     * @return
     */
    public static Message saveFile(MultipartFile file, String filePath, String
            userId, String groupId, String parameterId, String orders) {
        if ("".equals(file) || file.getSize() <= 0) {
            return Message.error("上传文件内容为空");
        }
        else {
            mkDir(filePath);
            mkDir(filePath + File.separator + userId);
            mkDir(filePath + File.separator + userId + File.separator + groupId);
            mkDir(filePath + File.separator + userId + File.separator +
                    groupId + File.separator + orders);
            String toPath = filePath + File.separator + userId + File
                    .separator + groupId + File.separator + orders;
            return saveFiletoDisk(file, toPath);
        }
    }

    public static Message saveFile(MultipartFile file, String filePath, String
            userId) {
        mkDir(filePath);
        mkDir(filePath );
        mkDir(filePath+ File.separator + userId);
        String toPath = filePath + File.separator + userId;
        Message message = saveFiletoDisk(file, toPath);
        if (!message.isSuccess()) {
            return message;
        }
        else {
            return Message.success("/statics/userImgs/" + userId + "/" + file
                    .getOriginalFilename());
        }
    }

    private static Message saveFiletoDisk(MultipartFile file, String toPath) {
        File toFile;
        InputStream ins = null;
        try {
            ins = file.getInputStream();
            toFile = new File(toPath + File.separator + file
                    .getOriginalFilename());
            inputStreamToFile(ins, toFile);
            ins.close();
            return Message.success("上传成功", toPath + File.separator + file.getOriginalFilename());
        } catch (IOException e) {
            return Message.error("文件上传异常");
        }
    }

    private static void mkDir(String path) {
        File myPath = new File(path);
        if (!myPath.exists()) {//若此目录不存在，则创建之// 这个东西只能简历一级文件夹，两级是无法建立的。。。。。
            myPath.mkdir();
        }
    }

    //获取流文件
    private static void inputStreamToFile(InputStream ins, File file) {
        try {
            OutputStream os = new FileOutputStream(file);
            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = ins.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
            os.close();
            ins.close();
        } catch (Exception e) {
            log.error("", e);
        }
    }

    /**
     * 删除本地临时文件
     */
    public static void delteTempFile(File file) {
        if (file != null) {
            File del = new File(file.toURI());
            del.delete();
        }
    }

    /**
     * 文件上传远程文件服务器
     * @return String 文件保存地址
     * @throws IOException
     */
    public static String uploadForFck(MultipartFile mf, String param)
            throws IOException {
        byte[] mfs = mf.getBytes();
        //定义上传后的文件名字
        StringBuilder fileName = new StringBuilder(new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()));
        Random random = new Random();
        for (int i = 0; i < 3; i++) {
            fileName.append(random.nextInt(10));
        }
        //获得后缀名
        String oriFileName = mf.getOriginalFilename();
        if (oriFileName == null) {
            return null;
        }
        String suffix = oriFileName.substring(oriFileName.lastIndexOf("."));
        //要上传文件的绝对路径
        String realPath = "http://gftest.chabaobao.net/fileserver/" +
                param + "_" + fileName + suffix;
        //由于我们需要在不同主机上上传不能直接通过流的方式写文件
        //创建Jersey客户端
        Client client = Client.create();

        //指定要上传的具体的地址,参数就是要上传的图片的绝对路径
        WebResource wr = client.resource(realPath);
        //文件的上传到文件主机上
        wr.put(mfs);
        return realPath;
    }

    /**
     * 读取字节输入流内容
     * @return
     */
    public static byte[] readInputStream(InputStream is) {
        ByteArrayOutputStream writer = new ByteArrayOutputStream();
        byte[] buff = new byte[1024 * 2];
        int len = 0;
        try {
            while ((len = is.read(buff)) != -1) {
                writer.write(buff, 0, len);
            }
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return writer.toByteArray();
    }

    /**
     * 模拟 http post请求
     * @return
     * @throws IOException
     */
    public static InputStream dowloadFile(String httpUrl) throws Exception {
        URL url;
        try {
            url = new URL(httpUrl);
        } catch (MalformedURLException e1) {
            throw new Exception();
        }
        URLConnection conn = url.openConnection();
        return conn.getInputStream();
    }

    /**
     * 判断文件大小
     * @param len 文件长度
     * @param size 限制大小
     * @param unit 限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
        //        long len = file.length();
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        }
        else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        }
        else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        }
        else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }
}
