package com.bdjbd.service.matches.impl;

import com.bdjbd.bo.*;
import com.bdjbd.dao.entity.QaCategory;
import com.bdjbd.service.matches.DataCategoryHandlerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author dbc
 * @version 1.0
 * @className DataHandlerServiceImpl
 * @description TODO
 * @date 2020/2/28
 **/
@Slf4j
@Service
public class DataCategoryHandlerServiceImpl implements DataCategoryHandlerService {

    @Override
    public Category createCategory(QaCategory academic) {
        Category category = new Category();
        category.setId(academic.getId());
        category.setName(academic.getName());
        category.setTitle(academic.getTitle());
        category.setDescription(academic.getDescription());
        category.setGrade(academic.getParent() == null ? 0 : academic.getParent().split(",").length);
        return category;
    }

    @Override
    public ClauseCategory createClauseCategory(String categoryId, MapperClause clause){
        ClauseCategory category = new ClauseCategory();
        category.setCategoryId(categoryId);
        category.setGroupId(clause.getGroupId());
        category.setGroupTitle(clause.getGroupTitle());
        category.setGroupContent(clause.getGroupContent());
        category.setGroupType(clause.getGroupType());
        category.setInnerGroup(clause.getInnerGroup());
        category.setClauseCondition(clause.getGroupCondition());
        return category;
    }

    @Override
    public Clause createClause(String clauseCategoryId, MapperClause mapperClause){
        Clause clause = new Clause();
        clause.setClauseCategoryId(clauseCategoryId);
        clause.setClauseId(mapperClause.getClauseId());
        clause.setClauseTitle(mapperClause.getClauseTitle());
        clause.setClauseContent(mapperClause.getClauseContent());
        clause.setClauseOrders(mapperClause.getClauseOrder());
        clause.setClauseItemCondition(mapperClause.getClauseItemCondition());
        clause.setClauseCode(mapperClause.getClauseCode());
        return clause;
    }

    public ClauseItem createClauseItem(String clauseId, MapperClause mapperClause){
        ClauseItem item = new ClauseItem();
        item.setClauseId(clauseId);
        item.setItemCode(mapperClause.getItemCode());
        item.setItemId(mapperClause.getItemId());
        item.setItemType(mapperClause.getItemType());
        item.setTypeValidate(mapperClause.getTypeValidate());
        item.setRelationValue(mapperClause.getItemValue());
        item.setItemExpress(mapperClause.getItemExpress());
        item.setItemRelationId(mapperClause.getItemRelationId());
        item.setItemRelationCode(mapperClause.getItemRelationCode());
        item.setItemAttr0(mapperClause.getItemAttr0());
        item.setItemAttr1(mapperClause.getItemAttr1());
        item.setItemAttr2(mapperClause.getItemAttr2());
        item.setItemAttr3(mapperClause.getItemAttr3());
        item.setItemAttr4(mapperClause.getItemAttr4());
        return item;
    }

    @Override
    public Category analysis(QaCategory academic, List<MapperClause> mapperClauses) {
        Category category = createCategory(academic);
        category.setClauseCategories(clauseCategoriesHandler(academic.getId(), mapperClauses));
        return category;
    }

    private List<ClauseCategory> clauseCategoriesHandler(String categoryId, List<MapperClause> mapperClauses){
        List<ClauseCategory> clauseCategories = new ArrayList<>();
        for(Iterator<MapperClause> iterator = mapperClauses.iterator(); iterator.hasNext();){
            MapperClause next = iterator.next();
            if(!isContainsClauseCategory(clauseCategories, next)){
                ClauseCategory clauseCategory = createClauseCategory(categoryId, next);
                clauseCategory.setClauses(clausesHandler(clauseCategory.getGroupId(), mapperClauses));
                clauseCategories.add(clauseCategory);
            }
        }
        return clauseCategories;
    }

    private Boolean isContainsClauseCategory(List<ClauseCategory> clauseCategories, MapperClause mapperClause){
        for(Iterator<ClauseCategory> iterator = clauseCategories.iterator(); iterator.hasNext();){
            ClauseCategory next = iterator.next();
            if(mapperClause.getGroupId().equals(next.getGroupId())){
                return true;
            }
        }
        return false;
    }

    private List<Clause> clausesHandler(String clauseCategoryId, List<MapperClause> mapperClauses){
        List<Clause> clauses = new ArrayList<>();
        for(Iterator<MapperClause> iterator = mapperClauses.iterator(); iterator.hasNext();){
            MapperClause next = iterator.next();
            if(next.getGroupId().equals(clauseCategoryId)){
                if(!isContainsClause(clauses, next)){
                    Clause clause = createClause(clauseCategoryId, next);
                    clause.setClauseItems(clauseItemsHandler(clause.getClauseId(), mapperClauses));
                    clauses.add(clause);
                }
            }
        }
        return clauses;
    }

    private Boolean isContainsClause(List<Clause> clauses, MapperClause mapperClause){
        for (Iterator<Clause> iterator = clauses.iterator(); iterator.hasNext();){
            Clause next = iterator.next();
            if(mapperClause.getClauseId().equals(next.getClauseId())){
                return true;
            }
        }
        return false;
    }

    private List<ClauseItem> clauseItemsHandler(String clauseId, List<MapperClause> mapperClauses){
        List<ClauseItem> clauseItems = new ArrayList<>();
        for(Iterator<MapperClause> iterator = mapperClauses.iterator(); iterator.hasNext();){
            MapperClause next = iterator.next();
            if(next.getClauseId().equals(clauseId)){
                if(!isContainsClauseItem(clauseItems, next)){
                    ClauseItem clauseItem = createClauseItem(clauseId, next);
                    clauseItems.add(clauseItem);
                }
            }
        }
        return clauseItems;
    }

    private Boolean isContainsClauseItem(List<ClauseItem> clauseItems, MapperClause mapperClause){
        for (Iterator<ClauseItem> iterator = clauseItems.iterator(); iterator.hasNext(); ) {
            ClauseItem next = iterator.next();
            if (mapperClause.getItemId().equals(next.getItemId()) &&
                    mapperClause.getItemRelationCode().equals(next.getItemRelationCode())) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Map<String, List<ClauseCategory>> relationalGrouping(QaCategory academic, List<ClauseCategory> clauseCategories) {
        Map<String, List<ClauseCategory>> result = new HashMap<>();
        for(Iterator<ClauseCategory> iterator = clauseCategories.iterator(); iterator.hasNext();){
            ClauseCategory item = iterator.next();
            List<ClauseCategory> temp;
            if(!result.containsKey(item.getInnerGroup())){
                temp = new ArrayList<>();
                result.put(item.getInnerGroup(), temp);
            }else{
                temp = result.get(item.getInnerGroup());
            }
            temp.add(item);
        }
        return result;
    }

}
