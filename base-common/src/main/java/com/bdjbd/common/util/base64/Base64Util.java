package com.bdjbd.common.util.base64;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author zhuzhe
 * @date 2019/8/5 16:01
 * @email zhuzhe_mail@163.com
 */
@Slf4j
public class Base64Util {

    public static MultipartFile base64ToMultipart(String base64) {
        try {
            String[] baseStr = base64.split(",");

            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = new byte[0];
            b = decoder.decodeBuffer(baseStr[1]);

            for(int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {
                    b[i] += 256;
                }
            }

            return new BASE64DecodedMultipartFile(b, baseStr[0]);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }
    public static InputStream base64ToInputStream(String base64) {
        ByteArrayInputStream stream;

        BASE64Decoder decoder = new BASE64Decoder();

        byte[] bytes = new byte[1024];

        try {
            bytes = decoder.decodeBuffer(base64);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ByteArrayInputStream(bytes);
    }
}
