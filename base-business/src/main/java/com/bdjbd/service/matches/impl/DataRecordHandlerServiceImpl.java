package com.bdjbd.service.matches.impl;

import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.RecordItemGroup;
import com.bdjbd.dao.entity.BaseSimpleDefinition;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.enums.ItemTypeExpress;
import com.bdjbd.service.matches.DataRecordHandlerService;
import com.sun.org.apache.regexp.internal.RE;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className DataRecordHandlerServiceImpl
 * @description TODO
 * @date 2020/3/2
 **/
@Service
@Slf4j
public class DataRecordHandlerServiceImpl implements DataRecordHandlerService {
    @Override
    public Integer hasBaseSimpleDefinitionCount(ClauseItem current, QaStandardRecord record) {
        Integer count = 0;
//        System.out.println("current :" + current);
//        System.out.println("record :" + record);
        for (Iterator<QaStandardRecordItem> iterator = record.getItems().iterator(); iterator.hasNext(); ) {
            QaStandardRecordItem next = iterator.next();
//            System.out.println(next);
//            System.out.println(current.getTypeValidate());
            if (next.getAttr2().equals(current.getTypeValidate())) {
                if (StringUtils.equals(next.getParameterValue(), current.getItemRelationName())) {
                    count++;
                }
            }
        }
        return count;
    }


    @Override
    public Integer beforeBaseSimpleDefinition(ClauseItem current, BaseSimpleDefinition currentDefinition, List<BaseSimpleDefinition> sameTypeList, QaStandardRecord record, Boolean hasCurGrade) {
        List<BaseSimpleDefinition> baseSimpleDefinitions = beforeBaseSimpleDefinitions(currentDefinition, sameTypeList, hasCurGrade);
        Integer count = 0;
        for (Iterator<QaStandardRecordItem> iterator = record.getItems().iterator(); iterator.hasNext(); ) {
            QaStandardRecordItem next = iterator.next();
            if (hasBaseSimpleDefinition(next.getAttr2(), next.getParameterValue(), baseSimpleDefinitions)) {
                count++;
            }
        }
        return count;
    }

    /**
     * 获取大于(等于)当前等级选项
     * @return
     */
    private List<BaseSimpleDefinition> beforeBaseSimpleDefinitions(BaseSimpleDefinition currentDefinition, List<BaseSimpleDefinition> sameTypeList, Boolean hasCurGrade) {
        List<BaseSimpleDefinition> result = new ArrayList<>();
        for (Iterator<BaseSimpleDefinition> iterator = sameTypeList.iterator(); iterator.hasNext(); ) {
            BaseSimpleDefinition next = iterator.next();
            if (next.getOrders() <= currentDefinition.getOrders()) {
                if (next.getOrders() == currentDefinition.getOrders()) {
                    if (hasCurGrade) {
                        result.add(next);
                    }
                }
                else {
                    result.add(next);
                }
            }
        }
        return result;
    }

    /**
     * 是否包含指定选项
     * @return
     */
    private Boolean hasBaseSimpleDefinition(String type, String paramValue, List<BaseSimpleDefinition> baseSimpleDefinitions) {
        for (Iterator<BaseSimpleDefinition> iterator = baseSimpleDefinitions.iterator(); iterator.hasNext(); ) {
            BaseSimpleDefinition next = iterator.next();
            if (next.getType().equals(type) && next.getName().equals(paramValue)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer containsBaseSimpleDefinitionCount(ClauseItem current, List<BaseSimpleDefinition> sameTypeList, QaStandardRecord record) {
        Integer count = 0;
        List<BaseSimpleDefinition> baseSimpleDefinitions = passBaseSimpleDefinitions(current.getRelationValue(), sameTypeList);
        for (Iterator<QaStandardRecordItem> iterator = record.getItems().iterator(); iterator.hasNext(); ) {
            QaStandardRecordItem next = iterator.next();
            if (hasContains(next, baseSimpleDefinitions)) {
                count++;
            }
        }
        return count;
    }

    private List<BaseSimpleDefinition> passBaseSimpleDefinitions(String contains, List<BaseSimpleDefinition> sameTypeList) {
        List<BaseSimpleDefinition> result = new ArrayList<>();
        String[] split = contains.split("##");
        for (String item : split) {
            if (StringUtils.isNotBlank(item)) {
                String[] items = item.split("&");
                BaseSimpleDefinition baseSimpleDefinition = passBaseSimpleDefinition(items, sameTypeList);
                if (baseSimpleDefinition != null) {
                    result.add(baseSimpleDefinition);
                }
            }
        }
        return result;
    }

    private BaseSimpleDefinition passBaseSimpleDefinition(String[] items, List<BaseSimpleDefinition> sameTypeList) {
        loop:
        for (Iterator<BaseSimpleDefinition> iterator = sameTypeList.iterator(); iterator.hasNext(); ) {
            BaseSimpleDefinition next = iterator.next();
            Boolean contains = false;
            for (String item : items) {
                if (StringUtils.isNotBlank(item)) {
                    if (!next.getName().contains(item)) {
                        continue loop;
                    }
                    else {
                        contains = true;
                    }
                }
            }
            if (contains)
                return next;
        }
        return null;
    }

    private Boolean hasContains(QaStandardRecordItem item, List<BaseSimpleDefinition> sameTypeList) {
        loop:
        for (Iterator<BaseSimpleDefinition> iterator = sameTypeList.iterator(); iterator.hasNext(); ) {
            BaseSimpleDefinition next = iterator.next();
            if (next.getId().equals(item.getParameterValue())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Integer parameterIdCount(String parameterId, QaStandardRecord record) {
        Integer count = 0;
        List<Integer> ordersList = new ArrayList<>();
        for (QaStandardRecordItem qaStandardRecordItem : record.getItems()) {
            if (qaStandardRecordItem.getParameterId().equals(parameterId)) {
                if (!ordersList.contains(qaStandardRecordItem.getOrders())) {
                    count++;
                    //保存数据顺序字段，一个顺序表示一条数据
                    ordersList.add(qaStandardRecordItem.getOrders());
                }
            }
        }
        return count;
    }

    /**
     * 用于数据比较
     * @return
     */
    @Override
    public Integer numberCompareCount(String itemExpress, Double currentValue,
            Double relationValue) {
        Integer count = 0;
        if (StringUtils.isBlank(itemExpress)
                || ItemTypeExpress.EQ.getKey().equals(itemExpress)) {
            if (currentValue.compareTo(relationValue) == 0) {
                count++;
            }
        }
        else if (ItemTypeExpress.GT.getKey().equals(itemExpress)) {
            if (currentValue.compareTo(relationValue) > 0) {
                count++;
            }
        }
        else if (ItemTypeExpress.GE.getKey().equals(itemExpress)) {
            if (currentValue.compareTo(relationValue) >= 0) {
                count++;
            }
        }
        else if (ItemTypeExpress.LT.getKey().equals(itemExpress)) {
            if (currentValue.compareTo(relationValue) < 0) {
                count++;
            }
        }
        else if (ItemTypeExpress.LE.getKey().equals(itemExpress)) {
            if (currentValue.compareTo(relationValue) <= 0) {
                count++;
            }
        }
        return count;
    }

    @Override
    public List<RecordItemGroup> recordItemGroupHandler(QaStandardRecord record) {
        List<RecordItemGroup> itemGroups = new ArrayList<>();
        for (Iterator<QaStandardRecordItem> iterator = record.getItems().iterator(); iterator.hasNext(); ) {
            QaStandardRecordItem next = iterator.next();
            RecordItemGroup recordItemGroup = getRecordItemGroup(itemGroups, next.getParameterGroupId(), next.getOrders());
            recordItemGroup.getItems().add(next);
        }
        return itemGroups;
    }

    private RecordItemGroup getRecordItemGroup(List<RecordItemGroup> itemGroups, String parameterGroupId, Integer orders) {
        for (Iterator<RecordItemGroup> iterator = itemGroups.iterator(); iterator.hasNext(); ) {
            RecordItemGroup next = iterator.next();
            if (parameterGroupId.equals(next.getParameterGroupId()) && orders.equals(next.getOrders())) {
                return next;
            }
        }
        RecordItemGroup itemGroup = new RecordItemGroup();
        itemGroup.setOrders(orders);
        itemGroup.setParameterGroupId(parameterGroupId);
        itemGroups.add(itemGroup);
        return itemGroup;
    }
}
