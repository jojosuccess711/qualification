package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.dao.entity.QaReviewCfg;
import com.bdjbd.dao.entity.QaReviewExpertEntity;
import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationEntity;
import com.bdjbd.service.QaReviewProfessionalEvaluationService;
import com.bdjbd.service.ReviewCfgService;
import com.bdjbd.service.sys.QaReviewExpertService;
import com.bdjbd.service.sys.SysAdminService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * 专家管理
 *
 * @author 83746
 */
@RestController
@RequestMapping("/admin/expert")
@Api(value = "专家管理 - Controller", description = "专家管理", tags = {"专家管理"})
public class ReviewExpertController {

    @Autowired
    private QaReviewExpertService qaReviewExpertService;
    @Autowired
    private SysAdminService adminService;

    @Autowired
    private ReviewCfgService reviewCfgService;

    @RequestMapping(value = "findExpertList", method = RequestMethod.POST)
    @ApiOperation(value = "专家管理列表")
    public Message findExpertList(@RequestHeader("Authorization") String authorization, Integer pageNum, Integer pageSize, QaReviewExpertEntity entity) {
        PageInfo<QaReviewExpertEntity> result = qaReviewExpertService.findExpertList(pageNum, pageSize, entity);
        return Message.success(result);
    }

    @RequestMapping(value = "addExpert", method = RequestMethod.POST)
    @ApiOperation(value = "新增专家")
    public Message addExpert(@RequestHeader("Authorization") String authorization, QaReviewExpertEntity entity) {

        if (entity.getAccountNumber().startsWith("1001")) {
            return Message.error("专家账号必须以1001开头");
        }


        int i = qaReviewExpertService.addExpert(entity);
        if (i > 0) {
            return Message.success("保存信息成功！！");
        }
        return Message.error("保存信息失败！！");
    }

    @RequestMapping(value = "findById/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "查询专家")
    public Message findById(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        QaReviewExpertEntity entity = qaReviewExpertService.findById(id);
        return Message.success(entity);
    }

    @RequestMapping(value = "updateExpert", method = RequestMethod.POST)
    @ApiOperation(value = "修改专家")
    public Message updateExpert(@RequestHeader("Authorization") String authorization, QaReviewExpertEntity entity) {
        int i = qaReviewExpertService.updateExpert(entity);
        if (i > 0) {
            return Message.success("修改信息成功！！");
        }
        return Message.error("修改信息失败！！");
    }

    @RequestMapping(value = "deleteExpert/{id}", method = RequestMethod.GET)
    @ApiOperation(value = "删除专家")
    public Message deleteExpert(@RequestHeader("Authorization") String authorization, @PathVariable("id") Integer id) {
        int i = qaReviewExpertService.deleteExpert(id);
        if (i > 0) {
            return Message.success("删除信息成功！！");
        }
        return Message.error("删除信息失败！！");
    }

    @RequestMapping(value = "findExpertTask", method = RequestMethod.POST)
    @ApiOperation(value = "查看专家任务")
    public Message findExpertTask(@RequestHeader("Authorization") String authorization) {
        String userId = adminService.findUserIdByToken(authorization);
        List<QaReviewCfg> expertTask = reviewCfgService.findExpertTask(userId);
        return Message.success(expertTask);
    }
}
