/**
 * @filename:QaStandardRecordCommittedController 2020年4月9日
 * @project 职称评审系统  V1.0
 * Copyright(c) 2018 songzekun Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.dao.entity.QaStandardRecordCommitted;
import com.bdjbd.service.QaStandardRecordCommittedService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @Description: 已提交的用户信息接口层
 * @Author: songzekun
 * @CreateDate: 2020年4月9日
 * @Version: V1.0
 *
 */
@Slf4j
@Api(value = "已提交的用户信息 - Controller", description = "已提交的用户信息", tags = {"已提交的用户信息"})
@RestController
@RequestMapping("/admin/committedRecord/qaStandardRecordCommitted")
public class QaStandardRecordCommittedController {
    @Autowired
    QaStandardRecordCommittedService qaStandardRecordCommittedService;

    @ApiOperation(value = "条件查询用户标准信息记录列表", notes = "条件查询用户标准信息记录列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "attr0", value = "实际工作单位", paramType = "query", example = ""),

            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", required = true, dataType = "Int", example = "5"),
            @ApiImplicitParam(name = "name", value = "用户姓名", paramType ="query", example = ""),
            @ApiImplicitParam(name = "category", value = "职称分类", paramType =
                    "query", example = ""),
            @ApiImplicitParam(name = "categoryType", value = "岗位", paramType =
                    "query", example = ""),
            @ApiImplicitParam(name = "technologyCategory", value = "职称",
                    paramType = "query", example = ""),
            @ApiImplicitParam(name = "attr0", value = "组织", paramType =
                    "query", example = "")
    })
    @PostMapping("/findList")
    public Message<?> findList(@RequestParam(required = false,defaultValue = "1") Integer pageNum,
            @RequestParam(required = false,defaultValue ="12") Integer pageSize,
            String name,String category,String categoryType,String
            technologyCategory,String attr0) {
        try {
            Page<QaStandardRecordCommitted> page =
                    qaStandardRecordCommittedService.findCommittedRecords(pageNum,
                            pageSize,  name,category, categoryType,
                    technologyCategory,attr0);
            return Message.success(page);
        } catch (Exception e) {
            log.error("条件查询用户标准信息记录列表异常：{}", e);
        }
        return Message.error("操作异常，请稍后重试");
    }
}