package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationEntity;
import com.bdjbd.service.QaReviewProfessionalEvaluationService;
import com.bdjbd.service.ReviewApplyParticipateService;
import com.bdjbd.service.sys.SysAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 专业技术职务评审一览表
 *
 * @author 83746
 */
@RestController
@RequestMapping("/admin/ProfessionalEvaluation")
@Api(value = "保存专业技术职务评审一览表 - Controller", description = "保存专业技术职务评审一览表", tags = {"保存专业技术职务评审一览表"})
public class ReviewProfessionalEvaluationController {

    @Autowired
    private QaReviewProfessionalEvaluationService qaReviewProfessionalEvaluationService;
    @Autowired
    private SysAdminService adminService;

    @RequestMapping(value = "saveProfessionalEvaluation", method = RequestMethod.POST)
    @ApiOperation(value = "保存专业技术职务评审一览表")
    public Message saveProfessionalEvaluation(@RequestHeader("Authorization") String authorization, QaReviewProfessionalEvaluationEntity entity) {
        //获取用户id
        String userId = adminService.findUserIdByToken(authorization);
        if (StringUtils.isEmpty(userId)) {
            return Message.error("获取用户id为空：{}" + authorization);
        }
        int i = qaReviewProfessionalEvaluationService.saveProfessionalEvaluation(userId, entity);
        if (i > 0) {
            return Message.success("保存信息成功！！");
        }
        return Message.error("保存信息失败！！" + authorization);
    }

    @RequestMapping(value = "submitProfessionalEvaluation", method = RequestMethod.POST)
    @ApiOperation(value = "提交")
    public Message submitProfessionalEvaluation(@RequestHeader("Authorization") String authorization, QaReviewProfessionalEvaluationEntity entity) {
        //获取用户id
        String userId = adminService.findUserIdByToken(authorization);
        if (StringUtils.isEmpty(userId)) {
            return Message.error("获取用户id为空：{}" + authorization);
        }
        int i = qaReviewProfessionalEvaluationService.submitProfessionalEvaluation(userId, entity);
        if (i > 0) {
            return Message.success("提交信息成功！！");
        }
        return Message.error("提交信息失败！！" + authorization);
    }

    @RequestMapping(value = "getSubmitStatus", method = RequestMethod.POST)
    @ApiOperation(value = "获取提交状态")
    public Message getSubmitStatus(@RequestHeader("Authorization") String authorization) {
        //获取用户id
        String userId = adminService.findUserIdByToken(authorization);
        if (StringUtils.isEmpty(userId)) {
            return Message.error("获取用户id为空：{}" + authorization);
        }
        QaReviewProfessionalEvaluationEntity byUserId = qaReviewProfessionalEvaluationService.findByUserId(userId);
        if (byUserId == null)
            return Message.success(0);
        return Message.success(byUserId.getSubmitStatus());
    }


    @RequestMapping(value = "getPersonStatus", method = RequestMethod.POST)
    @ApiOperation(value = "获取人员是否提交基本信息")
    public Message getPersonStatus(@RequestHeader("Authorization") String authorization) {
        String userId = adminService.findUserIdByToken(authorization);
        Integer result = qaReviewProfessionalEvaluationService.getPersonStatus(userId);
        if (result == 0)
            return Message.error("人员基本信息未提交，请提交后再操作~~");
        return Message.success();
    }


    /**
     * 首次参评人员管理
     *
     * @param anotherName
     * @param category
     * @return
     */
    @RequestMapping(value = "firstParticipateManagement", method = RequestMethod.POST)
    @ApiOperation(value = "首次参评人员管理")
    public Message firstParticipateManagement(@RequestHeader("Authorization") String authorization, String anotherName, String category) {
        List<Map<String, Object>> result = qaReviewProfessionalEvaluationService.firstParticipateManagement(anotherName, category);
        return Message.success(result);
    }

}
