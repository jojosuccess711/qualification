/**
 * @filename:QaAcademicRecordController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.Filter;
import com.bdjbd.bo.MapperClause;
import com.bdjbd.common.util.ClassUtil;
import com.bdjbd.dao.entity.*;
import com.bdjbd.enums.AcademicTypeEnum;
import com.bdjbd.enums.CategoryNameEnum;
import com.bdjbd.enums.ComputerCheckStatusEnum;
import com.bdjbd.service.*;
import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.service.sys.SysConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 *
 * @Description: 职称评审记录接口层
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Slf4j
@Api(value = "V2职称评审记录 - Controller", description = "V2职称评审记录", tags = {"V2职称评审记录"})
@RestController
@RequestMapping("/admin//qaAcademicRecord")
public class QaAcademicRecordController {

    @Autowired
    public QaAcademicRecordService qaAcademicRecordService;

    @Autowired
    SysAdminService adminService;

    @Autowired
    public QaCategoryService qaCategoryService;

    @Autowired
    QaStandardRecordService qaStandardRecordService;

    @Autowired
    QaAcademicRecordItemService qaAcademicRecordItemService;

    @Autowired
    SysConfigService sysConfigService;

    @Autowired
    QaBaseClauseItemService qaBaseClauseItemService;

    /**
     * @return Message<QaAcademicRecord>
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取职称评审记录信息", notes = "获取职称评审记录信息[qaAcademicRecord]，作者：DBC")
    @ApiImplicitParam(name = "id", value = "职称评审记录id", paramType = "path", dataType = "String")
    public Message<QaAcademicRecord> find(@PathVariable("id") String id) {
        try {
            QaAcademicRecord qaAcademicRecord = qaAcademicRecordService.find(id);
            if (qaAcademicRecord != null) {
                return Message.success(qaAcademicRecord);
            }
            else {
                log.error("获取职称评审记录失败ID：" + id);
            }
        } catch (Exception e) {
            log.error("获取职称评审记录执行异常：", e);
        }
        return Message.error("信息不存在");
    }

    /**
     * @return Message<QaAcademicRecord>
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加职称评审记录", notes = "添加职称评审记录[qaAcademicRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "姓名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "五大类id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryChildId", value = "领域id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryAcademicId", value = "职称id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态 1：审核通过 10：待提交审核（默认） 20：待电脑审核 21：电脑审核未通过 30：待人工审核（电脑审核通过） 31：人工审核未通过", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "firstCheckStatus", value = " 电脑审核状态 0：初始化状态（默认） 1：审核通过 2:  待审核 3：电脑审核未通过", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "firstCheckDate", value = "初审时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "secondCheckStatus", value = "人工审核状态  0：初始状态 1：审核通过 2：待人工审核 3：人工审核未通过", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "secondCheckDate", value = "人工审核时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "memo", value = "审核备注", paramType = "query", dataType = "String"),
    })
    public Message<QaAcademicRecord> add(QaAcademicRecord qaAcademicRecord) {
        try {
            int rg = qaAcademicRecordService.save(qaAcademicRecord);
            if (rg > 0) {
                return Message.success(qaAcademicRecord);
            }
            else {
                log.error("添加职称评审记录执行失败：" + qaAcademicRecord.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("添加职称评审记录执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<Object>
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除职称评审记录", notes = "删除职称评审记录, 作者：DBC")
    @ApiImplicitParam(name = "id", value = "职称评审记录id", paramType = "query", required = true, dataType = "String")
    public Message<Object> delete(String id) {
        try {
            int reg = qaAcademicRecordService.delete(id);
            if (reg > 0) {
                return Message.success(id);
            }
            else {
                log.error("删除职称评审记录失败ID：" + id);
                return Message.error("操作失败，请稍后刷新重试");
            }
        } catch (Exception e) {
            log.error("删除职称评审记录执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<QaAcademicRecord>
     */
    @ApiOperation(value = "修改职称评审记录", notes = "修改职称评审记录[qaAcademicRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "用户id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "姓名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "五大类id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryChildId", value = "领域id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryAcademicId", value = "职称id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "status", value = "状态 1：审核通过 10：待提交审核（默认） 20：待电脑审核 21：电脑审核未通过 30：待人工审核（电脑审核通过） 31：人工审核未通过", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "firstCheckStatus", value = " 电脑审核状态 0：初始化状态（默认） 1：审核通过 2:  待审核 3：电脑审核未通过", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "firstCheckDate", value = "初审时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "secondCheckStatus", value = "人工审核状态  0：初始状态 1：审核通过 2：待人工审核 3：人工审核未通过", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "secondCheckDate", value = "人工审核时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "memo", value = "审核备注", paramType = "query", dataType = "String"),
    })
    @PostMapping("/update")
    public Message<QaAcademicRecord> update(QaAcademicRecord qaAcademicRecord) {
        try {
            int reg = qaAcademicRecordService.update(qaAcademicRecord);
            if (reg > 0) {
                return Message.success(qaAcademicRecord);
            }
            else {
                log.error("修改职称评审记录失败ID：" + qaAcademicRecord.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("修改职称评审记录执行异常：" + e.getMessage());
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Page<QaAcademicRecord>
     */
    @ApiOperation(value = "分页条件查询职称评审记录", notes = "条件查询[qaAcademicRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", required = true, dataType = "Int", example = "5")
    })
    @PostMapping("/page")
    public Message<Page<QaAcademicRecord>> findPage(Pageable pageable) {
        try {
            Page<QaAcademicRecord> page = qaAcademicRecordService.findPage(pageable);
            return Message.success(page);
        } catch (Exception e) {
            log.error("获取职称评审记录执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "电脑审核通过职称详情信息", notes =
            "电脑审核通过职称详情信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", dataType = "Int", example = "10"),
            @ApiImplicitParam(name = "category", value = "大类", paramType =
                    "query", dataType = "String", example = "教育教学类"),
            @ApiImplicitParam(name = "subCategory", value = "小类", paramType =
                    "query", dataType = "String", example = "教学为主型"),
            @ApiImplicitParam(name = "userName", value = "姓名", paramType = "query", dataType = "String", example = "5"),
            @ApiImplicitParam(name = "jobTitle", value = "申请职称: 正高,副高,中职",
                    paramType = "query", dataType = "String", example = "正高"),
    })
    @PostMapping("/findRecords")
    public Message findRecords(@RequestParam(name = "pageNum", defaultValue =
            "1") Integer pageNum,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer
                    pageSize, String category, String subCategory, String
            userName, String jobTitle) {
        return qaAcademicRecordService.findRecords(pageNum, pageSize, category,
                subCategory, userName, jobTitle);
    }

    @ApiOperation(value = "拟参评人员符合人员占比", notes = "拟参评人员符合人员占比")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "curAcademicName", value = "现职称等级",
                    paramType = "query", dataType = "String", example = "副高"),
            @ApiImplicitParam(name = "recordTitle", value = "拟参评职称等级", paramType =
                    "query", dataType = "String", example = "副高晋正高"),
            @ApiImplicitParam(name = "categoryType", value = "目标岗位", paramType =
                    "query", dataType = "String", example = "教学为主型"),
            @ApiImplicitParam(name = "userName", value = "用户名", paramType =
                    "query", dataType = "String", example = "")
    })
    @PostMapping("/findRecordsRate")
    public Message findRecordsRate(String curAcademicName, String recordTitle,
            String categoryType, String userName) {
        return qaAcademicRecordService.findRecordsRate(curAcademicName,
                recordTitle, categoryType, userName);
    }

    @ApiOperation(value = "拟参评人员列表/拟参评符合、不符合人员列表", notes = "拟参评人员列表/拟参评符合、不符合人员列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "curAcademicName", value = "现职称等级",
                    paramType = "query", dataType = "String", example = "副高"),
            @ApiImplicitParam(name = "recordTitle", value = "拟参评职称等级", paramType =
                    "query", dataType = "String", example = "副高晋正高"),
            @ApiImplicitParam(name = "categoryType", value = "目标岗位", paramType =
                    "query", dataType = "String", example = "教学为主型"),
            @ApiImplicitParam(name = "hasPass", value = "是否符合条件： true " +
                    "符合，false 不符合 null 全部", paramType = "query", dataType =
                    "String", example = "true"),
            @ApiImplicitParam(name = "organization", value = "实际工作单位", paramType = "query", dataType =
                    "String", example = "指挥控制对抗系"),
            @ApiImplicitParam(name = "userName", value = "用户名", paramType =
                    "query", dataType = "String", example = "")
    })
    @PostMapping("/findRecordsList")
    public Message findRecordsList(@RequestParam(name = "pageNum",
            defaultValue = "1") Integer pageNum, @RequestParam(name = "pageSize",
            defaultValue = "10") Integer pageSize, String curAcademicName,
            String recordTitle, String categoryType, Boolean hasPass, String
            organization, String userName) {
        return qaAcademicRecordService.findRecordsList(pageNum, pageSize,
                curAcademicName, recordTitle, categoryType, hasPass,
                organization, userName);
    }

    @ApiOperation(value = "拟参评不符合人员占比", notes = "拟参评不符合人员占比")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "curAcademicName", value = "现职称等级",
                    paramType = "query", dataType = "String", example = "副高"),
            @ApiImplicitParam(name = "recordTitle", value = "拟参评职称等级", paramType =
                    "query", dataType = "String", example = "副高晋正高"),
            @ApiImplicitParam(name = "categoryType", value = "目标岗位", paramType =
                    "query", dataType = "String", example = "教学为主型"),
            @ApiImplicitParam(name = "userName", value = "用户姓名", paramType =
                    "query", dataType = "String", example = "用户姓名")
    })
    @PostMapping("/findNotPassRate")
    public Message findNotPassRate(String curAcademicName, String
            recordTitle, String categoryType, String userName) {
        Message notPassRate = qaAcademicRecordService.findNotPassRate(curAcademicName, recordTitle, categoryType, userName);
        Map<String, Object> data = (Map<String, Object>) notPassRate.getData();
        List<Map<?, ?>> list = (List<Map<?, ?>>) data.get("data");
        sortMapList(list);
        return notPassRate;
    }

    @ApiOperation(value = "拟参评不符合人员详情", notes = "拟参评不符合人员详情")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "recordId", value = "职称记录id",
                    paramType = "query", dataType = "String", example = "")
    })
    @PostMapping("/findNotPassDetails")
    public Message findNotPassDetails(String recordId) {
        return qaAcademicRecordService.findNotPassDetails(recordId);
    }

    /**
     * 查询当前登录用户的评审结果
     */
    @ApiOperation(value = "查询当前登录用户的评审结果", notes = "查询当前登录用户的评审结果")
    @ApiImplicitParams({
    })
    @PostMapping("/findUserAcademic")
    public Message findUserAcademic(@RequestHeader("Authorization") String auth) {
        try {
            Map<String, Object> map = new HashMap<>();
            // 检验该功能是否可使用  未开始申请参评
            Message<?> message = sysConfigService.checkStartDateEndDate("COMMIT_ACADEMIC_CATEGORY");
            if (message != null && message.getMessage().equals("该功能目前暂未开放")) {
                map.put("status", false);
                map.put("msg", "未开始申请参评");
                return Message.success(map);
            }
            String userId = adminService.findUserIdByToken(auth);
            QaAcademicRecord qaAcademicRecord = getQaAcademicRecord(userId);
            if (qaAcademicRecord == null) {
                map.put("status", false);
                map.put("msg", "未申请参评");
                return Message.success(map);
            }
            map.put("status", true);
            map.put("record", qaAcademicRecord);
            return Message.success(map);
        } catch (Exception e) {
            log.error("查询当前登录用户的评审结果失败, {}", e);
        }
        return Message.error("查询失败");
    }

    @ApiOperation(value = "查询当前登录用户的参评选项", notes = "查询当前登录用户的参评选项")
    @ApiImplicitParams({
    })
    @PostMapping("/findUserCategory")
    public Message findUserCategory(@RequestHeader("Authorization") String auth) {
        try {
            String userId = adminService.findUserIdByToken(auth);

            // 查询全部的 5大类 17小类
            List<QaCategory> listDetails = qaCategoryService.findListDetails(false);

            // 查询用户当前职称
            List<QaStandardRecord> qaStandardRecordList = qaStandardRecordService.findList(Arrays.asList(Filter.eq("userId", userId)), null);
            if (qaStandardRecordList.size() == 0) {
                return Message.success(listDetails);
            }
            QaStandardRecord qaStandardRecord = qaStandardRecordList.get(0);
            CategoryNameEnum categoryNameEnum = CategoryNameEnum.getByValue(qaStandardRecord.getTechnologyCategory());
            CategoryNameEnum categoryNameEnum1 = CategoryNameEnum.getById(categoryNameEnum.getId() + 1);
            List<String> list = new ArrayList<>();
            list.add(categoryNameEnum.getName());
            if (categoryNameEnum1 != null) {
                list.add(categoryNameEnum1.getName());
            }
            for (QaCategory listDetail : listDetails) {
                for (QaCategory qaCategory : listDetail.getChildList()) {
                    List<QaCategory> qaCategoryList = new ArrayList<>();
                    for (QaCategory category : qaCategory.getChildList()) {
                        setAnotherName(list, qaCategoryList, category);
                    }
                    qaCategory.setChildList(qaCategoryList);
                }
            }
            return Message.success(listDetails);
        } catch (Exception e) {
            log.error("查询失败，{}", e);
        }
        return Message.error("查询失败");
    }

    /**
     * 申请参评使用
     * @param auth  changpeng
     * @return
     */
    @ApiOperation(value = "查询当前登录用户的参评选项-申请参评使用", notes = "查询当前登录用户的参评选项-申请参评使用")
    @ApiImplicitParams({
    })
    @PostMapping("/findUserCategory1")
    public Message findUserCategory1(@RequestHeader("Authorization") String auth) {
        try {
            String userId = adminService.findUserIdByToken(auth);

            // 查询全部的 17小类
            List<QaCategory> listDetails = qaCategoryService.findListDetails1(false,userId);

            // 查询用户当前职称
            List<QaStandardRecord> qaStandardRecordList = qaStandardRecordService.findList(Arrays.asList(Filter.eq("userId", userId)), null);
            if (qaStandardRecordList.size() == 0) {
                return Message.success(listDetails);
            }
            QaStandardRecord qaStandardRecord = qaStandardRecordList.get(0);
            CategoryNameEnum categoryNameEnum = CategoryNameEnum.getByValue(qaStandardRecord.getTechnologyCategory());
            CategoryNameEnum categoryNameEnum1 = CategoryNameEnum.getById(categoryNameEnum.getId() + 1);
            List<String> list = new ArrayList<>();
            list.add(categoryNameEnum.getName());
            if (categoryNameEnum1 != null) {
                list.add(categoryNameEnum1.getName());
            }
            for (QaCategory listDetail : listDetails) {
                List<QaCategory> qaCategoryList = new ArrayList<>();
                for (QaCategory qaCategory : listDetail.getChildList()) {
                    setAnotherName(list, qaCategoryList, qaCategory);
                }
                listDetail.setChildList(qaCategoryList);
            }
            return Message.success(listDetails);
        } catch (Exception e) {
            log.error("查询失败，{}", e);
        }
        return Message.error("查询失败");
    }
    /**
     * 前端展示 {@link AcademicTypeEnum}
     */
    private void setAnotherName(List<String> list, List<QaCategory> qaCategoryList, QaCategory category) {
        if (list.get(0).equals(category.getAnotherName())) {
            if (CategoryNameEnum.CATEGORY_0.getName().equals(category.getAnotherName())) {
                category.setAnotherName(AcademicTypeEnum.ACADEMIC_TYPE_1.getValue());
            }
            if (CategoryNameEnum.CATEGORY_1.getName().equals(category.getAnotherName())) {
                category.setAnotherName(AcademicTypeEnum.ACADEMIC_TYPE_3.getValue());
            }
            if (CategoryNameEnum.CATEGORY_2.getName().equals(category.getAnotherName())) {
                category.setAnotherName(AcademicTypeEnum.ACADEMIC_TYPE_5.getValue());
            }
            if (CategoryNameEnum.CATEGORY_3.getName().equals(category.getAnotherName())) {
                category.setAnotherName(AcademicTypeEnum.ACADEMIC_TYPE_7.getValue());
            }
            qaCategoryList.add(category);
        }
        if (list.size() > 1 && list.get(1).equals(category.getAnotherName())) {
            if (CategoryNameEnum.CATEGORY_1.getName().equals(category.getAnotherName())) {
                category.setAnotherName(AcademicTypeEnum.ACADEMIC_TYPE_2.getValue());
            }
            if (CategoryNameEnum.CATEGORY_2.getName().equals(category.getAnotherName())) {
                category.setAnotherName(AcademicTypeEnum.ACADEMIC_TYPE_4.getValue());
            }
            if (CategoryNameEnum.CATEGORY_3.getName().equals(category.getAnotherName())) {
                category.setAnotherName(AcademicTypeEnum.ACADEMIC_TYPE_6.getValue());
            }
            qaCategoryList.add(category);
        }
    }

    @ApiOperation(value = "保存用户想要申请的职称", notes = "保存用户想要申请的职称")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "职称id"),
    })
    @PostMapping("/saveUserCategory")
    public Message<?> saveUserCategory(@RequestHeader("Authorization") String auth, String categoryId) {
        try {

            // 检验该功能是否可使用
            Message<?> message = sysConfigService.checkStartDateEndDate("COMMIT_ACADEMIC_CATEGORY");
            if (message != null)
                return message;

            String userId = adminService.findUserIdByToken(auth);
            if (StringUtils.isEmpty(categoryId)) {
                return Message.error("职称不能为空");
            }
            List<QaStandardRecord> qaStandardRecordList = qaStandardRecordService.findList(Arrays.asList(Filter.eq("userId", userId)), null);
            if (qaStandardRecordList.size() == 0) {
                // 用户没有填写，返回全部的
                return getMessage(categoryId);
            }
            QaStandardRecord qaStandardRecord = qaStandardRecordList.get(0);
            qaStandardRecord.setUserWantCategoryTitleId(categoryId);
            qaStandardRecord.setModifyDate(new Date());
            int i = qaStandardRecordService.saveUserCategory(qaStandardRecord);
            if (i == 0) {
                return getMessage(categoryId);
            }
            // 查询用户的申请结果并返回
            QaAcademicRecord qaAcademicRecord = getQaAcademicRecord(userId);
            return Message.success(qaAcademicRecord);
        } catch (Exception e) {
            log.error("保存失败，{}", e);
        }
        return Message.error("保存失败");
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "姓名"),
            @ApiImplicitParam(name = "category", value = "岗位分类"),
            @ApiImplicitParam(name = "categoryType", value = "岗位"),
            @ApiImplicitParam(name = "technologyCategory", value = "职称"),
            @ApiImplicitParam(name = "attr0", value = "工作单位"),
            @ApiImplicitParam(name = "pageNum", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小"),
    })
    @ApiOperation(value = "获取已提交人员信息列表", notes = "获取已提交人员信息列表")
    @PostMapping("/findUserCommittedRecords")
    public Message findUserCommittedRecords(String name, String category,
            String categoryType, String technologyCategory, String atrr0,
            @RequestParam
                    (required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "12") Integer
                    pageSize) {
        return qaAcademicRecordService.findUserCommittedRecords(name,
                category, categoryType, technologyCategory, atrr0, pageNum, pageSize);
    }

    @ApiOperation(value = "获取已提交人员信息详情", notes = "获取已提交人员信息详情")
    @GetMapping("/findUserCommittedDetails")
    public Message findUserCommittedDetails(String userId) {
        return qaAcademicRecordService.findUserCommittedDetails(userId);
    }

    private Message<?> getMessage(String categoryId) {
        List<MapperClause> mapperClauseList = qaBaseClauseItemService.findItemsByCategory(categoryId);
        List<Map<?, ?>> maps = new ArrayList<>();
        Map<String, String> stringMap = new HashMap<>();
        for (MapperClause mapperClause : mapperClauseList) {
            stringMap.put(mapperClause.getGroupId(), mapperClause.getGroupTitle());
        }
        for (String s : stringMap.keySet()) {
            Map<String, String> map = new HashMap<>();
            map.put("clauseGroupId", s);
            map.put("clauseGroupName", stringMap.get(s));
            maps.add(map);
        }
        sortMapList(maps);
        QaAcademicRecord qaAcademicRecord = new QaAcademicRecord();
        qaAcademicRecord.setNotPassGroups(maps);
        return Message.success(qaAcademicRecord);
    }

    /*查询用户的申请结果并返回*/
    private QaAcademicRecord getQaAcademicRecord(String userId) {
        List<Filter> filters = new ArrayList<>();
        filters.add(Filter.eq("userId", userId));
        filters.add(Filter.eq("hasUserInput", Boolean.TRUE));
        List<QaAcademicRecord> list = qaAcademicRecordService.findList(filters, null);
        if (list.size() == 0) {
            return null;
        }
        QaAcademicRecord qaAcademicRecord = list.get(0);
        if (qaAcademicRecord.getFirstCheckStatus() != ComputerCheckStatusEnum.CHECK_SUCCESS.getValue()) {
            List<QaAcademicRecordItem> notPassDetails = qaAcademicRecordItemService.findNotPassDetails(qaAcademicRecord.getId());
            List<Map<?, ?>> maps = ClassUtil.listBeanToMap(true, notPassDetails, "clauseGroupId", "clauseGroupName");
            sortMapList(maps);
            qaAcademicRecord.setNotPassGroups(maps);
        }
        return qaAcademicRecord;
    }

    private void sortMapList(List<Map<?, ?>> maps) {
        Collections.sort(maps, new Comparator<Map<?, ?>>() {
            @Override
            public int compare(Map<?, ?> o1, Map<?, ?> o2) {
                return o1.get("clauseGroupId").toString().compareTo(o2.get("clauseGroupId").toString());
            }
        });
    }


}