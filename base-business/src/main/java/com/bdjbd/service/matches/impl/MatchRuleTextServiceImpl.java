package com.bdjbd.service.matches.impl;

import com.alibaba.fastjson.JSONObject;
import com.bdjbd.bo.Clause;
import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.RecordItemGroup;
import com.bdjbd.bo.ResultClauseItem;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.enums.ItemTypeValidate;
import com.bdjbd.service.matches.DataRecordHandlerService;
import com.bdjbd.service.matches.MatchClauseItemService;
import com.bdjbd.service.matches.MatchRuleService;
import com.bdjbd.service.matches.MatchRuleTextService;
import com.bdjbd.service.matches.util.ExpressUtils;
import com.bdjbd.service.matches.util.Relation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author dbc
 * @version 1.0
 **/
@Slf4j
@Service
public class MatchRuleTextServiceImpl implements MatchRuleTextService {

    @Autowired
    private DataRecordHandlerService dataRecordHandlerService;
    @Autowired
    private MatchRuleService matchRuleService;
    @Autowired
    private MatchClauseItemService matchClauseItemService;

    @Override
    public ResultClauseItem check(List<ResultClauseItem> result, ClauseItem current, List<ClauseItem> clauseItems, Clause clause, QaStandardRecord record, List<RecordItemGroup> itemGroups) {
        ResultClauseItem resultClauseItem = new ResultClauseItem();
        resultClauseItem.setClauseItemCode(current.getItemRelationCode());
        resultClauseItem.setClauseItem(current);

        // contains
        if (current.getTypeValidate().equals(ItemTypeValidate.CONTAINS.getKey())) {
            containsHandler(resultClauseItem, current, record);
        }
        //near
        else if (current.getTypeValidate().equals(ItemTypeValidate.NEAR.getKey())) {
            nearHandler(resultClauseItem, current, record);
        }
        //number
        else if (current.getTypeValidate().equals(ItemTypeValidate.NUMBER.getKey())) {
            numberHandler(resultClauseItem, current, record);
        }
        //avg_number_year
        else if (current.getTypeValidate().equals(ItemTypeValidate.AVG_NUMBER_YEAR.getKey())) {
            avgNumberYearHandler(resultClauseItem, current, record);
        }
        //avg_number
        else if (current.getTypeValidate().equals(ItemTypeValidate.AVG_NUMBER.getKey())) {
            avgNumberHandler(resultClauseItem, current, record);
        }
        //sum
        else if (current.getTypeValidate().equals(ItemTypeValidate.SUM.getKey())) {
            sumHandler(resultClauseItem, current, record);
        }
        //record
        else if (current.getTypeValidate().equals(ItemTypeValidate.RECORD.getKey())) {
            recordHandler(resultClauseItem, clauseItems, clause, current, record, itemGroups);
        }
        return resultClauseItem;
    }

    /**
     * 记录中包含指定关键字
     * @return
     */
    private void containsHandler(ResultClauseItem resultClauseItem, ClauseItem current, QaStandardRecord record) {
        Integer count = 0;
        List<String> currentValueList = new ArrayList<>();
        for (QaStandardRecordItem next : record.getItems()) {
            if (next.getParameterId().equals(current.getItemAttr0())) {
                currentValueList.add(next.getParameterValue());
                String relationValue = current.getRelationValue();
                if (!StringUtils.isEmpty(relationValue)) {
                    // eg: CN##ISSN##ISBN
                    String[] split = relationValue.split("##");
                    for (String s : split) {
                        if (next.getParameterValue().contains(s)) {
                            count++;
                            break;
                        }
                    }
                }
            }
        }
        current.setCurrentValue(JSONObject.toJSONString(currentValueList));
        resultClauseItem.setCount(count);
        resultClauseItem.setResult(count > 0);
    }

    private void nearHandler(ResultClauseItem resultClauseItem, ClauseItem current, QaStandardRecord record) {
        resultClauseItem.setResult(false);

        // 获取最近几年的年份 yearList
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.getWeekYear();
        String relationValue = current.getRelationValue();
        if (StringUtils.isEmpty(relationValue)) {
            relationValue = "0";
        }
        int relationValueInt = Integer.valueOf(relationValue);
        List<Integer> yearList = new ArrayList<>();
        for (int i = 1; i <= relationValueInt; i++) {
            yearList.add(year - i);
        }

        List<Integer> yearList2 = new ArrayList<>();
        for (QaStandardRecordItem item : record.getItems()) {
            if (item.getParameterId().equals(current.getItemAttr0())) {
                yearList2.add(Integer.valueOf(item.getParameterValue()));
            }
        }
        boolean check = true;
        for (Integer integer : yearList) {
            if (!yearList2.contains(integer)) {
                log.info("不包含year={}", integer);
                check = false;
                break;
            }
        }
        current.setCurrentValue(JSONObject.toJSONString(yearList2));
        if (check) {
            resultClauseItem.setCount(1);
        } else {
            resultClauseItem.setCount(0);
        }
        resultClauseItem.setResult(check);
    }

    private void numberHandler(ResultClauseItem resultClauseItem, ClauseItem current, QaStandardRecord record) {
        Integer count = 0;
        List<String> currentValueList = new ArrayList<>();
        for (QaStandardRecordItem qaStandardRecordItem : record.getItems()) {
            if (qaStandardRecordItem.getParameterId().equals(current.getItemAttr0())) {
                String currentValue = qaStandardRecordItem.getParameterValue()
                        == null ? "0" : qaStandardRecordItem.getParameterValue();
                if (currentValue.contains("/")) {
                    currentValue = currentValue.split("/")[0];
                }
                String relationValue = null;
                if (StringUtils.isEmpty(current.getRelationValue())) {
                    relationValue = "0";
                } else {
                    relationValue = current.getRelationValue();
                }
                count += dataRecordHandlerService.numberCompareCount(current
                                .getItemExpress(), Double.valueOf(currentValue), Double.valueOf(relationValue));
                currentValueList.add(currentValue);
            }
        }
        current.setCurrentValue(JSONObject.toJSONString(currentValueList));
        resultClauseItem.setCount(count);
        resultClauseItem.setResult(count > 0);
    }

    private void avgNumberYearHandler(ResultClauseItem resultClauseItem, ClauseItem current, QaStandardRecord record) {
        Integer sum = 0;
        Integer count = 0;
        Set<Integer> yearSet = new HashSet<>();
        List<QaStandardRecordItem> items = record.getItems();
        // 时间关联字段 {"paramId":"10200-002","dateType":"yyyy"}
        // qa_base_clause_item.attr4
        String itemAttr4 = current.getItemAttr4();
        JSONObject object = JSONObject.parseObject(itemAttr4);
        for (QaStandardRecordItem qaStandardRecordItem : items) {
            if (qaStandardRecordItem.getParameterId().equals(current.getItemAttr0())) {
                String currentValue = qaStandardRecordItem.getParameterValue() == null ? "0" : qaStandardRecordItem.getParameterValue();
                sum += Integer.valueOf(currentValue);
            }
            if (qaStandardRecordItem.getParameterId().equals(object.getString("paramId"))) {
                String currentValue = qaStandardRecordItem.getParameterValue();
                Integer year = null;
                if (StringUtils.isEmpty(currentValue) || "-至今".equals(currentValue)) {
                    year = Calendar.getInstance().getWeekYear();
                } else {
                    year = Integer.valueOf(currentValue.split("\\.")[0]);
                }
                yearSet.add(year);
            }
        }
        count = yearSet.size();
        avg(resultClauseItem, current, sum, count);
    }

    private void avg(ResultClauseItem resultClauseItem, ClauseItem current, Integer sum, Integer count) {
        String relationValue = current.getRelationValue();
        if (count == 0) {
            count = 1;
        }
        int i = sum / count;
        Integer integer = dataRecordHandlerService.numberCompareCount(current.getItemExpress(), Double.valueOf(i), Double.valueOf(relationValue));
        current.setCurrentValue("" + i);
        resultClauseItem.setCount(integer);
        resultClauseItem.setResult(integer > 0);
    }

    private void avgNumberHandler(ResultClauseItem resultClauseItem, ClauseItem current, QaStandardRecord record) {
        Integer sum = 0;
        Integer count = 0;
        for (QaStandardRecordItem qaStandardRecordItem : record.getItems()) {
            if (qaStandardRecordItem.getParameterId().equals(current.getItemAttr0())) {
                String currentValue = qaStandardRecordItem.getParameterValue() == null ? "0" : qaStandardRecordItem.getParameterValue();
                sum += Integer.valueOf(currentValue);
                count++;
            }
        }
        avg(resultClauseItem, current, sum, count);
    }

    private void sumHandler(ResultClauseItem resultClauseItem, ClauseItem current, QaStandardRecord record) {
        Integer count = 0;
        Integer sum = 0;

        String itemAttr0 = current.getItemAttr0();
        if (!StringUtils.isEmpty(itemAttr0)) {
            String[] split = itemAttr0.split(",");
            List<String> list = Arrays.asList(split);
            for (QaStandardRecordItem qaStandardRecordItem : record.getItems()) {
                if (list.contains(qaStandardRecordItem.getParameterId())) {
                    sum += Integer.valueOf(qaStandardRecordItem.getParameterValue());
                }
            }
        }
        count += dataRecordHandlerService.numberCompareCount(current
                .getItemExpress(), Double.valueOf(sum), Double.valueOf(current
                .getRelationValue()));
        current.setCurrentValue(sum.toString());
        resultClauseItem.setCount(count);
        resultClauseItem.setResult(count > 0);
    }

    private void recordHandler(ResultClauseItem resultClauseItem, List<ClauseItem> clauseItems, Clause clause, ClauseItem
            current, QaStandardRecord record, List<RecordItemGroup> itemGroups) {
        resultClauseItem.setResult(false);

        //处理记录关联关系
        Integer count = recordRelationHandler(current, clauseItems, clause, record, itemGroups);
        if(count.equals(-1))
            count = dataRecordHandlerService.parameterIdCount(current.getItemAttr0(), record);
        current.setCurrentValue(count.toString());
        Integer integer = Integer.valueOf(StringUtils.isEmpty(current.getRelationValue()) ? "0" : current.getRelationValue());
        if (StringUtils.isEmpty(current.getItemExpress())) {
            return;
        }
        switch (current.getItemExpress()) {
            case ">":
                if (count > integer) {
                    resultClauseItem.setResult(true);
                }
                break;
            case ">=":
                if (count >= integer) {
                    resultClauseItem.setResult(true);
                }
                break;
            case "<":
                if (count < integer) {
                    resultClauseItem.setResult(true);
                }
                break;
            case "<=":
                if (count <= integer) {
                    resultClauseItem.setResult(true);
                }
                break;
            case "=":
                if (count == integer) {
                    resultClauseItem.setResult(true);
                }
                break;
        }
    }

    private Integer recordRelationHandler(ClauseItem current, List<ClauseItem> clauseItems, Clause clause, QaStandardRecord record, List<RecordItemGroup> itemGroups){
        String attr2 = current.getItemAttr2();
        if(org.apache.commons.lang3.StringUtils.isNotBlank(attr2) && org.apache.commons.lang3.StringUtils.isNotBlank(clause.getClauseItemCondition())){
            //表达式处理
            Relation relation1 = ExpressUtils.relationMapHandler(current.getItemRelationCode(), clause.getClauseItemCondition());
            if(relation1 != null){
                return checkRecordHandler(itemGroups, current.getItemRelationCode(), relation1, clause);
            }
        }
        return -1;
    }

    private Integer checkRecordHandler(List<RecordItemGroup> itemGroups, String key, Relation relation, Clause clause){
        Integer count = 0;
        QaStandardRecord temp = new QaStandardRecord();
        for(Iterator<RecordItemGroup> iterator = itemGroups.iterator(); iterator.hasNext();){
            RecordItemGroup next = iterator.next();
            temp.setItems(next.getItems());
            Boolean aBoolean = matchClauseItemService.academicMatch(clause, key, relation, temp);
            if (aBoolean)
                count ++;
        }
        return count;
    }
}
