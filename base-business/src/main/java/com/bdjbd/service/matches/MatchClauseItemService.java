package com.bdjbd.service.matches;

import com.bdjbd.bo.Category;
import com.bdjbd.bo.Clause;
import com.bdjbd.bo.ResultCategory;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.service.matches.util.Relation;

import java.util.List;
import java.util.Map;

/**
 * @author dbc
 * @version 1.0
 * @className MatchClauseItemService
 * @description TODO
 * @date 2020/2/28
 **/
public interface MatchClauseItemService {

    /**
     * 获取某职称是否匹配
     * @param academic
     * @param record
     * @return
     */
    ResultCategory academicIsMatch(Category academic, QaStandardRecord record);

    /**
     * 匹配职称(五大类，17小类，职称)
     * @param analysis
     * @param record
     * @return
     */
    List<ResultCategory> match(Category analysis, QaStandardRecord record);

    /**
     * 获取已匹配的职称(五大类，17小类，职称)
     * @param analysis
     * @param record
     * @return
     */
    List<ResultCategory> academicMatched(Category analysis, QaStandardRecord record);

    /**
     * 获取不匹配的职称(五大类，17小类，职称)
     * @param analysis
     * @param record
     * @return
     */
    List<ResultCategory> academicNotMatched(Category analysis, QaStandardRecord record);

    /**
     *
     * @return
     */
    Boolean academicMatch(Clause clause, String key, Relation relation, QaStandardRecord record);
}
