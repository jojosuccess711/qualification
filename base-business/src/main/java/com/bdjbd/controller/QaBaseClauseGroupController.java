/**
 * @filename:QaBaseClauseGroupController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.dao.entity.QaBaseClauseGroup;
import com.bdjbd.service.QaBaseClauseGroupService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

/**   
 * 
 * @Description:  基本条件项组接口层
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Api(value="基本条件项组 - Controller", description = "基本条件项组", tags = {"基本条件项组"})
@RestController
@RequestMapping("/admin//qaBaseClauseGroup")
public class QaBaseClauseGroupController {

	@Autowired
	public QaBaseClauseGroupService qaBaseClauseGroupService;
	
	/**
	 * @explain 查询基本条件项组对象  <swagger GET请求>
	 * @param   id
	 * @return  Message<QaBaseClauseGroup>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "获取基本条件项组信息", notes = "获取基本条件项组信息[qaBaseClauseGroup]，作者：DBC")
	@ApiImplicitParam(name = "id", value = "基本条件项组id", paramType = "path", dataType = "String")
	public Message<QaBaseClauseGroup> find(@PathVariable("id") String id){
		try {
			QaBaseClauseGroup qaBaseClauseGroup = qaBaseClauseGroupService.find(id);
			if (qaBaseClauseGroup != null) {
				return Message.success(qaBaseClauseGroup);
			} else {
				log.error("获取基本条件项组失败ID：" + id);
			}
		} catch (Exception e) {
			log.error("获取基本条件项组执行异常：", e);
		}
    	return Message.error("信息不存在");
	}
	
	/**
	 * @explain 添加基本条件项组对象
	 * @param   qaBaseClauseGroup
	 * @return  Message<QaBaseClauseGroup>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加基本条件项组", notes = "添加基本条件项组[qaBaseClauseGroup], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "code", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "title", value = "标头", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "内容", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "memo", value = "备注", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "condition", value = "条件表达式", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "orders", value = "排序", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr0", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr1", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr2", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr3", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr4", value = "", paramType="query", dataType = "String"),
    })
	public Message<QaBaseClauseGroup> add(QaBaseClauseGroup qaBaseClauseGroup){
		try {
			int rg = qaBaseClauseGroupService.save(qaBaseClauseGroup);
			if (rg > 0) {
				return Message.success(qaBaseClauseGroup);
			} else {
				log.error("添加基本条件项组执行失败：" + qaBaseClauseGroup.toString());
				return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("添加基本条件项组执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 删除基本条件项组对象
	 * @param   id
	 * @return  Message<Object>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除基本条件项组", notes = "删除基本条件项组, 作者：DBC")
	@ApiImplicitParam(name = "id", value = "基本条件项组id", paramType="query", required = true, dataType = "String")
	public Message<Object> delete(String id){
		try {
			int reg = qaBaseClauseGroupService.delete(id);
			if (reg > 0) {
    			return Message.success(id);
			} else {
				log.error("删除基本条件项组失败ID：" + id);
    			return Message.error("操作失败，请稍后刷新重试");
			}
		} catch (Exception e) {
			log.error("删除基本条件项组执行异常：", e);
		}
    	return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 修改基本条件项组对象
	 * @param   qaBaseClauseGroup
	 * @return  Message<QaBaseClauseGroup>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "修改基本条件项组", notes = "修改基本条件项组[qaBaseClauseGroup], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "code", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "title", value = "标头", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "内容", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "memo", value = "备注", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "condition", value = "条件表达式", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "orders", value = "排序", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr0", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr1", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr2", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr3", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr4", value = "", paramType="query", dataType = "String"),
    })
	@PostMapping("/update")
	public Message<QaBaseClauseGroup> update(QaBaseClauseGroup qaBaseClauseGroup){
		try {
			int reg = qaBaseClauseGroupService.update(qaBaseClauseGroup);
			if (reg > 0) {
				return Message.success(qaBaseClauseGroup);
			} else {
				log.error("修改基本条件项组失败ID：" + qaBaseClauseGroup.toString());
    			return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("修改基本条件项组执行异常："+e.getMessage());
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 获取匹配基本条件项组
	 * @return  Page<QaBaseClauseGroup>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "分页条件查询基本条件项组", notes = "条件查询[qaBaseClauseGroup], 作者：DBC")
    @ApiImplicitParams({
     	@ApiImplicitParam(name = "pageNum", value = "当前页", paramType="query", required = true, dataType = "Int", example="1"),
     	@ApiImplicitParam(name = "pageSize", value = "页行数", paramType="query", required = true, dataType = "Int", example="5")
    })
	@PostMapping("/page")
	public Message<Page<QaBaseClauseGroup>> findPage(Pageable pageable){
		try {
    		Page<QaBaseClauseGroup> page = qaBaseClauseGroupService.findPage(pageable);
			return Message.success(page);
		} catch (Exception e) {
			log.error("获取基本条件项组执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
}