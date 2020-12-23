package com.bdjbd.service.matches;

import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.RecordItemGroup;
import com.bdjbd.dao.entity.BaseSimpleDefinition;
import com.bdjbd.dao.entity.QaStandardRecord;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className DataRecordHandlerService
 * @description TODO
 * @date 2020/3/2
 **/
public interface DataRecordHandlerService {

    /**
     * 包含指定id数量
     * @param current
     * @return
     */
    Integer hasBaseSimpleDefinitionCount(ClauseItem current, QaStandardRecord record);

    /**
     * 指定等级（及）以上
     * @param current
     * @param record
     * @return
     */
    Integer beforeBaseSimpleDefinition(ClauseItem current, BaseSimpleDefinition currentDefinition, List<BaseSimpleDefinition> sameTypeList, QaStandardRecord record, Boolean hasCurGrade);

    /**
     * 指定信息中是否包含关键词
     * @param current
     * @param record
     * @return
     */
    Integer containsBaseSimpleDefinitionCount(ClauseItem current, List<BaseSimpleDefinition> sameTypeList, QaStandardRecord record);

    /**
     * 获取指定parameterId数量
     * @param parameterId
     * @param record
     * @return
     */
    Integer parameterIdCount(String parameterId, QaStandardRecord record);

    /**
     * 用于数据比较
     * @param itemExpress
     * @param currentValue
     * @param relationValue
     * @return
     */
    Integer numberCompareCount(String itemExpress, Double currentValue,
                               Double relationValue);

    /**
     * 按照参数组序号分组用户填入记录
     * @param record
     * @return
     */
    List<RecordItemGroup> recordItemGroupHandler(QaStandardRecord record);
}
