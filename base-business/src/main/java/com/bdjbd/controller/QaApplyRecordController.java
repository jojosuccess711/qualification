/**
 * @filename:QaApplyRecordController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;


import com.alibaba.fastjson.JSON;
import com.bdjbd.Filter;
import com.bdjbd.common.util.jwt.IJWTInfo;
import com.bdjbd.dao.entity.*;
import com.bdjbd.service.*;
import com.bdjbd.service.sys.SysAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.dao.entity.QaApplyRecord;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import java.util.*;

/**
 *
 * @Description: 申请记录接口层
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Slf4j
@Api(value = "申请记录 - Controller", description = "申请记录", tags = {"申请记录"})
@RestController
@RequestMapping("/admin/qaApplyRecord")
public class QaApplyRecordController {

    @Autowired
    public QaApplyRecordService qaApplyRecordService;

    @Autowired
    public QaUserInfoService qaUserInfoService;

    @Autowired
    public QaCategoryService qaCategoryService;

    @Autowired
    private QaApplyRecordAttributeService qaApplyRecordAttributeService;

    @Autowired
    SysAdminService adminService;

    /**
     * @return Message<QaApplyRecord>
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取申请记录信息", notes = "获取申请记录信息[qaApplyRecord]，作者：DBC")
    @ApiImplicitParam(name = "id", value = "申请记录id", paramType = "path", dataType = "String")
    public Message<QaApplyRecord> find(@PathVariable("id") String id) {
        try {
            QaApplyRecord qaApplyRecord = qaApplyRecordService.find(id);
            if (qaApplyRecord != null) {
                return Message.success(qaApplyRecord);
            }
            else {
                log.error("获取申请记录失败ID：" + id);
            }
        } catch (Exception e) {
            log.error("获取申请记录执行异常：", e);
        }
        return Message.error("信息不存在");
    }

    /**
     * @return Message<QaApplyRecord>
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加申请记录", notes = "添加申请记录[qaApplyRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "申请用户（qa_user_info表主键）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "申请用户姓名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "申请领域（qa_category表主键）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryChildId", value = "申请岗位（qa_category表主键）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "academicTitle", value = "申请职称 1：教授   2：副教授", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "firstApproveStatus", value = "初审状态  0：未通过 1：通过 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "firstApproveTime", value = "初审时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "secondApproveStatus", value = "复审状态  0：未通过 1：通过   2：审批中 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "secondApproveTime", value = "复审时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "approveUserId", value = "审批人id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "approveUserName", value = "审批人姓名", paramType = "query", dataType = "String"),
    })
    public Message<QaApplyRecord> add(QaApplyRecord qaApplyRecord) {
        try {
            int rg = qaApplyRecordService.save(qaApplyRecord);
            if (rg > 0) {
                return Message.success(qaApplyRecord);
            }
            else {
                log.error("添加申请记录执行失败：" + qaApplyRecord.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("添加申请记录执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<Object>
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除申请记录", notes = "删除申请记录, 作者：DBC")
    @ApiImplicitParam(name = "id", value = "申请记录id", paramType = "query", required = true, dataType = "String")
    public Message<Object> delete(String id) {
        try {
            int reg = qaApplyRecordService.delete(id);
            if (reg > 0) {
                return Message.success(id);
            }
            else {
                log.error("删除申请记录失败ID：" + id);
                return Message.error("操作失败，请稍后刷新重试");
            }
        } catch (Exception e) {
            log.error("删除申请记录执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<QaApplyRecord>
     */
    @ApiOperation(value = "修改申请记录", notes = "修改申请记录[qaApplyRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "createDate", value = "创建时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userId", value = "申请用户（qa_user_info表主键）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "userName", value = "申请用户姓名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryId", value = "申请领域（qa_category表主键）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "categoryChildId", value = "申请岗位（qa_category表主键）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "academicTitle", value = "申请职称 1：教授   2：副教授", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "firstApproveStatus", value = "初审状态  0：未通过 1：通过 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "firstApproveTime", value = "初审时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "secondApproveStatus", value = "复审状态  0：未通过 1：通过   2：审批中 ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "secondApproveTime", value = "复审时间", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "approveUserId", value = "审批人id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "approveUserName", value = "审批人姓名", paramType = "query", dataType = "String"),
    })
    @PostMapping("/update")
    public Message<QaApplyRecord> update(QaApplyRecord qaApplyRecord) {
        try {
            int reg = qaApplyRecordService.update(qaApplyRecord);
            if (reg > 0) {
                return Message.success(qaApplyRecord);
            }
            else {
                log.error("修改申请记录失败ID：" + qaApplyRecord.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("修改申请记录执行异常：" + e.getMessage());
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Page<QaApplyRecord>
     */
    @ApiOperation(value = "分页条件查询申请记录", notes = "条件查询[qaApplyRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", required = true, dataType = "Int", example = "5")
    })
    @PostMapping("/page")
    public Message<Page<QaApplyRecord>> findPage(Pageable pageable) {
        try {
            Page<QaApplyRecord> page = qaApplyRecordService.findPage(pageable);
            return Message.success(page);
        } catch (Exception e) {
            log.error("获取申请记录执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<QaApplyRecord>
     */
    @PostMapping("/summit")
    @ApiOperation(value = "保存/修改资料", notes = "保存资料[qaApplyRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryId", value = "申请领域（qa_category表主键）", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "clause", value = "选择结果json ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "operateType", value = "操作类型  1：新增   2：修改 ", paramType = "query", dataType = "String"),})
    public Message<QaApplyRecord> summit(@RequestHeader("Authorization") String token, QaApplyRecord qaApplyRecord, String clause, Integer operateType) {
        try {
            IJWTInfo info = adminService.findByToken(token);
            if (StringUtils.isEmpty(clause)) {
                return Message.error("数据不全");
            }
            Map<String, String> list = (Map) JSON.parse(clause);
            //判断基本资料是否已填写，未填写是不允许申请职称
            QaUserInfo qaUserInfo = qaUserInfoService.find(info.getId());
            if (qaUserInfo.getName().isEmpty() || qaUserInfo.getAcademicTitle().isEmpty()) {
                return Message.error("个人基本资料不完整，请前去补充");
            }
            //如前端无法判断是新增或修改，系统自行判断
            List<QaApplyRecord> qaApplyRecordList = qaApplyRecordService.findByUserAndCategory(info.getId(), qaApplyRecord.getCategoryId());
            if (qaApplyRecordList != null && !qaApplyRecordList.isEmpty()) {
                operateType = 2;
            }
            else {
                operateType = 1;
            }
            int reg = qaApplyRecordService.createList(info.getId(), info.getName(), qaApplyRecord.getCategoryId(), list, operateType);
            //qaApplyRecord = qaApplyRecordService.create(qaApplyRecord.getUserId(), qaApplyRecord.getUserName(), qaApplyRecord.getCategoryId(), qaApplyRecord.getCategoryChildId(), qaApplyRecord.getCategoryAcademicId(), list, operateType);
            return Message.success(qaApplyRecord);
        } catch (Exception e) {
            log.error("申请提交执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<QaApplyRecord>
     */
    @PostMapping("/edit")
    @ApiOperation(value = "修改审核资料(弃用)", notes = "修改审核资料（仅可保存变提交，或修改提交的选择结果）[qaApplyRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "clause", value = "选择结果json ", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "operateType", value = "操作类型  1：保存审核信息   2：提交审核申请 ", paramType = "query", dataType = "String"),})
    public Message<QaApplyRecord> edit(QaApplyRecord qaApplyRecord, String clause, Integer operateType) {
        try {
            if (StringUtils.isEmpty(clause)) {
                return Message.error("数据不全");
            }
            Map<String, String> list = (Map) JSON.parse(clause);
            return qaApplyRecordService.edit(qaApplyRecord.getId(), list, operateType);
        } catch (Exception e) {
            log.error("申请修改执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "已审核人员信息/待审核人员信息/人员职称表", notes = "已审核人员信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", dataType = "Int", example = "5"),
            @ApiImplicitParam(name = "category", value = "大类", paramType = "query", dataType = "String", example = "5"),
            @ApiImplicitParam(name = "subCategory", value = "小类", paramType = "query", dataType = "String", example = "5"),
            @ApiImplicitParam(name = "userName", value = "姓名", paramType = "query", dataType = "String", example = "5"),
            @ApiImplicitParam(name = "jobTitle", value = "申请职称: 正高,副高,中职", paramType = "query", dataType = "String", example = "5"),
            @ApiImplicitParam(name = "approveTime", value = "受审时间:日期，不需要时分秒", paramType = "query", dataType = "String", example = "2020-02-22"),
            @ApiImplicitParam(name = "queryType", value = "查询类型：1 已审核人员信息/ 2 待审核人员信息/ 3 人员职称表", paramType = "query", dataType = "Int", example = "1"),
    })
    @PostMapping("/findList")
    public Message<?> findList(Integer pageNum, Integer pageSize, String category, String subCategory,
            String userName, String jobTitle, String approveTime, Integer approveResult, Integer queryType) {
        try {
            if (pageNum == null) {
                pageNum = 1;
            }
            if (pageSize == null) {
                pageSize = 10;
            }
            if (queryType == null) {
                return Message.error("查询类型不能空");
            }
            List<Integer> approveResultList = null;
            if (queryType == 1) {
                approveResultList = new ArrayList<>();
                approveResultList.add(0);
                approveResultList.add(1);
            }
            else if (queryType == 2) {
                approveResultList = new ArrayList<>();
                approveResultList.add(2);
            }
            List<QaApplyRecord> list = qaApplyRecordService
                    .findList(pageNum, pageSize, category, subCategory, userName, jobTitle, approveTime, null, approveResultList);

            Integer count = qaApplyRecordService.countList(category, subCategory, userName, jobTitle, approveTime, null, approveResultList);
            if (count == null) {
                count = 0;
            }

            if (queryType == 3 && list.size() > 0) {
                List<String> userIds = new ArrayList<>();
                for (QaApplyRecord qaApplyRecord : list) {
                    userIds.add(qaApplyRecord.getUserId());
                }
                List<Filter> filters = new ArrayList<>();
                filters.add(Filter.in("id", userIds));
                List<QaUserInfo> userInfoList = qaUserInfoService.findList(list.size(), filters, null);
                for (QaApplyRecord qaApplyRecord : list) {
                    for (QaUserInfo qaUserInfo : userInfoList) {
                        if (qaUserInfo.getId().equals(qaApplyRecord.getUserId())) {
                            qaApplyRecord.setTechnologyTitle(qaUserInfo.getTechnologyTitle());
                            break;
                        }
                    }
                }
            }
            Map<String, Object> map = new HashMap<>();
            map.put("count", count);
            map.put("list", list);
            return Message.success(map);
        } catch (Exception e) {
            log.error("已审核人员信息：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<QaApplyRecord>
     */
    @PostMapping("/approve")
    @ApiOperation(value = "人工审核通过/不通过", notes = "人工审核通过/不通过）[qaApplyRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "applyId", value = "申请记录Id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "opinion", value = "审核建议", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "是否通过 0：不通过  1：通过  ", paramType = "query", dataType = "String"),})
    public Message<QaApplyRecord> approve(String applyId, String opinion, Integer type) {
        try {
            return qaApplyRecordService.approve(applyId, opinion, type);
        } catch (Exception e) {
            log.error("申请修改执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "个人首页-通过登录用户信息获取申请列表", notes = "个人首页-通过登录用户信息获取申请列表")
    @PostMapping("/personalPage")
    public Message<Page<QaApplyRecord>> findPersonalPage(@RequestHeader("Authorization") String authorization) {
        try {
            String userId = adminService.findUserIdByToken(authorization);
            return qaApplyRecordService.findPersonalPage(userId, null, null);
        } catch (Exception e) {
            log.error("获取申请记录执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "登录系统，已申请未申请标签提示", notes = "登录系统，已申请未申请标签提示")
    @PostMapping("/personalApplies")
    public Message findPersonalApplies(@RequestHeader("Authorization") String authorization) {
        try {
            String userId = adminService.findUserIdByToken(authorization);
            return qaApplyRecordService.findPersonalApplies(userId);
        } catch (Exception e) {
            log.error("获取申请记录执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * 审核详情
     * @return
     */
    @ApiOperation(value = "审核详情", notes = "审核详情")
    @PostMapping("/auditDetails")
    @ApiImplicitParam(name = "id", value = "申请审核id", paramType = "query", required = true, dataType = "String")
    public Message findAuditDetails(/*@RequestHeader("Authorization")String	authorization,*/
            String id) {
        try {
            QaApplyRecord qaApplyRecord = qaApplyRecordService.findRecordWithAttribute(Filter.eq("id", id));
            return Message.success(qaApplyRecord);
        } catch (Exception e) {
            log.error("获取申请记录执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<QaApplyRecord>
     */
    @PostMapping("/approveDetails")
    @ApiOperation(value = "申请审核详情查看（初审失败原因）", notes = "审核详情查看[qaApplyRecord], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "申请记录Id", paramType = "query", dataType = "String"),})
    public Message<QaApplyRecord> approveDetails(String id) {
        try {
            return qaApplyRecordService.approveDetails(id);
        } catch (Exception e) {
            log.error("申请修改执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "资料录入-展示条件", notes = "资料录入-展示条件")
    @PostMapping("/applyInfo")
    public Message<?> applyInfo(@RequestHeader("Authorization") String authorization) {
        try {
            IJWTInfo info = adminService.findByToken(authorization);
            List<QaCategory> listDetails = qaCategoryService.findListDetails(true);
            List<QaApplyRecordAttribute> list = qaApplyRecordAttributeService.findByUser(info.getId());
            for (QaCategory listDetail : listDetails) {
                listDetail.setChildList(null);
                for (QaClause qaClause : listDetail.getQaClauseList()) {
                    for (QaApplyRecordAttribute qaApplyRecordAttribute : list) {
                        if (qaClause.getId().equals(qaApplyRecordAttribute.getCaluseId())) {
                            qaClause.setQaApplyRecordAttribute(qaApplyRecordAttribute);
                            listDetail.setHasApply(true);
                            break;
                        }
                    }
                }
            }
            return Message.success(listDetails);
        } catch (Exception e) {
            log.error("资料录入-展示条件：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "信息查询", notes = "信息查询")
    @PostMapping("/query")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userName", value = "申请人姓名", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "phoneNum", value = "申请人电话", paramType = "query", dataType = "String")
    })
    public Message query(String userName, String phoneNum) {
        try {
            return qaApplyRecordService.findPersonalPage(null, userName, phoneNum);
        } catch (Exception e) {
            log.error("获取申请记录执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    @ApiOperation(value = "查询职称申请结果人数列表", notes = "查询职称申请结果人数列表")
    @PostMapping("/findCountListApplyResult")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "approveStatus", value = "审核状态 0：未通过 1：通过 2：审批中", paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "past", value = "是否满足业绩条件要求 1：是 0：否 ", paramType = "query", dataType = "Int"),
            @ApiImplicitParam(name = "categoryAcademicId", value = "申请职称id", paramType = "query", dataType = "String"),
    })
    public Message findCountListApplyResult(Integer approveStatus, Integer past, String categoryAcademicId) {
        try {
            List<QaApplyRecord> applyAndAttrCount =
                    qaApplyRecordService.findApplyAndAttrCount(approveStatus, past, categoryAcademicId);
            if (applyAndAttrCount.size() == 0) {
                return Message.success(Arrays.asList(0));
            }

            List<Integer> countList = new ArrayList<>();
            Set<Integer> countSet = new LinkedHashSet<>();
            Map<Integer, Integer> map = new HashMap<>();
            for (QaApplyRecord qaApplyRecord : applyAndAttrCount) {
                Integer countPast = qaApplyRecord.getCountPast();
                countSet.add(countPast);
                if (map.get(countPast) == null) {
                    map.put(countPast, 1);
                }
                else {
                    map.put(countPast, map.get(countPast) + 1);
                }
            }
            countList.addAll(countSet);
            Collections.sort(countList);
            Integer max = countList.get(countList.size() - 1);
            List<Integer> resList = new ArrayList<>();
            for (Integer i = 0; i <= max; i++) {
                if (map.get(i) == null) {
                    resList.add(0);
                }
                else {
                    resList.add(map.get(i));
                }
            }
            return Message.success(resList);
        } catch (Exception e) {
            log.error("查询职称申请结果人数列表失败：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }
}