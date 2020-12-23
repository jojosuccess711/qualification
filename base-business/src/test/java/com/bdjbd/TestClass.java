package com.bdjbd;

import org.apache.commons.lang3.ArrayUtils;

import java.util.regex.Pattern;

/**
 * @author dbc
 * @version 1.0
 * @className TestClass
 * @description TODO
 * @date 2020/2/20
 **/
public class TestClass {

    public static void main(String[] args) {
        System.out.println(Pattern.matches("[A-Za-z0-9]{0,}&$|^&[A-Za-z0-9]{0,}", "&"));

    }
}
