package com.bdjbd.util;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description:
 * @Author zekunsong
 * @CreateDate 2020/4/27
 * @Version V1.0
 */
@Slf4j
public class ClassTransferUtil {
//    private static final ClassTransferUtil service = new ClassTransferUtil();
//
//
//    private ClassTransferUtil() {
//    }
//
//    public static ClassTransferUtil example() {
//        return service;
//    }

    public static Object cloneAttribute(Object clone, Object beCloned) {
        Field[] fieldClone = null;
        Field[] fieldBeCloned = null;
        Map<String, Field> map = new HashMap<String, Field>();
        try {
            Class<?> classClone = clone.getClass();
            Class<?> classBecloned = beCloned.getClass();

            fieldClone = classClone.getDeclaredFields();
            fieldBeCloned = classBecloned.getDeclaredFields();

            for (int t = 0; t < fieldBeCloned.length; t++) {
                if(!"serialVersionUID".equals(fieldBeCloned[t].getName())){
                    map.put(fieldBeCloned[t].getName(), fieldBeCloned[t]);
                }
            }

            for (int i = 0; i < fieldClone.length; i++) {
                String fieldCloneName = fieldClone[i].getName();
                Field fie = map.get(fieldCloneName);
                if (fie != null) {
                    Method method1 = classClone.getMethod(getMethodName(fieldCloneName));
                    Method method2 = classBecloned.getMethod(setMethodName(fieldCloneName), fie.getType());
                    method2.invoke(beCloned, method1.invoke(clone));
                }
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        } finally {
            fieldClone = null;
            fieldBeCloned = null;
            map.clear();
        }
        return beCloned;
    }

    private static String getMethodName(String fieldName) {
        String head = fieldName.substring(0, 1).toUpperCase();
        String tail = fieldName.substring(1);
        return "get" + head + tail;
    }

    private static String setMethodName(String fieldName) {
        String head = fieldName.substring(0, 1).toUpperCase();
        String tail = fieldName.substring(1);
        return "set" + head + tail;
    }
}
