package com.bdjbd.common.util;

import java.security.MessageDigest;

/**
  * @className MD5Util
  * @description MD5 工具类
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public final class MD5Util {

    private static final String SALT = "yy";

    private static final String WECAHT_SALT="yy_aa";

    public static String encode(String password) {
        password = password + SALT;
        return processEncode(password);
    }

    public static void main(String[] args) {
        System.out.println(encode("1234"));
    }

    /**
     * 与微信模块约定的加密模块
     * */
    public static String wechatEncode(String password){
        password = password + WECAHT_SALT;
        return processEncode(password);
    }

    public static boolean wehcatValidation(String str, String token){
        boolean flag = false;
        if(wechatEncode(str).equals(token)){
            flag = true;
        }
        return flag;
    }

    public static String processEncode(String password) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        char[] charArray = password.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16) {
                hexValue.append("0");
            }

            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * MD5 编码
     *
     * @param origin      字符串
     * @param charsetname 编码名称
     * @return MD5 编码后的字符串
     */
    public static String MD5Encode(String origin, String charsetname) {
        String resultString = null;
        try {
            resultString = new String(origin);
            MessageDigest md = MessageDigest.getInstance("MD5");
            if (charsetname == null || "".equals(charsetname))
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes()));
            else
                resultString = byteArrayToHexString(md.digest(resultString
                        .getBytes(charsetname)));
        } catch (Exception exception) {
        }
        return resultString;
    }

    /**
     * byte数组 转成 十六进制字符串
     *
     * @param b
     * @return 十六进制字符串
     */
    private static String byteArrayToHexString(byte b[]) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++)
            resultSb.append(byteToHexString(b[i]));

        return resultSb.toString();
    }

    /**
     * byte 转成  十六进制
     *
     * @param b
     * @return 十六进制 字符串
     */
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n += 256;
        int d1 = n / 16;
        int d2 = n % 16;
        return hexDigits[d1] + hexDigits[d2];
    }

    /**
     * 十六进制 字符数组
     */
    private static final String hexDigits[] = {"0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
}
