package com.bdjbd.service.matches.impl;

import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.ResultClauseItem;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.enums.ItemTypeExpress;
import com.bdjbd.service.matches.MatchRuleTextareaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className MatchRuleTextareaServiceImpl
 * @description TODO
 * @date 2020/3/5
 **/
@Slf4j
@Service
public class MatchRuleTextareaServiceImpl implements MatchRuleTextareaService {

    @Override
    public ResultClauseItem check(ClauseItem current, List<ClauseItem> clauseItems, QaStandardRecord record) {
        StringBuilder currentValue = new StringBuilder();
        for (QaStandardRecordItem qaStandardRecordItem : record.getItems()) {
            if (qaStandardRecordItem.getParameterId().equals(current.getItemAttr0())) {
                String parameterValue = qaStandardRecordItem.getParameterValue() == null ? "" : qaStandardRecordItem.getParameterValue();
                currentValue.append(parameterValue);
            }
        }
        current.setCurrentValue(currentValue.toString());
        ResultClauseItem result = new ResultClauseItem();
        result.setClauseItemCode(current.getItemRelationCode());
        result.setClauseItem(current);
        //验证textarea字数
        Integer count = 0;
        if(ItemTypeExpress.LE.getKey().equals(current.getItemExpress())){
            if(current.getCurrentValue().length()<=Integer.parseInt(current.getTypeValidate()==null?"0":current.getTypeValidate())){
                count++;
            }
        }else if(ItemTypeExpress.LT.getKey().equals(current.getItemExpress())){
            if(current.getCurrentValue().length()<Integer.parseInt(current.getTypeValidate()==null?"0":current.getTypeValidate())){
                count++;
            }
        }
        result.setResult(count>0);
        result.setCount(count);
        return result;
    }
}
