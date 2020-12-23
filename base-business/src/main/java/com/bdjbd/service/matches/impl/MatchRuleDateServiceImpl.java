package com.bdjbd.service.matches.impl;

import com.alibaba.fastjson.JSONObject;
import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.ResultClauseItem;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.service.matches.MatchRuleDateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author dbc
 * @version 1.0
 * @className MatchRuleDateServiceImpl
 * @description TODO
 * @date 2020/3/5
 **/
@Slf4j
@Service
public class MatchRuleDateServiceImpl implements MatchRuleDateService {

    @Override
    public ResultClauseItem check(ClauseItem current, List<ClauseItem> clauseItems, QaStandardRecord record) {
        List<String> parameterValueList = new ArrayList<>();
        for (QaStandardRecordItem item : record.getItems()) {
            if (item.getParameterId().equals(current.getItemAttr0())) {
                String parameterValue = item.getParameterValue() == null ? "" : item.getParameterValue();
                parameterValueList.add(parameterValue);
            }
        }
        current.setCurrentValue(JSONObject.toJSONString(parameterValueList));
        ResultClauseItem result = new ResultClauseItem();
        result.setClauseItemCode(current.getItemRelationCode());
        result.setClauseItem(current);
        String pattern=current.getTypeValidate();
        result.setResult(true);
        result.setCount(parameterValueList.size());
        for (String string : parameterValueList) {
            //验证时间格式
            if (!Pattern.matches(pattern, string)) {
                result.setResult(false);
                break;
            }
        }
        return result;
    }
}
