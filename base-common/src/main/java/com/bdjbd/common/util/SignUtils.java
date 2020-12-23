package com.bdjbd.common.util;

import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
  * @className SignUtils
  * @description 签名工具类
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public final class SignUtils {

    /**
     * MD5 签名方式
     * @param map
     * @param key
     * @return
     */
    public static String signMD5Tree(Map<String, Object> map, String key){
        TreeMap<String, Object> treeMap = new TreeMap<>();
        treeMap.putAll(map);
        return signMD5Handler(treeMap, key);
    }

    /**
     * MD5 签名方式
     * @param map
     * @param key
     * @return
     */
    public static String signMD5Default(Map<String, Object> map, String key){
        return signMD5Handler(map, key);
    }

    private static String signMD5Handler(Map<String, Object> map, String key){
        StringBuilder sb = new StringBuilder();
        for(Iterator<Map.Entry<String, Object>> iterator = map.entrySet().iterator(); iterator.hasNext();){
            Map.Entry<String, Object> next = iterator.next();
            sb.append(next.getKey());
            sb.append("=");
            sb.append(map.get(next.getKey()));
            sb.append("&");
        }
        sb.append("key=");
        sb.append(key);
        return MD5Util.MD5Encode(sb.toString(), "utf-8");
    }
}
