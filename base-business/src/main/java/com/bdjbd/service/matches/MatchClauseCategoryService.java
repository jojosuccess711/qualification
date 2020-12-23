package com.bdjbd.service.matches;

import com.bdjbd.bo.ResultCategory;
import com.bdjbd.dao.entity.QaCategory;
import com.bdjbd.dao.entity.QaStandardRecord;

import java.util.Map;

/**
 * @author dbc
 * @version 1.0
 * @className MatchClauseCategoryService
 * @description TODO
 * @date 2020/2/28
 **/
public interface MatchClauseCategoryService {

    /**
     * 是否匹配
     * @param category
     * @param record
     * @return
     */
    ResultCategory isMatch(QaCategory category, QaStandardRecord record);

    /**
     * 不匹配项
     * @return
     */
//    Map<String, Object> notMatch();
}
