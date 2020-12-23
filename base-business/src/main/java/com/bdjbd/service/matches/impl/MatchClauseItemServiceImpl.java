package com.bdjbd.service.matches.impl;

import com.bdjbd.bo.*;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.enums.ItemTypeValidate;
import com.bdjbd.service.matches.MatchClauseItemService;
import com.bdjbd.service.matches.MatchRuleService;
import com.bdjbd.service.matches.util.CheckResult;
import com.bdjbd.service.matches.util.ExpressCheckResultUtils;
import com.bdjbd.service.matches.util.Relation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.map.LinkedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author dbc
 * @version 1.0
 * @className MatchClauseItemServiceImpl
 * @description TODO
 * @date 2020/2/28
 **/
@Slf4j
@Service
public class MatchClauseItemServiceImpl implements MatchClauseItemService {

    @Autowired
    private MatchRuleService matchRuleService;

    @Override
    public ResultCategory academicIsMatch(Category academic, QaStandardRecord record) {
        ResultCategory resultCategory = clauseCategoryMatch(academic, record);
        return resultCategory;
    }

    @Override
    public List<ResultCategory> match(Category analysis, QaStandardRecord record) {
        return getResultCategory(analysis, record, null);
    }

    @Override
    public List<ResultCategory> academicMatched(Category analysis, QaStandardRecord record) {
        return getResultCategory(analysis, record, true);
    }

    @Override
    public List<ResultCategory> academicNotMatched(Category analysis, QaStandardRecord record) {
        return getResultCategory(analysis, record, false);
    }

    /**
     * 匹配结果
     * @param analysis
     * @param record
     * @param matched null：（包含满足与不满足） true：满足条件 false: 不满足条件
     * @return
     */
    private List<ResultCategory> getResultCategory(Category analysis, QaStandardRecord record, Boolean matched){
        List<ResultCategory> result = new ArrayList<>();
        if(analysis.getChildren() == null){
            resultCategoryAddHandler(result, clauseCategoryMatch(analysis, record), matched);
        } else {
            for(Iterator<Category> iterator = analysis.getChildren().iterator(); iterator.hasNext();){
                Category item = iterator.next();
                resultCategoryAddHandler(result, clauseCategoryMatch(item, record), matched);
            }
        }
        return result;
    }

    private void resultCategoryAddHandler(List<ResultCategory> result, ResultCategory resultCategory, Boolean matched){
        if(matched == null){
            result.add(resultCategory);
        } else if(matched){
            if(resultCategory.getResult())
                result.add(resultCategory);
        } else {
            if(!resultCategory.getResult())
                result.add(resultCategory);
        }
    }

    /**
     * 条件组匹配
     * @param academic
     * @param record
     * @return
     */
    public ResultCategory clauseCategoryMatch(Category academic, QaStandardRecord record){
        ResultCategory resultCategory = new ResultCategory();
        resultCategory.setCategory(academic);

        List<ResultClauseCategory> resultClauseCategories = new ArrayList<>();
        for (Iterator<ClauseCategory> iterator = academic.getClauseCategories().iterator(); iterator.hasNext();){
            ClauseCategory item = iterator.next();

            ResultClauseCategory resultClauseCategory = new ResultClauseCategory();
            resultClauseCategory.setRelationCode("A" + item.getInnerGroup());
            resultClauseCategory.setClauseCategory(item);

            //条件项
            List<CheckResult> resultClauseList = clauseMatch(item.getClauses(), record);
            resultClauseCategory.setResultClauseList(resultClauseList);

            if(StringUtils.isNotBlank(item.getClauseCondition())){
                String clauseCondition = item.getClauseCondition();
                //根据表达式匹配结果(eg: A1||B1||C1)
                Boolean check = ExpressCheckResultUtils.check(clauseCondition, resultClauseList);
                resultClauseCategory.setResult(check);
            }else{
                //无表达式，默认获取条件项首项的匹配结果
                resultClauseCategory.setResult(resultClauseList.get(0).getResult());
            }
            resultClauseCategories.add(resultClauseCategory);
        }
        resultCategory.setResultClauseCategories(resultClauseCategories);

        Map<String, Boolean> innerGroupResult = relationGroupingHandler(resultClauseCategories);
        resultCategory.setInnerGroupResult(innerGroupResult);
        //此处分组获取结果：
        resultCategory.setResult(resultClauseCategory(innerGroupResult));
        return resultCategory;
    }

    /**
     * 使用 innerGroup 分组获取结果
     * @param innerGroupResult
     * @return
     */
    private Boolean resultClauseCategory(Map<String, Boolean> innerGroupResult){
        for(Iterator<String> iterator = innerGroupResult.keySet().iterator(); iterator.hasNext();){
            if(!innerGroupResult.get(iterator.next())){
                return false;
            }
        }
        return true;
    }

    private Map<String, Boolean> relationGroupingHandler(List<ResultClauseCategory> resultClauseCategories){
        Map<String, Boolean> result = new HashMap<>();
        for(Iterator<ResultClauseCategory> iterator = resultClauseCategories.iterator(); iterator.hasNext();){
            ResultClauseCategory next = iterator.next();
            Boolean aBoolean = result.get(next.getClauseCategory().getInnerGroup());
            if (aBoolean == null) {
                result.put(next.getClauseCategory().getInnerGroup(), next.getResult());
            } else {
                result.put(next.getClauseCategory().getInnerGroup(), (aBoolean || next.getResult()));
            }
        }
        return result;
    }

    /**
     * 条件项匹配
     * @param clauses
     * @param record
     * @return
     */
    public List<CheckResult> clauseMatch(List<Clause> clauses, QaStandardRecord record){
        List<CheckResult> resultClauseList = new ArrayList<>();

        for(Iterator<Clause> iterator = clauses.iterator(); iterator.hasNext();){
            Clause item = iterator.next();
            ResultClause resultClause = new ResultClause();
            resultClause.setClause(item);
            resultClause.setRelationCode(item.getClauseCode());

            //条件子项按照 A1 B1 分组
            List<CheckResult> resultClauseItemGroups = clauseItemMatch(item, null, record);
            resultClause.setResultClauseItemGroupList(resultClauseItemGroups);

            //条件子项关系 (eg: A1||B1)
            String clauseItemCondition = item.getClauseItemCondition();
            //根据表达式匹配结果(eg: A1||B1||C1)
            Boolean check = ExpressCheckResultUtils.check(clauseItemCondition, resultClauseItemGroups);
            resultClause.setResult(check);
            resultClause.setRelationCode(item.getClauseId());
            resultClauseList.add(resultClause);
        }
        return resultClauseList;
    }

    /**
     * 条件子项匹配结果
     * @param clause
     * @param record
     * @return
     */
    public List<CheckResult> clauseItemMatch(Clause clause, String ignoreKey, QaStandardRecord record){
        List<CheckResult> returnResult = new ArrayList<>();
        //条件子项分组整理(eg A1 B1 C1)
        Map<String, List<ClauseItem>> formatClauseItems = clauseItemGroupingHandler(ignoreKey, clause);
        for(Iterator<String> it = formatClauseItems.keySet().iterator(); it.hasNext();){
            List<ClauseItem> item = formatClauseItems.get(it.next());

            ResultClauseItemGroup resultClauseItemGroup = clauseItemGroupingHandler(item, clause, record);
            returnResult.add(resultClauseItemGroup);
        }
        return returnResult;
    }

    /**
     * 将条件子项按照 A1 B1 C1。。。 规则分组
     * @param clause
     * @return
     */
    private Map<String, List<ClauseItem>> clauseItemGroupingHandler(String ignoreKey, Clause clause){
        Map<String, List<ClauseItem>> result = new LinkedMap<>();
        for(Iterator<ClauseItem> iterator = clause.getClauseItems().iterator(); iterator.hasNext();){
            ClauseItem next = iterator.next();
            if(ignoreKey != null){
                if(ignoreKey.equals(next.getItemRelationCode()) || next.getTypeValidate().equals(ItemTypeValidate.RECORD.getKey()))
                    continue;
            }

            List<ClauseItem> tempClauseItems = result.get(next.getItemRelationCode());
            if(tempClauseItems == null){
                tempClauseItems = new ArrayList<>();
            }
            tempClauseItems.add(next);
            result.put(next.getItemRelationCode(), tempClauseItems);
        }
        return result;
    }

    /**
     * 条件项组 匹配结果
     * @param clauseItems
     * @param record
     * @return
     */
    private ResultClauseItemGroup clauseItemGroupingHandler(List<ClauseItem> clauseItems, Clause clause, QaStandardRecord record){
        ResultClauseItemGroup resultClauseItemGroup = new ResultClauseItemGroup();
        resultClauseItemGroup.setRelationCode(clauseItems.get(0).getItemRelationCode());
        resultClauseItemGroup.setResult(true);

        List<ResultClauseItem> resultList = matchRuleService.check(clauseItems, clause, record);
        for(Iterator<ResultClauseItem> iterator = resultList.iterator(); iterator.hasNext();){
            ResultClauseItem item = iterator.next();
            if (item.getResult() == null) {
                continue;
            }
            if(!item.getResult()){
                resultClauseItemGroup.setResult(item.getResult());
                resultClauseItemGroup.getNoPass().add(item);
            }else{
                resultClauseItemGroup.getPass().add(item);
            }
        }
        return resultClauseItemGroup;
    }

    @Override
    public Boolean academicMatch(Clause clause, String key, Relation relation, QaStandardRecord record) {
        removeRelationHandler(key, relation);
        //条件子项按照 A1 B1 分组
        List<CheckResult> resultClauseItemGroups = clauseItemMatch(clause, key, record);
        //根据表达式匹配结果(eg: A1||B1||C1)
        return ExpressCheckResultUtils.check(key, relation, resultClauseItemGroups);
    }

    private void removeRelationHandler(String key, Relation relation){
        if(relation.getChildren() != null && relation.getChildren().size() > 0){
            for(Iterator<Relation> iterator = relation.getChildren().iterator(); iterator.hasNext();){
                Relation next = iterator.next();
                removeRelationHandler(key, next);
            }
        }else{
            if(relation.getItems() != null && relation.getItems().size() > 0){
                for(Iterator<String> iterator = relation.getItems().iterator(); iterator.hasNext();){
                    String next = iterator.next();
                    if(next.equals(key)){
                        iterator.remove();
                    }
                }
            }
        }
    }
}
