package com.bdjbd.service.matches;

import com.bdjbd.bo.*;
import com.bdjbd.dao.entity.QaCategory;

import java.util.List;
import java.util.Map;

/**
 * @author dbc
 * @version 1.0
 * @className DataHandlerService 数据处理
 * @description TODO
 * @date 2020/2/28
 **/
public interface DataCategoryHandlerService {

    /**
     * 创建Category
     * @param academic
     * @return
     */
    Category createCategory(QaCategory academic);

    /**
     * 创建条件组
     * @param categoryId
     * @param clause
     * @return
     */
    ClauseCategory createClauseCategory(String categoryId, MapperClause clause);

    /**
     * 床见条件项
     * @param clauseCategoryId
     * @param mapperClause
     * @return
     */
    Clause createClause(String clauseCategoryId, MapperClause mapperClause);

    /***
     * 数据分析
     * @param academic
     * @return
     */
    Category analysis(QaCategory academic, List<MapperClause> mapperClauses);

    /**
     * 关系分组（职称与条件组, 【默认规则：组内或关系，组间与关系】）
     * @param academic
     * @param clauseCategories
     * @return
     */
    Map<String, List<ClauseCategory>> relationalGrouping(QaCategory academic, List<ClauseCategory> clauseCategories);

}
