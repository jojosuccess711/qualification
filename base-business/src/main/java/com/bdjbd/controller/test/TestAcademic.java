package com.bdjbd.controller.test;

import com.alibaba.fastjson.JSON;
import com.bdjbd.bo.*;
import com.bdjbd.dao.entity.BaseSimpleDefinition;
import com.bdjbd.dao.entity.QaCategory;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.mapper.QaBaseClauseItemMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhuzhe
 * @date 2020/4/9 14:23
 * @email zhuzhe_mail@163.com
 */
@Slf4j
@RestController
public class TestAcademic {

    @Autowired
    private Common common;

    /**
     * 评审测试
     *
     * @param userId
     * @return
     */
    // http://localhost:8091/assessors
    @GetMapping("/assessors")
    public Object test(String userId) {


        userId = "Wwx4YtxU";
//        common.assessorsService.saveOrUpdateAcademicRecord(userId);

        QaStandardRecord record = common.assessorsService.getQaStandardRecord(userId);
        QaCategory category = common.qaCategoryService.find("304001");

        ResultCategory match = common.matchClauseCategoryService.isMatch(category, record);

        return "ok ... ";
    }

    /**
     * 获取某个职称的评审条件
     *
     * @param categoryId
     * @return
     */
    // http://localhost:8091/clause/getByCategory?user=mDa7WSB2&categoryId=303001&groupId=30200101
    @GetMapping("/clause/getByCategory")
    public Object test11(String categoryId, String user, String groupId) {



        QaCategory qaCategory = common.qaCategoryService.find(categoryId);
        /**
         * {@link QaBaseClauseItemMapper#findItemsByCategory(java.lang.String)}
         */
        List<MapperClause> mapperClauseList = common.qaBaseClauseItemService.findItemsByCategory(qaCategory.getId());


        Category analysis = common.dataCategoryHandlerService.analysis(qaCategory, mapperClauseList);

        List<ClauseCategory> clauseCategories = analysis.getClauseCategories();
        completeData(clauseCategories);
//        analysis.setClauseCategories(clauseCategories);

        List<ClauseCategory> newCal = new ArrayList<>();
        for (ClauseCategory clauseCategory : clauseCategories) {
            if (StringUtils.isNotBlank(groupId) && clauseCategory.getGroupId().equals(groupId)) {
                newCal.add(clauseCategory);
            }
        }
//        analysis.setClauseCategories(newCal);

        QaStandardRecord record = common.assessorsService.getQaStandardRecord(user);
        ResultCategory resultCategory = common.matchClauseItemService.academicIsMatch(analysis, record);

        return null;
    }

    public void completeData(List<ClauseCategory> clauseCategories) {
        List<BaseSimpleDefinition> baseSimpleDefinitions = common.baseSimpleDefinitionService.findAll();
        for (ClauseCategory clauseCategory : clauseCategories) {
            for (Clause clause : clauseCategory.getClauses()) {
                for (ClauseItem clauseItem : clause.getClauseItems()) {
                    String itemType = clauseItem.getItemType();
                    if (itemType.equals("select") || itemType.equals("select&text") || itemType.equals("radio")) {
                        String itemRelationId = clauseItem.getItemRelationId();
                        for (BaseSimpleDefinition baseSimpleDefinition : baseSimpleDefinitions) {
                            if (baseSimpleDefinition.getId().equals(itemRelationId)) {
                                clauseItem.setItemRelationName(baseSimpleDefinition.getName());
                                clauseItem.setItemRelationTypeName(baseSimpleDefinition.getMemo());
                                break;
                            }
                        }
                    }
                }
            }
        }
    }


    static String aaa = "[\n" +
            "    {\n" +
            "      \"type\": \"dateCheck\",\n" +
            "      \"desc\": \"评审时需要用到的时间对比检查\",\n" +
            "      \"status\": true,\n" +
            "      \"checkJson\": {\n" +
            "        \"dateCheck\": [\n" +
            "          {\n" +
            "            \"parameterId\": \"10100-032\",\n" +
            "            \"type\": \"after\",\n" +
            "            \"param\": \"start\"\n" +
            "          },\n" +
            "          {\n" +
            "            \"parameterId\": \"10100-025\",\n" +
            "            \"type\": \"after\",\n" +
            "            \"param\": \"start\"\n" +
            "          }\n" +
            "        ]\n" +
            "      }\n" +
            "    },\n" +
            "    {\n" +
            "      \"type\": \"dateOrder\",\n" +
            "      \"desc\": \"保存和展示时根据时间排序\",\n" +
            "      \"status\": true,\n" +
            "      \"checkJson\": {\n" +
            "        \"order\": \"desc\",\n" +
            "        \"param\": \"start\"\n" +
            "      }\n" +
            "    }\n" +
            "  ]";

    public static void main(String[] args) {

        String s = aaa.replaceAll("\n", "")
                .replaceAll(" ", "")
                .replaceAll("", "")
                .replaceAll("", "")
                .replaceAll("", "");

        System.out.println(aaa);

        String s1 = "[{\"type\":\"dateCheck\",\"desc\":\"评审时需要用到的时间对比检查\",\"status\":true,\"checkJson\":{\"dateCheck\":[{\"parameterId\":\"10100-032\",\"type\":\"after\",\"param\":\"start\"},{\"parameterId\":\"10100-025\",\"type\":\"after\",\"param\":\"start\"}]}},{\"type\":\"dateOrder\",\"desc\":\"保存和展示时根据时间排序\",\"status";
        List<ParameterCheck> parameterChecks = JSON.parseArray(s, ParameterCheck.class);


        System.out.println(parameterChecks);
    }
}
