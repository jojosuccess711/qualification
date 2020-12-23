package com.bdjbd.service.matches.impl;

import com.alibaba.fastjson.JSONObject;
import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.ResultClauseItem;
import com.bdjbd.dao.entity.BaseSimpleDefinition;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.enums.ItemTypeExpress;
import com.bdjbd.enums.ItemTypeValidate;
import com.bdjbd.service.BaseSimpleDefinitionService;
import com.bdjbd.service.matches.DataRecordHandlerService;
import com.bdjbd.service.matches.MatchRuleSelectService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className MatchRuleSelectServiceImpl
 * @description TODO
 * @date 2020/3/5
 **/
@Slf4j
@Service
public class MatchRuleSelectServiceImpl implements MatchRuleSelectService {

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
        Integer count = 0;

        //包含 （attr0=qa_base_parameter表id； attr1=包含内容【 ##分割】 attr2=qa_base_simple_definition表type）
        if(current.getTypeValidate().equals(ItemTypeValidate.CONTAINS.getKey())){
            List<BaseSimpleDefinition> list = baseSimpleDefinitionService.findList(current.getItemAttr1());
            count = dataRecordHandlerService.containsBaseSimpleDefinitionCount(current, list, record);
        }else{

            //等于
            if(StringUtils.isBlank(current.getItemExpress()) || ItemTypeExpress.EQ.getKey().equals(current.getItemExpress())){
                count = dataRecordHandlerService.hasBaseSimpleDefinitionCount(current, record);
            }else{

                BaseSimpleDefinition currentDefinition = baseSimpleDefinitionService.find(current.getItemRelationId());
                if (currentDefinition == null) {
//                    log.error("数据错误：不存在 ItemId = {}, RelationId = {}", current.getItemId(), current.getItemRelationId());
                }

                List<BaseSimpleDefinition> list = baseSimpleDefinitionService.findList(currentDefinition.getType());
                //以上
                if(ItemTypeExpress.GT.getKey().equals(current.getItemExpress())){
                    count = dataRecordHandlerService.beforeBaseSimpleDefinition(current, currentDefinition, list, record, false);
                }
                //及以上
                else if(ItemTypeExpress.GE.getKey().equals(current.getItemExpress())){
                    count = dataRecordHandlerService.beforeBaseSimpleDefinition(current, currentDefinition, list, record, true);
                }
            }
        }

        result.setResult(count > 0);
        result.setCount(count);
        return result;
    }
}
