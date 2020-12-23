/**
 * @filename:QaBaseClauseItemController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import com.bdjbd.dao.entity.QaCategory;
import com.bdjbd.enums.CategoryTypeEnum;
import com.bdjbd.service.QaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.dao.entity.QaBaseClauseItem;
import com.bdjbd.service.QaBaseClauseItemService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 *
 * @Description: 条件子项接口层
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Slf4j
@Api(value = "条件子项 - Controller", description = "条件子项", tags = {"条件子项"})
@RestController
@RequestMapping("/admin//qaBaseClauseItem")
public class QaBaseClauseItemController {

    @Autowired
    public QaBaseClauseItemService qaBaseClauseItemService;

    @Autowired
    public QaCategoryService qaCategoryService;

    /**
     * @return Message<QaBaseClauseItem>
     */
    @GetMapping("/{id}")
    @ApiOperation(value = "获取条件子项信息", notes = "获取条件子项信息[qaBaseClauseItem]，作者：DBC")
    @ApiImplicitParam(name = "id", value = "条件子项id", paramType = "path", dataType = "String")
    public Message<QaBaseClauseItem> find(@PathVariable("id") String id) {
        try {
            QaBaseClauseItem qaBaseClauseItem = qaBaseClauseItemService.find(id);
            if (qaBaseClauseItem != null) {
                return Message.success(qaBaseClauseItem);
            }
            else {
                log.error("获取条件子项失败ID：" + id);
            }
        } catch (Exception e) {
            log.error("获取条件子项执行异常：", e);
        }
        return Message.error("信息不存在");
    }

    /**
     * @return Message<QaBaseClauseItem>
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加条件子项", notes = "添加条件子项[qaBaseClauseItem], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "clauseId", value = "条件项id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标头", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "memo", value = "备注", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类别  select 选择框  input 输入框", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "typeValidate", value = "类型校验  null 默认  select 下拉框(relation参数校验)  text 文本  number 数字", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "locationIndex", value = "位置下标", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "value", value = "值", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "relation", value = "关系引用表(select) id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr0", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr1", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr2", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr3", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr4", value = "", paramType = "query", dataType = "String"),
    })
    public Message<QaBaseClauseItem> add(QaBaseClauseItem qaBaseClauseItem) {
        try {
            int rg = qaBaseClauseItemService.save(qaBaseClauseItem);
            if (rg > 0) {
                return Message.success(qaBaseClauseItem);
            }
            else {
                log.error("添加条件子项执行失败：" + qaBaseClauseItem.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("添加条件子项执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<Object>
     */
    @PostMapping("/delete")
    @ApiOperation(value = "删除条件子项", notes = "删除条件子项, 作者：DBC")
    @ApiImplicitParam(name = "id", value = "条件子项id", paramType = "query", required = true, dataType = "String")
    public Message<Object> delete(String id) {
        try {
            int reg = qaBaseClauseItemService.delete(id);
            if (reg > 0) {
                return Message.success(id);
            }
            else {
                log.error("删除条件子项失败ID：" + id);
                return Message.error("操作失败，请稍后刷新重试");
            }
        } catch (Exception e) {
            log.error("删除条件子项执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Message<QaBaseClauseItem>
     */
    @ApiOperation(value = "修改条件子项", notes = "修改条件子项[qaBaseClauseItem], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "clauseId", value = "条件项id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "code", value = "编号", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "title", value = "标头", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "content", value = "内容", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "memo", value = "备注", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "type", value = "类别  select 选择框  input 输入框", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "typeValidate", value = "类型校验  null 默认  select 下拉框(relation参数校验)  text 文本  number 数字", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "locationIndex", value = "位置下标", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "value", value = "值", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "relation", value = "关系引用表(select) id", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr0", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr1", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr2", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr3", value = "", paramType = "query", dataType = "String"),
            @ApiImplicitParam(name = "attr4", value = "", paramType = "query", dataType = "String"),
    })
    @PostMapping("/update")
    public Message<QaBaseClauseItem> update(QaBaseClauseItem qaBaseClauseItem) {
        try {
            int reg = qaBaseClauseItemService.update(qaBaseClauseItem);
            if (reg > 0) {
                return Message.success(qaBaseClauseItem);
            }
            else {
                log.error("修改条件子项失败ID：" + qaBaseClauseItem.toString());
                return Message.error("操作失败，请稍后重试");
            }
        } catch (Exception e) {
            log.error("修改条件子项执行异常：" + e.getMessage());
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Page<QaBaseClauseItem>
     */
    @ApiOperation(value = "分页条件查询条件子项", notes = "条件查询[qaBaseClauseItem], 作者：DBC")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNum", value = "当前页", paramType = "query", required = true, dataType = "Int", example = "1"),
            @ApiImplicitParam(name = "pageSize", value = "页行数", paramType = "query", required = true, dataType = "Int", example = "5")
    })
    @PostMapping("/page")
    public Message<Page<QaBaseClauseItem>> findPage(Pageable pageable) {
        try {
            Page<QaBaseClauseItem> page = qaBaseClauseItemService.findPage(pageable);
            return Message.success(page);
        } catch (Exception e) {
            log.error("获取条件子项执行异常：", e);
        }
        return Message.error("操作异常，请稍后重试");
    }

    /**
     * @return Page<QaBaseClause>
     */
    @ApiOperation(value = "获取所有评审条件", notes = "获取所有评审条件[qaBaseClause], 作者：DBC")
    @IgnoreAuthority
    @IgnoreUserToken
    @ApiImplicitParams({
            @ApiImplicitParam(name = "categoryIdList", value = "领域ID列表", paramType = "query", dataType = "List<String>"),
            @ApiImplicitParam(name = "categoryChildIdList", value = "岗位Id列表", paramType = "query", dataType = "List<String>"),
            @ApiImplicitParam(name = "categoryAcademicIdList", value = "职称Id列表", paramType = "query", dataType = "List<String>"),
    })
    @PostMapping("/findAll")
    public Message<QaBaseClauseItem> findAll(@RequestParam(value = "categoryIdList") List<Long> categoryIdList, @RequestParam(value = "categoryChildIdList") List<Long> categoryChildIdList, @RequestParam(value = "categoryAcademicIdList") List<Long> categoryAcademicIdList) {
        //将领域Id列表和岗位Id列表转换为职称Id列表
        if (!categoryIdList.isEmpty()) {
            categoryAcademicIdList = qaCategoryService.findListByCategoryList(categoryIdList, CategoryTypeEnum.ACADEMIC.getKey());
        }
        else if (!categoryChildIdList.isEmpty()) {
            categoryAcademicIdList = qaCategoryService.findListByCategoryList(categoryChildIdList, CategoryTypeEnum.ACADEMIC.getKey());
        }
        return qaBaseClauseItemService.finAllList(categoryAcademicIdList);
    }
}