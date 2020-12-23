package com.bdjbd.service.matches.impl;

import com.bdjbd.bo.*;
import com.bdjbd.dao.entity.*;
import com.bdjbd.service.BaseParameterGroupService;
import com.bdjbd.service.BaseParameterService;
import com.bdjbd.service.BaseSimpleDefinitionService;
import com.bdjbd.service.QaBaseClauseItemService;
import com.bdjbd.service.matches.DataCategoryHandlerService;
import com.bdjbd.service.matches.MatchClauseCategoryService;
import com.bdjbd.service.matches.MatchClauseItemService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dbc
 * @version 1.0
 **/
@Slf4j
@Service
public class MatchClauseCategoryServiceImpl implements MatchClauseCategoryService {

    @Autowired
    private DataCategoryHandlerService dataCategoryHandlerService;

    @Autowired
    private QaBaseClauseItemService qaBaseClauseItemService;

    @Autowired
    private MatchClauseItemService matchClauseItemService;

    @Autowired
    BaseParameterService parameterService;

    @Autowired
    BaseParameterGroupService parameterGroupService;

    @Autowired
    BaseSimpleDefinitionService baseSimpleDefinitionService;

    @Override
    public ResultCategory isMatch(QaCategory category, QaStandardRecord record) {
        List<MapperClause> mapperClauseList = qaBaseClauseItemService.findItemsByCategory(category.getId());
        System.out.println("mapperClauseList : " + mapperClauseList);
        Category analysis = dataCategoryHandlerService.analysis(category, mapperClauseList);
        System.out.println("analysis : "+analysis);
        List<ClauseCategory> clauseCategories = analysis.getClauseCategories();
        completeData(clauseCategories);
        System.out.println("clauseCategories :"+clauseCategories);
        analysis.setClauseCategories(clauseCategories);
        return matchClauseItemService.academicIsMatch(analysis, record);
    }

    public void completeData(List<ClauseCategory> clauseCategories) {
        List<BaseSimpleDefinition> baseSimpleDefinitions = baseSimpleDefinitionService.findAllSimpleDefinitions();
        for (ClauseCategory clauseCategory : clauseCategories) {
            for (Clause clause : clauseCategory.getClauses()) {
                for (ClauseItem clauseItem : clause.getClauseItems()) {
                    String itemType = clauseItem.getItemType();
                    if (itemType.equals("select") || itemType.equals("select&text") || itemType.equals("radio")) {
                        String itemRelationId = clauseItem.getItemRelationId();
                        for (BaseSimpleDefinition baseSimpleDefinition : baseSimpleDefinitions) {
                            if (baseSimpleDefinition.getId().equals(itemRelationId)) {
                                clauseItem.setItemRelationName(baseSimpleDefinition.getName());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

}
