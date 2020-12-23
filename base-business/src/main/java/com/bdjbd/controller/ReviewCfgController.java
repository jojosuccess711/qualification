package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.bo.TimeVO;
import com.bdjbd.dao.entity.QaReviewCfg;
import com.bdjbd.dao.entity.SysAdmin;
import com.bdjbd.service.ReviewCfgService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Table;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 4:36 PM 2020/8/27
 */
@RestController
@RequestMapping("/reviewCfg")
@Slf4j
@Api(value = "评审配置管理 - Controller", description = "评审配置管理接口", tags = {"评审配置管理接口"})
public class ReviewCfgController {

    @Autowired
    private ReviewCfgService reviewCfgService;

    @RequestMapping(value = "/addCfg", method = RequestMethod.POST)
    @ApiOperation(value = "添加评审配置")
    public Message addCfg(@RequestHeader("Authorization") String authorization, QaReviewCfg cfg) {
        int result = 0;
        if (StringUtils.isBlank(cfg.getId()))
            result = reviewCfgService.addCfg(cfg);
        else
            result = reviewCfgService.updateCfg(cfg);
        if (result > 0)
            return Message.success("配置成功");
        else
            return Message.error("配置失败");

    }

    @RequestMapping(value = "/findExperts", method = RequestMethod.POST)
    @ApiOperation(value = "获取专家评审")
    public Message findExperts(@RequestHeader("Authorization") String authorization) {

        List<SysAdmin> experts = reviewCfgService.findExperts();

        return Message.success(experts);
    }

    @RequestMapping(value = "/deleteCfg", method = RequestMethod.POST)
    @ApiOperation(value = "删除评审配置")
    public Message deleteCfg(@RequestHeader("Authorization") String authorization, QaReviewCfg cfg) {

        int result = reviewCfgService.deleteCfg(cfg);
        if (result > 0)
            return Message.success("删除配置成功");
        else
            return Message.error("删除配置失败");
    }

    @RequestMapping(value = "/findCfg", method = RequestMethod.POST)
    @ApiOperation(value = "获取评审配置")
    public Message findCfg(@RequestHeader("Authorization") String authorization, QaReviewCfg cfg) {
        TreeMap<Integer, Object> result = reviewCfgService.findCfg(cfg);
        return Message.success(result);
    }

    @RequestMapping(value = "/findCfgTime", method = RequestMethod.POST)
    @ApiOperation(value = "获取评审配置时间")
    public Message findCfgTime(@RequestHeader("Authorization") String authorization, QaReviewCfg cfg) {
        TimeVO result = reviewCfgService.findCfgTime(cfg);
        return Message.success(result);
    }

}
