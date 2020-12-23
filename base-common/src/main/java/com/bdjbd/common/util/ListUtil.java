package com.bdjbd.common.util;

import java.util.ArrayList;
import java.util.List;

/**
  * @className ListUtil
  * @description List 集合工具类
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public final class ListUtil {

    /**
     * 将指定类型对象封装到List集合
     * @param t
     * @param <T>
     * @return
     */
    public static <T> List<T> addToList(T... t){
        List<T> list = new ArrayList<>();
        for(T item : t){
            if(item != null)
                list.add(item);
        }
        return list;
    }
}
