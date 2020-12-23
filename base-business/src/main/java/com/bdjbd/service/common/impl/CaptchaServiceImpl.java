package com.bdjbd.service.common.impl;

import com.bdjbd.service.common.KaptchaService;
import com.bdjbd.service.sys.SysAdminService;
import com.google.code.kaptcha.Producer;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;

/**
 * @Author: dbc
 * @Date: 2018/9/7
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class CaptchaServiceImpl implements KaptchaService {

    public static final String SESSION_KEY = "SESSION_KEY_IMAGE_CODE";

    @Autowired
    private Producer producer;
    @Autowired
    private SysAdminService adminService;

    /**
     * 获取验证码
     *
     * @param randomStr 随机码
     * @param request
     * @param response
     */
    @Override
    public void getCaptcha(String randomStr, HttpServletRequest request, HttpServletResponse response) {
        try{
            request.setCharacterEncoding("utf-8");
            response.setContentType("text/html;charset=utf-8");
            response.setContentType("image/jpeg");
            response.setDateHeader("Expires", 0);
            response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
            response.addHeader("Cache-Control", "post-check=0, pre-check=0");
            response.setHeader("Pragma", "no-cache");
            String text = producer.createText();
            log.debug("图形验证码内容 --> " + text);
            BufferedImage image = producer.createImage(text);
            adminService.saveImageCode(randomStr, text);
            ServletOutputStream out = response.getOutputStream();
            ImageIO.write(image, "JPEG", out);
            IOUtils.closeQuietly(out);
        }catch (Exception e){
            log.error("验证码生成异常", e);
        }
    }
}
