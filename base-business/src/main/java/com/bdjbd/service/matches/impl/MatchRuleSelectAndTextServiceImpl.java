package com.bdjbd.service.matches.impl;

import com.bdjbd.bo.ClauseItem;
import com.bdjbd.bo.ResultClauseItem;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.service.matches.MatchRuleSelectAndTextService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author dbc
 * @version 1.0
 * @className MatchRuleSelectAndTextServiceImpl
 * @description TODO
 * @date 2020/3/5
 **/
@Slf4j
@Service
public class MatchRuleSelectAndTextServiceImpl implements MatchRuleSelectAndTextService {

    @Override
    public ResultClauseItem check(ClauseItem current, List<ClauseItem> clauseItems, QaStandardRecord record) {
        return null;
    }
}
