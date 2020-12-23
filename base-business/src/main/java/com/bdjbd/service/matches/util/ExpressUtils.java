package com.bdjbd.service.matches.util;

import org.apache.commons.lang3.StringUtils;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @author dbc
 * @version 1.0
 * @className ExpressUtils
 * @description 条件表达式工具
 * @date 2020/3/3
 **/
public class ExpressUtils {

    public static Relation relationHandler(String message){
        message = message.replaceAll("\\s*", "");
        List<String> strings = expressSplit(message);
        if(strings.isEmpty()){
            strings.add(message);
        }
        return relationHandler(new Relation(), strings);
    }


    public static Relation relationHandler(Relation relation, List<String> items){
        if(relation != null){
            relation.setRelation(findRelation(items).getKey());
        }
        //单项
        if(items.size() == 1){
            relationItem(relation, null, items.get(0));
            return relation;
        }
        //复项
        List<Relation> result = new ArrayList<>();
        for(Iterator<String > iterator = items.iterator(); iterator.hasNext();){
            String item = iterator.next();
            relationItem(null, result, item);
        }
        relation.setChildren(result);
        return relation;
    }

    private static void relationItem(Relation relation, List<Relation> result, String item){
        Relation temp =  new Relation();
        if(result != null){
            List<String> expressSplit = expressSplit(item);
            if(!expressSplit.isEmpty()){
                result.add(relationHandler(new Relation(), expressSplit));
                return;
            }
            if(item.contains(Relation.Type.OR.getKey())){
                temp.setRelation(Relation.Type.OR.getKey());
            }else if(item.contains(Relation.Type.AND.getKey())){
                temp.setRelation(Relation.Type.AND.getKey());
            }else{
                temp.setRelation(Relation.Type.AND.getKey());
            }
        }else{
            temp = relation;
        }
        String[] split = item.split(temp.getRelation().equals(Relation.Type.OR.getKey()) ? "[" + temp.getRelation() + "]" : temp.getRelation());
        List<String> childrenItems = new ArrayList<>();
        for(String str : split){
            if(StringUtils.isNotBlank(str)){
                childrenItems.add(str);
            }
        }
        if(!childrenItems.isEmpty()){
            temp.setItems(childrenItems);
            if(result != null){
                result.add(temp);
            }
        }
    }

    private static Relation.Type findRelation(List<String> items){
        if(items.size() == 1){
            if(items.get(0).contains(Relation.Type.OR.getKey()))
                return Relation.Type.OR;
            return Relation.Type.AND;
        }
        for(Iterator<String> iterator = items.iterator(); iterator.hasNext();){
            String next = iterator.next();
            boolean matches = Pattern.matches("[A-Za-z0-9&]{0,}&$|^&[A-Za-z0-9&]{0,}", next);
            if(matches){
                return Relation.Type.AND;
            }
        }
        return Relation.Type.OR;
    }

    /**
     * 提取括号中内容，忽略括号中的括号
     * @param msg
     * @return
     */
    public static List<String> extractMessage(String msg) {
        List<String> list = new ArrayList<String>();
        int start = 0;
        int startFlag = 0;
        int endFlag = 0;
        for (int i = 0; i < msg.length(); i++) {
            if (msg.charAt(i) == '(') {
                startFlag++;
                if (startFlag == endFlag + 1) {
                    start = i;
                }
            } else if (msg.charAt(i) == ')') {
                endFlag++;
                if (endFlag == startFlag) {
                    list.add(msg.substring(start + 1, i));
                }
            }
        }
        return list;
    }

    /**
     * 条件拆项
     * @param express
     * @return
     */
    public static List<String> expressSplit(String express){
        List<String> result = new ArrayList<>();

        List<String> strings = extractMessage(express);
        String sign;
        if(!strings.isEmpty()){
            int firstIndex = express.indexOf(strings.get(0));
            sign = express.substring(0, firstIndex - 1);
            if(StringUtils.isNotBlank(sign)){
                result.add(sign);
            }
        }
        for(int i = 0; i < strings.size(); i++){
            result.add(strings.get(i));
            if(i < strings.size() - 1){
                int firstIndex = express.indexOf(strings.get(i));
                int endIndex = express.indexOf(strings.get(i+1));
                sign = express.substring(firstIndex + strings.get(i).length() + 1, endIndex - 1);
                result.add(sign);
            }
        }
        if(!strings.isEmpty()){
            int endIndex = express.indexOf(strings.get(strings.size() - 1));
            sign = express.substring(endIndex + strings.get(strings.size() - 1).length() + 1, express.length());
            if(StringUtils.isNotBlank(sign)){
                result.add(sign);
            }
        }
        return result;
    }

    public static Relation relationMapHandler(String key, String message){
        Relation relation = relationHandler(message);
        return relationHandler(key, relation, relation);
    }

    private static Relation relationHandler(String key, Relation parent, Relation relation){
        if(relation.getChildren() != null && !relation.getChildren().isEmpty()){
            for(Iterator<Relation> iterator = relation.getChildren().iterator(); iterator.hasNext();){
                Relation next = iterator.next();
                Relation relation1 = relationHandler(key, relation, next);
                if(relation1 != null){
                    return relation1;
                }
            }
        }else if(relation.getItems() != null && !relation.getItems().isEmpty()){
            if(relation.getItems().contains(key)){
                //AND条件
                if(relation.getItems().size() == 1 && parent.getRelation().equals(Relation.Type.AND.getKey()))
                    return parent;
                //AND条件
                if(relation.getRelation().equals(Relation.Type.AND.getKey())){
                    return relation;
                }
            }
        }
        return null;
    }
}
