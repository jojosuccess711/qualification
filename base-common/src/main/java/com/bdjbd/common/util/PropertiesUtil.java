package com.bdjbd.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesUtil {

    public static Properties getProperties(String path) {
        Properties prop = new Properties();
        try {
            InputStream in = PropertiesUtil.class.getResourceAsStream(path);
            BufferedReader bf = new BufferedReader(new InputStreamReader(in, "UTF-8"));//按字符流读取，防止中文乱码
            prop.load(bf);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

    public static String getProperties(String path, String key) {
        return getProperties(path).getProperty(key).trim();
    }

    public static String getConfig(String key) {
        return getProperties("/config.properties").getProperty(key).trim();
    }
}