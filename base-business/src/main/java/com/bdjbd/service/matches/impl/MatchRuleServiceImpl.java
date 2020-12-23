package com.bdjbd.service.matches.impl;

import com.bdjbd.bo.Clause;
import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.RecordItemGroup;
import com.bdjbd.bo.ResultClauseItem;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.enums.ItemType;
import com.bdjbd.service.matches.*;
import com.bdjbd.service.matches.util.CustomerSortUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className MatchRuleServiceImpl
 * @description TODO
 * @date 2020/3/4
 **/
@Slf4j
@Service
public class MatchRuleServiceImpl implements MatchRuleService {

    @Autowired
    private MatchRuleDateService dateService;
    @Autowired
    private MatchRuleRadioService radioService;
    @Autowired
    private MatchRuleSelectAndTextService selectAndTextService;
    @Autowired
    private MatchRuleSelectService selectService;
    @Autowired
    private MatchRuleTextService textService;
    @Autowired
    private MatchRuleTextareaService textareaService;
    @Autowired
    private DataRecordHandlerService dataRecordHandlerService;

    @Override
    public List<ResultClauseItem> check(List<ClauseItem> clauseItems, Clause clause, QaStandardRecord record) {
        //分组处理
        List<RecordItemGroup> itemGroups = dataRecordHandlerService.recordItemGroupHandler(record);

        List<ResultClauseItem> result = new ArrayList<>();
//        System.out.println("MatchRuleServiceImpl->check 50"+clauseItems);
        clauseItems = CustomerSortUtils.clauseItemSort(clauseItems);
//        System.out.println("MatchRuleServiceImpl->check 52"+clauseItems);
        for(Iterator<ClauseItem> iterator = clauseItems.iterator(); iterator.hasNext();){
            ResultClauseItem resultClauseItem = checkSingle(iterator.next(), result, clauseItems, clause, record, itemGroups);
            if(resultClauseItem != null)
                result.add(resultClauseItem);
        }
        return result;
    }

    @Override
    public ResultClauseItem checkSingle(ClauseItem next, List<ResultClauseItem> result, List<ClauseItem> clauseItems, Clause clause, QaStandardRecord record, List<RecordItemGroup> itemGroups) {
        ResultClauseItem resultClauseItem = null;
        //验证数据类型
        if(ItemType.SELECT.getKey().equals(next.getItemType())){
            resultClauseItem = selectService.check(next, clauseItems, record);
        }
        else if(ItemType.SELECT_AND_TEXT.getKey().equals(next.getItemType())){
            resultClauseItem = selectService.check(next, clauseItems, record);
        }
        else if(ItemType.RADIO.getKey().equals(next.getItemType())){
            resultClauseItem = radioService.check(next, clauseItems, record);
        }
        else if(ItemType.DATE.getKey().equals(next.getItemType())){
            resultClauseItem = dateService.check(next, clauseItems, record);
        }
        else if(ItemType.TEXTAREA.getKey().equals(next.getItemType())){
            resultClauseItem = textareaService.check(next, clauseItems, record);
        }
        else if(ItemType.TEXT.getKey().equals(next.getItemType())){
            resultClauseItem = textService.check(result, next, clauseItems, clause, record, itemGroups);
        }
        else{
            System.out.println();
//            log.error("不支持的数据方式 id = {}, type = {}", next.getItemId(), next.getItemType());
        }
        return resultClauseItem;
    }
}
