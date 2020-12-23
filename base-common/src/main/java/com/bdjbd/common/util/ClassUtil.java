package com.bdjbd.common.util;

import com.alibaba.fastjson.JSONObject;
import com.bdjbd.common.annotation.MethodInfo;
import com.bdjbd.common.annotation.Protocol;
import com.bdjbd.common.definition.ConstantDenfinition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Method;
import java.util.*;

/**
  * @className ClassUtil
  * @description 反射工具类
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Slf4j
public final class ClassUtil {

    /**
     * 将对象转换为 map  MethodInfo
     *
     * @param thisObj
     * @param isContainsClass 是否包含 getClass
     * @return map
     */
    @SuppressWarnings("rawtypes")
    public static Map<String, Object> beanToMap(Object thisObj, Boolean isContainsClass) {
        SortedMap<String, Object> map = new TreeMap<String, Object>();
        Class c;
        try {
            c = Class.forName(thisObj.getClass().getName());
            Method[] m = c.getDeclaredMethods();
            Method method;
            for (int i = 0; i < m.length; i++) {
                method = m[i];
                if (method.getName().startsWith("get")) {
                    if (!isContainsClass && method.getName().contains("Class")) {
                        continue;
                    }
                    try {
                        Object value = method.invoke(thisObj);
                        if (value != null) {
                            String key;
                            MethodInfo methodInfo = method.getAnnotation(MethodInfo.class);
                            if (methodInfo != null) {
                                key = methodInfo.alias();
                            } else {
                                key = StringUtils.uncapitalize(method.getName().substring(3));
                            }
                            map.put(key, value);
                        }
                    } catch (Exception e) {
                        log.warn("error:" + method.getName());
                    }
                }
            }
        } catch (Exception e) {
            log.warn("beanToMap error [MethodInfo] info ==>" + JSONObject.toJSONString(thisObj), e);
        }
        return map;
    }

    /**
     * 将对象转换为 map (只处理包含注解的get属性)
     *
     * @param o
     * @return map
     */
    public static Map<String, Object> beanToMapProtocol(Object o, String... ignores) {
        SortedMap<String, Object> map = new TreeMap<String, Object>();
        try {
            Class<?> c = Class.forName(o.getClass().getName());
            Method[] m = c.getDeclaredMethods();
            Method method;
            for (int i = 0; i < m.length; i++) {
                method = m[i];
                if (method.getName().startsWith("get")) {
                    try {
                        Protocol methodInfo = method.getAnnotation(Protocol.class);
                        if (methodInfo != null) {
                            String key = methodInfo.alias();
                            if (StringUtils.isBlank(key)) {
                                key = StringUtils.uncapitalize(method.getName().substring(3));
                            }
                            if (ignores.length > 0 && ArrayUtils.contains(ignores, key)) {
                                continue;
                            }
                            Object value = method.invoke(o);
                            if (value != null) {
                                if (value instanceof Collection) {
                                    List<Map<String, Object>> list = new ArrayList<>();
                                    Collection<?> coll = (Collection<?>) value;
                                    for (Iterator<?> iterator = coll.iterator(); iterator.hasNext(); ) {
                                        list.add(beanToMap(true, iterator.next()));
                                    }
                                    value = list;
                                }
                                map.put(key, value);
                            }
                        }
                    } catch (Exception e) {
//						System.out.println("error:" + method.getName());
                    }
                }
            }
        } catch (Exception e) {
            log.warn("beanToMap error [Protocol] info ==>" + JSONObject.toJSONString(o), e);
        }
        return map;
    }

    /**
     * 动态bean属性构建 Map
     *
     * @param fields 属性数组
     * @return 动态构建属性Map
     */
    public static Map<String, Class<String>> getDynamicBeanPropertyMap(String[] fields) {
        Map<String, Class<String>> propertyMap = new HashMap<String, Class<String>>();
        for (String field : fields) {
            propertyMap.put(field, getString());
        }
        return propertyMap;
    }

    /**
     * 动态bean属性值构建 Map
     *
     * @param obj    对象
     * @param fields 属性数组
     * @return 动态构建属性值 Map
     */
    public static Map<String, String> getDynamicBeanValueMap(Object obj, String[] fields, Integer count, String[]... listProperty) {
        Map<String, String> valueMap = new LinkedHashMap<String, String>();
        Object curObj;
        for (String field : fields) {
            if (field == null) {
                continue;
            }
            curObj = ReflectionUtils.getFieldValueByNames(field, obj);
            if (curObj instanceof Date) {
                valueMap.put(field, curObj != null ? DateUtil.formatYMDHMS((Date) curObj) : "");
            } else if (curObj instanceof List<?>) {
                List<Object> list = (List<Object>) curObj;
                StringBuilder sb = new StringBuilder();
                Object o;
                Map<String, String> tempMap;
                for (Iterator<Object> iterator = list.iterator(); iterator.hasNext(); ) {
                    o = iterator.next();
                    tempMap = getDynamicBeanValueMap(o, listProperty[count], count, listProperty);
                    Integer tempMapCount = 0;
                    for (Iterator<String> iterator2 = tempMap.keySet().iterator(); iterator2.hasNext(); ) {
                        sb.append(tempMap.get(iterator2.next()));
                        if (tempMap.size() > ++tempMapCount) {
                            sb.append(" *");
                        }
                    }
                    sb.append(ConstantDenfinition.STR_SPACE);
                }
                valueMap.put(field, sb.toString());
                count++;
            } else {
                valueMap.put(field, (curObj == null) ? "" : curObj.toString());
            }
        }
        return valueMap;
    }

    /**
     * Class - Integer
     *
     * @return
     */
    public static Class<?> getInteger() {
        try {
            return (Class<?>) Class.forName("java.lang.Integer");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Class - Long
     *
     * @return
     */
    public static Class<Long> getLong() {
        try {
            return (Class<Long>) Class.forName("java.lang.Long");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Class - String
     *
     * @return
     */
    public static Class<String> getString() {
        try {
            return (Class<String>) Class.forName("java.lang.String");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 替换 属性名中的 [.]
     *
     * @param key
     * @return 替换为下划线的属性名
     */
    public static String replacePoint(String key) {
        return key.contains(".") ? key.replace(".", "_") : key;
    }

    /**
     * 将对象转换为 map (只处理包含注解的get属性)
     *
     * @param o
     * @return map
     */
    public static Map<String, Object> beanToMap(Boolean hasNext, Object o, String... fields) {
        SortedMap<String, Object> map = new TreeMap<>();
        if(o == null){
            return map;
        }
        try {
            Class<?> c = Class.forName(o.getClass().getName());
            Method[] m = c.getDeclaredMethods();
            Method method;
            for (int i = 0; i < m.length; i++) {
                method = m[i];
                if (method.getName().startsWith("get")) {
                    try {
                        method.setAccessible(true);
                        String key = StringUtils.uncapitalize(method.getName().substring(3));
                        if (fields.length > 0 && ArrayUtils.contains(fields, key)) {
                            Object value = method.invoke(o);
                            if (value != null) {
                                collectionHandler(hasNext, value);
                                map.put(key, value);
                            }
                        } else if (fields.length > 0) {
                            String item = isExist(key, fields);
                            if (item != null) {
                                if(map.containsKey(item.substring(0, item.indexOf(".")))){
                                    continue;
                                }
                                Object fieldValue = method.invoke(o);
                                String[] curFields = getCurFields(item, fields);
                                List status = null;
                                if(fieldValue instanceof Collection){
                                    status = collectionHandler(hasNext, fieldValue, curFields);
                                }
                                if(status == null){
                                    Map<String, Object> map1 = beanToMap(hasNext, fieldValue, curFields);
                                    if(map1.size() > 0){
                                        if(hasNext){
                                            map.put(item.substring(0, item.indexOf(".")), map1);
                                        }else{
                                            map.putAll(map1);
                                        }
                                    }
                                }else{
                                    map.put(item.substring(0, item.indexOf(".")), status);
                                }
                            }
                        }
                    } catch (Exception e) {
//						System.out.println("error:" + method.getName());
                    }
                }
            }
        } catch (Exception e) {
            log.warn("beanToMap error info ==>" + o, e);
        }
        return map;
    }

    private static List collectionHandler(Boolean hasNext, Object value, String... fields){
        if (value instanceof Collection) {
            List list = new ArrayList<>();
            Collection<?> coll = (Collection<?>) value;
            for (Iterator<?> iterator = coll.iterator(); iterator.hasNext(); ) {
                list.add(beanToMap(hasNext, iterator.next(), fields));
            }
            return list;
        }
        return null;
    }

    private static String[] getCurFields(String item, String... fields){
        List<String> list = new ArrayList<>();
        String itemKey = item.substring(0, item.indexOf("."));
        for(String temp : fields){
            if(temp != null && temp.startsWith(itemKey)){
                list.add(temp.substring(temp.indexOf(".") + 1));
            }
        }
        return list.toArray(new String[list.size()]);
    }

    /**
     * @param list
     * @param fields
     * @return
     */
    public static List<Map<?, ?>> listBeanToMap(Boolean hasNext, List<?> list, String... fields) {
        List<Map<?, ?>> temp = new ArrayList<>();
        if (list != null) {
            for (Iterator<?> iterator = list.iterator(); iterator.hasNext(); ) {
                Object item = iterator.next();
                temp.add(beanToMap(hasNext, item, fields));
            }
        }
        return temp;
    }

    /**
     * 获取无参方法值
     *
     * @param o
     * @param name
     * @return
     */
    public static Object getNoParamMethodValue(Object o, String name) {
        try {
            Class<?> c = Class.forName(o.getClass().getName());
            Method method = c.getMethod("get" + StringUtils.capitalize(name));
            return method.invoke(o);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String isExist(String key, String... fields) {
        for (String item : fields) {
            if (StringUtils.isNotBlank(item)) {
                if (item.contains(".")) {
                    String[] split = item.split("[.]");
                    if (ArrayUtils.contains(split, key)) {
                        return item;
                    }
                }
            }
        }
        return null;
    }
}