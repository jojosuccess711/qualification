package com.bdjbd.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dbc
 * @version 1.0
 * @className MapUtil
 * @description TODO
 * @date 2019/10/18
 **/
public final class MapUtil {

    public static <K, V> Map<K, V> put(K k, V v){
        Map<K, V> result = new HashMap<>();
        result.put(k, v);
        return result;
    }
}
