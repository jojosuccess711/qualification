package com.bdjbd;

import com.alibaba.fastjson.JSON;
import com.bdjbd.service.matches.util.CheckResult;
import com.bdjbd.common.util.ListUtil;
import com.bdjbd.service.matches.util.ExpressCheckResultUtils;
import com.bdjbd.service.matches.util.ExpressUtils;
import com.bdjbd.service.matches.util.Relation;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className MatchBrackets
 * @description TODO
 * @date 2020/3/3
 **/
public class MatchBrackets {

    public static void expressUtils(){
        String express = "(A1||B1)&((C1||D1)&E1)&(F1||G1)";
//        express = "(A1&A2)||(B1&B2)||((C1||C2)&D1)";
//        express = "A1||(B1&C1)||D1||E1||(F1&G1)";
//        express = "A1||B1";
//        express = "A1||B1||C1||D1";
//        express = "A1";
//        express = "A1&B1";
//        express = "A1&B1&C1&D1";
//        express = "A1||B1||C1||(D1&E1)";
//        express = "A1&((B1&C1)||(D1&E1))";
//        express = "(A1&B1&C1)||(A2&B2&C2)||((A3||A4)&C5)";
//        express = "(A1&((B1&C1)||(B2&C2)||(B3&C3)))||(A2&((B2&C4)||(B3&C5)||(B4&C6)))";
//        express = "(A1||A2)&B1&C1";
//        express = "(A1||A2 ) &B1&C1&D1";
//        express = "B1&C1&D1&(A1||A2 )";

        Relation relation = ExpressUtils.relationHandler(express);

        String s = JSON.toJSONString(relation);
        System.out.println(s);
    }

    private static CheckResult createCheckResult(String key, Boolean result){
        CheckResult result1 = new CheckResult();
        result1.setRelationCode(key);
        result1.setResult(result);
        return result1;
    }

    public static void relationHandler(){
        String express = "(A1||B1)&((C1||D1)&E1)&(F1||G1)";
//        express = "(A1&A2)||(B1&B2)||((C1||C2)&D1)";
//        express = "A1||(B1&C1)||D1||E1||(F1&G1)";
//        express = "A1||B1";
//        express = "A1||B1||C1||D1";
//        express = "A1";
//        express = "A1&B1";
//        express = "A1&B1&C1&D1";
//        express = "A1||B1||C1||(D1&E1)";
//        express = "A1&((B1&C1)||(D1&E1))";
//        express = "(A1&B1&C1)||(A2&B2&C2)||((A3||A4)&C5)";
//        express = "(A1&((B1&C1)||(B2&C2)||(B3&C3)))||(A2&((B2&C4)||(B3&C5)||(B4&C6)))";
//        express = "(A1||A2)&B1&C1";
//        express = "(A1||A2 )&B1&C1&D1";
        express = "B1&C1&D1&(A1||A2 )";

        Relation e1 = ExpressUtils.relationMapHandler("D1", express);
        String s = JSON.toJSONString(e1);
        System.out.println(s);
    }

    public static void main(String[] args) {
        String express = "(A1||B1)&((C1||D1)&E1)&(F1||G1)";
        express = "A1||B1||C1";
        express = "A1&B1";
        express = "A1";
        express = "A1&(B1||C1)";
        express = "(A1&B1)||(C1&D1)||((E1||F1)&G1)";
        express = "(A1&((B1&C1)||(B2&C2)||(B3&C3)))||(A2&((B2&C4)||(B3&C5)||(B4&C6)))";

//        List<CheckResult> checkResults = ListUtil.addToList(
//                createCheckResult("A1", true),
//                createCheckResult("B1", false),
//                createCheckResult("C1", true),
//                createCheckResult("D1", false),
//                createCheckResult("E1", true),
//                createCheckResult("F1", false),
//                createCheckResult("G1", true));
//
//        Boolean check = ExpressCheckResultUtils.check(express, checkResults);
//        System.out.println(check);

//        expressUtils();

        relationHandler();
    }

}
