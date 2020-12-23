package com.bdjbd.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bdjbd.Filter;
import com.bdjbd.bo.*;
import com.bdjbd.common.util.DateUtil;
import com.bdjbd.common.util.UUIDUtils;
import com.bdjbd.dao.entity.*;
import com.bdjbd.enums.*;
import com.bdjbd.service.matches.MatchClauseCategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * 职称评审
 * @author zhuzhe
 */
@Slf4j
@Service
public class AssessorsService {

    @Autowired
    public QaAcademicRecordService qaAcademicRecordService;

    @Autowired
    public QaAcademicRecordItemService qaAcademicRecordItemService;

    @Autowired
    MatchClauseCategoryService matchClauseCategoryService;

    @Autowired
    QaCategoryService qaCategoryService;

    @Autowired
    QaStandardRecordService qaStandardRecordService;

    @Autowired
    QaStandardRecordItemService qaStandardRecordItemService;

    @Autowired
    private BaseParameterService baseParameterService;

    /**
     * 保存更新评审记录
     */
    //    @Transactional
    public void saveOrUpdateAcademicRecord(String userId) {
        QaStandardRecord record = getQaStandardRecord(userId);
        if (record == null) {
            // 删除之前的参评记录
            qaAcademicRecordService.deleteAndInsertRecords(userId, new ArrayList<>(), new ArrayList<>());
            return;
        }

        /*此处为测试，不提交*/
        //        test(record);
        //        if (true) {
        //            return;
        //        }

        /**
         * 用户进行电脑审核，审核当前级别的职称和比当前职称高一个级别的职称
         */
        List<QaCategory> qaCategoryList = qaCategoryService.findAll();
        QaCategory qaCategoryCur = null;
        for (QaCategory qaCategory : qaCategoryList) {
            if (qaCategory.getId().equals(record.getAttr3())) {
                qaCategoryCur = qaCategory;
                break;
            }
        }

        /*获取当前级别和比当前职称高一个级别的全部职称*/
        Map<String, List<QaCategory>> qaCategories = getQaCategories(record.getTechnologyCategory());
//        System.out.println("职称数据" + qaCategories);
        List<QaAcademicRecord> qaAcademicRecordListAll = new ArrayList<>();
        List<QaAcademicRecordItem> qaAcademicRecordItemListAll = new ArrayList<>();
        // 续任审核
        checkLevel(userId, record, qaCategoryCur, qaCategories, qaAcademicRecordListAll, qaAcademicRecordItemListAll, "level_1");
        // 晋升审核
        checkLevel(userId, record, qaCategoryCur, qaCategories, qaAcademicRecordListAll, qaAcademicRecordItemListAll, "level_2");
        qaAcademicRecordService.deleteAndInsertRecords(userId, qaAcademicRecordListAll, qaAcademicRecordItemListAll);
        log.info("审核结束");
    }

    // 按照level审核
    private void checkLevel(String userId, QaStandardRecord record, QaCategory qaCategoryCur, Map<String, List<QaCategory>> qaCategories, List<QaAcademicRecord> qaAcademicRecordListAll, List<QaAcademicRecordItem> qaAcademicRecordItemListAll, String type) {
        List<QaCategory> qaCategoryList1 = qaCategories.get(type);
        // 根据时间筛选items
        List<QaStandardRecordItem> items2 = getQaStandardRecordItems(record.getItems(), type);
        record.setItems(items2);
        if (qaCategoryList1 != null && qaCategoryList1.size() > 0 && items2.size() > 0) {
            List<QaAcademicRecord> qaAcademicRecordList = new ArrayList<>();
            List<QaAcademicRecordItem> qaAcademicRecordItemList = new ArrayList<>();
            check(userId, record, qaCategoryCur, qaCategoryList1, qaAcademicRecordList, qaAcademicRecordItemList);
            qaAcademicRecordListAll.addAll(qaAcademicRecordList);
            qaAcademicRecordItemListAll.addAll(qaAcademicRecordItemList);
        }
    }

    private void check(String userId, QaStandardRecord record, QaCategory qaCategoryCur, List<QaCategory> list, List<QaAcademicRecord> qaAcademicRecordList, List<QaAcademicRecordItem> qaAcademicRecordItemList) {
        log.info("开始审核...");
        for (QaCategory category : list) {
            // 审核结果
            ResultCategory match = matchClauseCategoryService.isMatch(category, record);
//            log.info("审核结果... {},", match);

            QaAcademicRecord qaAcademicRecord = new QaAcademicRecord();
            qaAcademicRecord.setId(UUIDUtils.generateUuid());
            qaAcademicRecord.setCreateDate(new Date());
            qaAcademicRecord.setModifyDate(new Date());
            qaAcademicRecord.setUserId(userId);
            qaAcademicRecord.setUserName(record.getName());

            String[] split = category.getParent().split(",");
            qaAcademicRecord.setCategoryId(split[0]);
            qaAcademicRecord.setCategoryChildId(split[1]);
            qaAcademicRecord.setCategoryAcademicId(category.getId());
            if (match.getResult()) {
                qaAcademicRecord.setStatus(AcademicCheckStatusEnum.CHECK_WAIT_MAN.getValue());
                qaAcademicRecord.setFirstCheckStatus(ComputerCheckStatusEnum.CHECK_SUCCESS.getValue());
            }
            else {
                qaAcademicRecord.setStatus(AcademicCheckStatusEnum.CHECK_FAIL_COMPUTER.getValue());
                qaAcademicRecord.setFirstCheckStatus(ComputerCheckStatusEnum.CHECK_FAIL.getValue());
            }
            qaAcademicRecord.setFirstCheckDate(new Date());
            qaAcademicRecord.setSecondCheckStatus(ManCheckStatusEnum.CHECK_WAIT.getValue());
            qaAcademicRecord.setSecondCheckDate(null);
            qaAcademicRecord.setMemo("");
            qaAcademicRecord.setCurCategoryId(record.getAttr1());
            qaAcademicRecord.setCurCategoryChildId(record.getAttr2());
            qaAcademicRecord.setCurAcademicId(record.getAttr3());
            qaAcademicRecord.setCurAcademicDate(record.getTechnologyCategoryTime());
            qaAcademicRecord.setAcademicType(getAcademicType(qaCategoryCur, category));
            if (qaAcademicRecord.getCategoryId().equals(qaAcademicRecord.getCurCategoryId())) {
                qaAcademicRecord.setHasAcross(false);
            }
            else {
                qaAcademicRecord.setHasAcross(true);
            }
            // 是否用户申请
            qaAcademicRecord.setHasUserInput(false);

            int checkSuccessNumber = 0;
            for (ResultClauseCategory resultClauseCategory : match.getResultClauseCategories()) {
                QaAcademicRecordItem qaAcademicRecordItem = new QaAcademicRecordItem();
                qaAcademicRecordItem.setId(UUIDUtils.generateUuid());
                qaAcademicRecordItem.setCreateDate(new Date());
                qaAcademicRecordItem.setModifyDate(new Date());
                qaAcademicRecordItem.setRecordId(qaAcademicRecord.getId());
                ClauseCategory clauseCategory = resultClauseCategory.getClauseCategory();
                qaAcademicRecordItem.setClauseGroupId(clauseCategory.getGroupId());
                qaAcademicRecordItem.setHasPass(resultClauseCategory.getResult());
                List<Clause> clauses = resultClauseCategory.getClauseCategory().getClauses();
                if (clauses != null && clauses.size() > 0) {
                    qaAcademicRecordItem.setStandardValue(JSONObject.toJSONString(clauses));
                    qaAcademicRecordItem.setActualValue(JSONObject.toJSONString(clauses));
                }
                qaAcademicRecordItem.setInnerGroup(resultClauseCategory.getClauseCategory().getInnerGroup());
                qaAcademicRecordItem.setInnerGroupHasPass(match.getInnerGroupResult().get(qaAcademicRecordItem.getInnerGroup()));
                qaAcademicRecordItemList.add(qaAcademicRecordItem);
                if (resultClauseCategory.getResult()) {
                    checkSuccessNumber++;
                }
            }
            qaAcademicRecord.setAttr0("" + checkSuccessNumber);
            qaAcademicRecord.setAttr1((match.getResultClauseCategories().size() - checkSuccessNumber) + "");
            qaAcademicRecordList.add(qaAcademicRecord);
        }
    }

    /**
     * 获取用户需要参与评审的record
     * @return
     */
    public QaStandardRecord getQaStandardRecord(String userId) {
        List<QaStandardRecord> qaStandardRecords = qaStandardRecordService.findList(Arrays.asList(Filter.eq("userId", userId)), null);
        if (qaStandardRecords.size() == 0) {
            return null;
        }
        QaStandardRecord record = qaStandardRecords.get(0);
        List<QaStandardRecordItem> items = qaStandardRecordItemService.findList(Arrays.asList(Filter.eq("recordId", record.getId())), null);
        if (items.size() == 0) {
            log.info("items.size() == 0 ; 方法return");
            return null;
        }
        record.setItems(items);
        return record;
    }

    /**
     * 获取需要核对的记录
     * 个人参评、拟参评使用的用户数据：
     * 晋升：使用 现技职类别时间 以后数据
     * 续任：使用 最近一次续任时间 以后数据
     * @return
     */
    private List<QaStandardRecordItem> getQaStandardRecordItems(List<QaStandardRecordItem> items, String type) {
        List<BaseParameter> parameterList = baseParameterService.findAllParameters();
        //Filter.ne("parameterGroupId", "10100") 需要排除此类
        List<QaStandardRecordItem> recordItemList = new ArrayList<>();
        for (QaStandardRecordItem item : items) {
            if (item.getParameterGroupId().equals("10100") || item.getParameterGroupId().equals("1-10100-001")) {
                recordItemList.add(item);
            }
        }
        items.removeAll(recordItemList);
        List<QaStandardRecordItem> items2 = new ArrayList<>();
        Map<String, List<QaStandardRecordItem>> map = getStringListMap(items);
        for (String key : map.keySet()) {
            List<QaStandardRecordItem> itemList = map.get(key);
            Map<Integer, List<QaStandardRecordItem>> map1 = getIntegerListMap(itemList);
            loop:
            for (Integer integer : map1.keySet()) {
                List<QaStandardRecordItem> itemList1 = map1.get(integer);
                for (QaStandardRecordItem item : itemList1) {
                    // 获取日期检查规则 jsonObject
                    JSONObject jsonObject = getJsonObject(parameterList, item);
                    if (jsonObject != null) {
                        // 判断时间检查
                        DateCheck[] dateChecks = jsonObject.getObject("dateCheck", DateCheck[].class);
                        if (dateChecks != null) {
                            for (DateCheck dateCheck : dateChecks) {
                                if (type.equals(dateCheck.getLevel())) {
                                    for (QaStandardRecordItem qaStandardRecordItem : recordItemList) {
                                        if (qaStandardRecordItem.getParameterId().equals(dateCheck.getParameterId())) {
                                            if (!StringUtils.isEmpty(item.getParameterValue())) {
                                                if (!DateCheck.compare(getDateValue(item, dateCheck), qaStandardRecordItem.getParameterValue(), dateCheck.getType())) {
                                                    continue loop;
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                items2.addAll(itemList1);
            }
        }
        return items2;
    }

    /**
     * 获取日期检查规则
     * @return
     */
    private JSONObject getJsonObject(List<BaseParameter> parameterList, QaStandardRecordItem item) {
        JSONObject jsonObject = null;
        BaseParameter baseParameter = getParameter(parameterList, item.getParameterId());
        String attr4 = baseParameter.getAttr4();
        if (!StringUtils.isEmpty(attr4)) {
            List<ParameterCheck> parameterChecks = JSON.parseArray(attr4, ParameterCheck.class);
            ParameterCheck dateCheck = ParameterCheck.getInstance("dateCheck", parameterChecks);
            if (dateCheck != null && dateCheck.isStatus()) {
                jsonObject = dateCheck.getCheckJson();
            }
        }
        return jsonObject;
    }

    /**
     * 获取比较的时间
     * @return
     */
    private String getDateValue(QaStandardRecordItem item, DateCheck dateCheck) {
        String[] split = item.getParameterValue().split("-");
        String value = null;
        if (DateCheck.start(dateCheck.getParam())) {
            value = split[0];
        }
        if (DateCheck.end(dateCheck.getParam())) {
            value = split[1].equalsIgnoreCase("至今") ? DateUtil.dateFormat("yyyy.MM", new Date()) : split[1];
        }
        if (!value.contains(".")) {
            value = value + ".00";
        }
        return value;
    }

    private BaseParameter getParameter(List<BaseParameter> parameterList, String parameterId) {
        for (BaseParameter baseParameter : parameterList) {
            if (baseParameter.getId().equals(parameterId)) {
                return baseParameter;
            }
        }
        return null;
    }

    /**
     * 以orders分类
     * @return
     */
    private Map<Integer, List<QaStandardRecordItem>> getIntegerListMap(List<QaStandardRecordItem> itemList) {
        Map<Integer, List<QaStandardRecordItem>> map1 = new HashMap<>();
        for (QaStandardRecordItem item : itemList) {
            List<QaStandardRecordItem> itemList1 = map1.get(item.getOrders());
            if (itemList1 == null) {
                itemList1 = new ArrayList<>();
            }
            itemList1.add(item);
            map1.put(item.getOrders(), itemList1);
        }
        return map1;
    }

    /**
     * 以groupId分类
     * @return
     */
    private Map<String, List<QaStandardRecordItem>> getStringListMap(List<QaStandardRecordItem> items) {
        Map<String, List<QaStandardRecordItem>> map = new HashMap<>();
        //        List<String> stringList = new ArrayList<>();
        for (QaStandardRecordItem qaStandardRecordItem : items) {
            //            stringList.add(qaStandardRecordItem.getParameterGroupId());
            List<QaStandardRecordItem> itemList = map.get(qaStandardRecordItem.getParameterGroupId());
            if (itemList == null) {
                itemList = new ArrayList<>();
            }
            itemList.add(qaStandardRecordItem);
            map.put(qaStandardRecordItem.getParameterGroupId(), itemList);
        }
        return map;
    }

    private void test(QaStandardRecord record) {
        //        List<QaCategory> qaCategoryList1 = qaCategoryService.findAll();
        //        List<QaCategory> qaCategoryList2 = new ArrayList<>();
        //        List<QaCategory> qaCategoryList3 = new ArrayList<>();
        //        for (QaCategory qaCategory : qaCategoryList1) {
        //            if (qaCategory.getType() == 2) {
        //                qaCategoryList2.add(qaCategory);
        //            }
        //        }
        //
        //        for (QaCategory qaCategory : qaCategoryList2) {
        //            ResultCategory match1 = matchClauseCategoryService.isMatch(qaCategory, record);
        //            if (match1.getResult()) {
        //                System.out.println("评审通过......" + qaCategory.getId());
        //                qaCategoryList3.add(qaCategory);
        //            } else {
        //                System.out.println("评审失败......");
        //            }
        //        }
        //
        //        for (QaCategory qaCategory : qaCategoryList3) {
        //            matchClauseCategoryService.isMatch(qaCategory, record);
        //        }

        // 示例: 教育教学类 -> 教学为主型 -> 讲师(中职) 101001
        QaCategory qaCategory1 = qaCategoryService.find("101001");
        ResultCategory match = matchClauseCategoryService.isMatch(qaCategory1, record);

        //List<QaCategory> qaCategoryList = qaCategoryService.findAll();

        System.out.println(record);

    }

    private String getAcademicType(QaCategory qaCategoryCur, QaCategory category) {
        String academicType = null;
        // category  qaCategoryCur
        if (qaCategoryCur.getAnotherName().contains(CategoryNameEnum.CATEGORY_0.getName())) {
            if (category.getAnotherName().contains(CategoryNameEnum.CATEGORY_0.getName())) {
                academicType = "" + AcademicTypeEnum.ACADEMIC_TYPE_1.getKey();
            }
            else {
                academicType = "" + AcademicTypeEnum.ACADEMIC_TYPE_2.getKey();
            }
        }
        if (qaCategoryCur.getAnotherName().contains(CategoryNameEnum.CATEGORY_1.getName())) {
            if (category.getAnotherName().contains(CategoryNameEnum.CATEGORY_1.getName())) {
                academicType = "" + AcademicTypeEnum.ACADEMIC_TYPE_3.getKey();
            }
            else {
                academicType = "" + AcademicTypeEnum.ACADEMIC_TYPE_4.getKey();
            }
        }
        if (qaCategoryCur.getAnotherName().contains(CategoryNameEnum.CATEGORY_2.getName())) {
            if (category.getAnotherName().contains(CategoryNameEnum.CATEGORY_2.getName())) {
                academicType = "" + AcademicTypeEnum.ACADEMIC_TYPE_5.getKey();
            }
            else {
                academicType = "" + AcademicTypeEnum.ACADEMIC_TYPE_6.getKey();
            }
        }
        if (qaCategoryCur.getAnotherName().contains(CategoryNameEnum.CATEGORY_3.getName())) {
            if (category.getAnotherName().contains(CategoryNameEnum.CATEGORY_3.getName())) {
                academicType = "" + AcademicTypeEnum.ACADEMIC_TYPE_7.getKey();
            }
        }
        return academicType;
    }

    // 获取当前级别和比当前职称高一个级别的全部职称
    private Map<String, List<QaCategory>> getQaCategories(String technologyCategory) {
        Map<String, List<QaCategory>> map = new HashMap<>();
        CategoryNameEnum categoryNameEnum = CategoryNameEnum.getByValue(technologyCategory);
        List<Filter> filters1 = new ArrayList<>();
        filters1.add(Filter.eq("type", CategoryTypeEnum.ACADEMIC.getKey()));
        filters1.add(Filter.eq("anotherName", categoryNameEnum.getName()));
        map.put("level_1", qaCategoryService.findList(filters1, null));
        CategoryNameEnum byId = CategoryNameEnum.getById(categoryNameEnum.getId() + 1);
        if (byId != null) {
            List<Filter> filters2 = new ArrayList<>();
            filters2.add(Filter.eq("type", CategoryTypeEnum.ACADEMIC.getKey()));
            filters2.add(Filter.eq("anotherName", byId.getName()));
            map.put("level_2", qaCategoryService.findList(filters2, null));
        }
        return map;
    }

}
