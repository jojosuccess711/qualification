package com.bdjbd.service.matches.util;

import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className ExpressCheckResultUtils
 * @description TODO
 * @date 2020/3/3
 **/
public class ExpressCheckResultUtils {

    /**
     * 条件表达式校验结果
     * @param checkResults
     * @return
     */
    public static Boolean check(String key, Relation relation, List<CheckResult> checkResults){
        if(relation == null)
            return true;
        if(relation.getChildren() != null){
            return childrenResult(relation, key, checkResults);
        }
        return itemResult(relation.getRelation(), key, relation.getItems(), checkResults);
    }

    /**
     * 条件表达式校验结果
     * @param express
     * @param checkResults
     * @return
     */
    public static Boolean check(String express, List<CheckResult> checkResults){
        if (express == null) {
            return false;
        }
        Relation relation = ExpressUtils.relationHandler(express);
        if(relation.getChildren() != null){
            return childrenResult(relation, null, checkResults);
        }
        return itemResult(relation.getRelation(), null, relation.getItems(), checkResults);
    }

    /**
     * 条件项间校验结果 (eg： A1||B1)
     * @param relation
     * @param items
     * @param checkResults
     * @return
     */
    private static Boolean itemResult(String relation, String ignoreKey, List<String> items, List<CheckResult> checkResults){
        for(Iterator<String> iterator = items.iterator(); iterator.hasNext();){
            String next = iterator.next();
            if(StringUtils.isNotBlank(ignoreKey) && next.equals(ignoreKey))
                continue;

            Boolean result = checkResult(next, checkResults);
            if(relation.equals(Relation.Type.AND.getKey())){
                if(!result)
                    return result;
            }else{
                if(result)
                    return result;
            }
        }
        if(relation.equals(Relation.Type.AND.getKey()))
            return true;
        return false;
    }

    /**
     * 获取指定标识结果集结果
     * @param key
     * @param checkResults
     * @return
     */
    private static Boolean checkResult(String key, List<CheckResult> checkResults){
        for(Iterator<CheckResult> iterator = checkResults.iterator(); iterator.hasNext();){
            CheckResult next = iterator.next();
            if(next.getRelationCode() != null && next.getRelationCode().equals(key)){
                return next.getResult();
            }
        }
        return false;
    }

    /**
     * 条件子项 结果
     * @param relation
     * @param checkResults
     * @return
     */
    private static Boolean childrenResult(Relation relation, String ignoreKey, List<CheckResult> checkResults){
        for(Iterator<Relation> iterator = relation.getChildren().iterator(); iterator.hasNext();){
            Relation next = iterator.next();
            Boolean result;
            if(next.getChildren() != null){
                result = childrenResult(next, ignoreKey, checkResults);
            }else{
                result = itemResult(next.getRelation(), ignoreKey, next.getItems(), checkResults);
            }

            if(relation.getRelation().equals(Relation.Type.AND.getKey())){
                if(!result)
                    return result;
            }else{
                if(result)
                    return result;
            }
        }
        if(relation.getRelation().equals(Relation.Type.AND.getKey()))
            return true;
        return false;
    }
}
