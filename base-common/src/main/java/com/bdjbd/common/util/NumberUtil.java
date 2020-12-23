package com.bdjbd.common.util;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.math.BigDecimal;
import java.util.Random;

/**
  * @className NumberUtil
  * @description 数字工具类
  * @author dbc
  * @date 2019/7/10
  * @version 1.0
  **/
public final class NumberUtil {

    private static ScriptEngine engine = new ScriptEngineManager().getEngineByName("js");

    /**
     * 传入字符串表达式计算结果并返回
     *
     * @param expression
     * @return
     */
    public static Object eval(String expression) {
        try {
            return engine.eval(expression);
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 获取 4 位随机数【从 1000 - 9999】
     *
     * @return 4 位随机数
     */
    public static Integer randomFourNumber() {
        return (int) (Math.random() * 9000 + 1000);
    }

    /**
     * BigDecimal to Integer
     *
     * @param a BigDecimal
     * @param b 乘以指定 Integer 数值
     * @return Integer
     */
    public static Integer bigDecimalToInteger(BigDecimal a, Integer b) {
        return a.multiply(new BigDecimal(100)).intValue();
    }

    /**
     * 判断 类似  BigDecimal(0.01) 与  "0.01" 是否相等
     *
     * @param a BigDecimal 值
     * @param b String 值
     * @param c 增加的值
     * @return 相等 返回 true
     */
    public static Boolean equalesBigDecimalString(BigDecimal a, String b, Integer c) {
        BigDecimal tempA = a.multiply(new BigDecimal(c));
        BigDecimal tempB = new BigDecimal(b);
        if (tempA.intValue() == tempB.intValue()) {
            return true;
        }
        return false;
    }

    public static String getNumberRandomString(int num) {
        String str = "0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append(str.charAt((int) (Math.random() * 10)));
        }
        return sb.toString();
    }

    /**
     * 判断字符串是否是小数
     *
     * @param str
     * @return
     */
    public static boolean isDouble(String str) {
        return str.matches("\\d+\\.\\d+$");
    }

    /**
     * 判断字符串是否是整数
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        return str.matches("^\\d+$$");
    }

    /**
     * 随机指定范围内N个不重复的数
     * 在初始化的无重复待选数组中随机产生一个数放入结果中，
     * 将待选数组被随机到的数，用待选数组(len-1)下标对应的数替换
     * 然后从len-2里随机产生下一个随机数，如此类推
     * @param max  指定范围最大值
     * @param min  指定范围最小值
     * @param n  随机数个数
     * @return int[] 随机数结果集
     */
    public static int[] randomArray(int min,int max,int n){
        int len = max-min+1;

        if(max < min || n > len){
            return null;
        }

        //初始化给定范围的待选数组
        int[] source = new int[len];
        for (int i = min; i < min+len; i++){
            source[i-min] = i;
        }

        int[] result = new int[n];
        Random rd = new Random();
        int index = 0;
        for (int i = 0; i < result.length; i++) {
            //待选数组0到(len-2)随机一个下标
            index = Math.abs(rd.nextInt() % len--);
            //将随机到的数放入结果集
            result[i] = source[index];
            //将待选数组中被随机到的数，用待选数组(len-1)下标对应的数替换
            source[index] = source[len];
        }
        return result;
    }
}
