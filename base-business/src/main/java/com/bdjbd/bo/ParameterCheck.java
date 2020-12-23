package com.bdjbd.bo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * @author zhuzhe
 * @date 2020/4/13 13:35
 * @email zhuzhe_mail@163.com
 */
@Data
public class ParameterCheck {

    private String type;
    private String desc;
    private boolean status;
    private JSONObject checkJson;

    public static ParameterCheck getInstance(String type, List<ParameterCheck> list) {
        for (ParameterCheck parameterCheck : list) {
            if (parameterCheck.getType().equals(type)) {
                return parameterCheck;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        String test = "[{\"type\":\"dateCheck\",\"desc\":\"评审时需要用到的时间对比检查\",\"status\":false,\"checkJson\":{\"dateCheck\":[{\"parameterId\":\"10100-021\",\"type\":\"after\",\"param\":\"start\"},{\"parameterId\":\"10100-025\",\"type\":\"before\",\"param\":\"end\"}]}}]";

        List<ParameterCheck> parameterChecks = JSON.parseArray(test, ParameterCheck.class);

        ParameterCheck dateCheck = getInstance("dateCheck11", parameterChecks);

        System.out.println(dateCheck);
    }
}
