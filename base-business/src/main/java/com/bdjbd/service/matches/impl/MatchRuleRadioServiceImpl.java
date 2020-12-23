package com.bdjbd.service.matches.impl;

import com.alibaba.fastjson.JSONObject;
import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.ResultClauseItem;
import com.bdjbd.dao.entity.BaseSimpleDefinition;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.enums.ItemTypeExpress;
import com.bdjbd.service.BaseSimpleDefinitionService;
import com.bdjbd.service.matches.DataRecordHandlerService;
import com.bdjbd.service.matches.MatchRuleRadioService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className MatchRuleRadioServiceImpl
 * @description TODO
 * @date 2020/3/5
 **/
@Slf4j
@Service
public class MatchRuleRadioServiceImpl implements MatchRuleRadioService {

    @Autowired
    private BaseSimpleDefinitionService baseSimpleDefinitionService;
    @Autowired
    private DataRecordHandlerService dataRecordHandlerService;

    @Override
    public ResultClauseItem check(ClauseItem current, List<ClauseItem> clauseItems, QaStandardRecord record) {
        List<String> currentValueList = new ArrayList<>();
        for (QaStandardRecordItem item : record.getItems()) {
            if (item.getParameterId().equals(current.getItemAttr0())) {
                currentValueList.add(item.getParameterValue() == null ? "" : item.getParameterValue());
            }
        }
        current.setCurrentValue(JSONObject.toJSONString(currentValueList));
        ResultClauseItem result = new ResultClauseItem();
        result.setClauseItemCode(current.getItemRelationCode());
        result.setClauseItem(current);

        BaseSimpleDefinition currentDefinition = baseSimpleDefinitionService.find(current.getItemRelationId());
        if(currentDefinition == null){
            log.error("数据错误：不存在 ItemId = {}, RelationId = {}" , current.getItemId(), current.getItemRelationId());
        }

        Integer count = dataRecordHandlerService.hasBaseSimpleDefinitionCount(current, record);
        result.setResult(count > 0);
        result.setCount(count);
        return result;
    }
}
