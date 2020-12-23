package com.bdjbd.service.matches.util;

import com.bdjbd.bo.ClauseItem;
import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className CustomerSortUtils 自定义排序工具
 * @description TODO
 * @date 2020/3/6
 **/
public class CustomerSortUtils {

    /** 类型排序 */
    static String[] typeCustomerSort = new String[]{"select", "select&text", "radio", "textarea", "date", "all", "text"};

    /** 校验类型排序 */
    static String[] typeValidateCustomerSort = new String[]{"default", "name", "contains", "near", "number", "all", "sum", "record"};

    /** 整数 */
    static String lastText = "record";

    /** 文本 */
    static String text = "text";

    /**
     * 根据规则排序
     * @param clauseItems
     */
    public static List<ClauseItem> clauseItemSort(List<ClauseItem> clauseItems){
        List<ClauseItem> result = new ArrayList<>();
        for(String type : typeCustomerSort){
            if(type.equals(text)){
                for(String typeValidate : typeValidateCustomerSort){
                    sortHandler(result, clauseItems, type, typeValidate);
                }
            } else {
                sortHandler(result, clauseItems, type, null);
            }
        }
        return result;
    }

    /**
     * 排序规则
     * @param result
     * @param clauseItems
     * @param type
     * @param typeValidate
     */
    private static void sortHandler(List<ClauseItem> result, List<ClauseItem> clauseItems, String type, String typeValidate){
        for(Iterator<ClauseItem> iterator = clauseItems.iterator(); iterator.hasNext();){
            ClauseItem next = iterator.next();
            if(type.equals(text) && next.getItemType().equals(type)){
                if(!typeValidate.equals(lastText) && typeValidate.equals(next.getTypeValidate())){
                    result.add(next);
                }else{
                    if(typeValidate.equals("all")){
                        if(!ArrayUtils.contains(typeValidateCustomerSort, next.getTypeValidate())){
                            result.add(next);
                        }
                    }else if(typeValidate.equals(lastText) && typeValidate.equals(next.getTypeValidate())){
                        result.add(next);
                    }
                }
            }else if(type.equals(next.getItemType())
                    || (type.equals("all") && !ArrayUtils.contains(typeCustomerSort, next.getItemType()))){
                result.add(next);
            }
        }
    }
}
