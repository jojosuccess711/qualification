package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.service.ReviewApplyParticipateService;
import com.bdjbd.service.sys.SysAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * 申请参评
 *
 * @author 83746
 */
@RestController
@RequestMapping("/admin/applyParticipate")
@Api(value = "申请参评 - Controller", description = "申请参评", tags = {"申请参评"})
public class ReviewApplyParticipateController {

    @Autowired
    private ReviewApplyParticipateService reviewApplyParticipateService;
    @Autowired
    private SysAdminService adminService;

    @RequestMapping(value = "/applygointo", method = RequestMethod.POST)
    @ApiOperation(value = "进入申请参评")
    public Message applyGoInto(@RequestHeader("Authorization") String authorization, String categoryNameType, String intfaceType) {
        //获取用户id
        String userId = adminService.findUserIdByToken(authorization);
        if (StringUtils.isEmpty(userId)) {
            return Message.error("获取用户id为空：{}" + authorization);
        }
        Map<String, Object> map = reviewApplyParticipateService.applyGoInto(userId, categoryNameType, intfaceType);
        return Message.success(map);
    }

    @RequestMapping(value = "/preview", method = RequestMethod.POST)
    @ApiOperation(value = "预览")
    public Message preview(@RequestHeader("Authorization") String authorization, String categoryNameType, String intfaceType) {
        //获取用户id
        String userId = adminService.findUserIdByToken(authorization);
        return Message.success(reviewApplyParticipateService.preview(userId, categoryNameType, intfaceType));
    }



    @RequestMapping(value = "/apply", method = RequestMethod.POST)
    @ApiOperation(value = "管理员获取参评申请参评")
    public Message apply(@RequestHeader("Authorization") String authorization, String categoryNameType, String intfaceType,String userId) {
        //获取用户id
//        String userId = adminService.findUserIdByToken(authorization);
        if (StringUtils.isEmpty(userId)) {
            return Message.error("获取用户id为空：{}" + authorization);
        }
        categoryNameType = reviewApplyParticipateService.get(userId);
        Map<String, Object> map = reviewApplyParticipateService.applyGoInto(userId, categoryNameType, intfaceType);
        return Message.success(map);
    }
}
