package com.bdjbd.service.matches;

import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.ResultClauseItem;
import com.bdjbd.dao.entity.QaStandardRecord;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className MatchRuleSelectService
 * @description TODO
 * @date 2020/3/5
 **/
public interface MatchRuleSelectService {

    /**
     * select格式校验
     * @param clauseItems
     * @param record
     * @return
     */
    ResultClauseItem check(ClauseItem current, List<ClauseItem> clauseItems, QaStandardRecord record);
}
