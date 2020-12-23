package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.dao.entity.QaParticipants;
import com.bdjbd.service.ParticipantsService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 4:40 PM 2020/8/26
 */
@RestController
@RequestMapping("/participants")
@Slf4j
@Api(value = "首次参评人员管理 - Controller", description = "首次参评人员管理接口", tags = {"首次参评人员管理接口"})
public class ParticipantsController {

    @Autowired
    private ParticipantsService participantsService;

    @RequestMapping(value = "/findList", method = RequestMethod.POST)
    @ApiOperation(value = "获取参评人员列表")
    public Message findList(@RequestHeader("Authorization") String authorization, String categoryName, String category, String type, Integer pageNum, Integer pageSize) {

        PageInfo<QaParticipants> list = participantsService.findList(categoryName, category, type, pageNum, pageSize);

        String time = participantsService.findTime(categoryName, category);
        Map<String, Object> map = new HashMap<>();
        map.put("page", list);
        map.put("startTime", time);
        return Message.success(map);
    }

    @RequestMapping(value = "/move", method = RequestMethod.POST)
    @ApiOperation(value = "移动")
    public Message move(@RequestHeader("Authorization") String authorization, String id) {
        int i = participantsService.move(id);
        return Message.success();
    }

    @RequestMapping(value = "/participation", method = RequestMethod.POST)
    @ApiOperation(value = "进入参评")
    public Message participation(@RequestHeader("Authorization") String authorization, String id, String status,String type) {
        int i = participantsService.participation(id, status,type);
        if (i > 0)
            return Message.success("进入参评成功");
        else
            return Message.error("进入参评失败");

    }

}
