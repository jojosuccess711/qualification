/**
 * @filename:QaApplyLogController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.Order;
import com.bdjbd.dao.entity.QaCategory;
import com.bdjbd.service.QaCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.dao.entity.QaApplyLog;
import com.bdjbd.service.QaApplyLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**   
 * 
 * @Description:  申请审核动态日志接口层
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Api(value="申请审核动态日志 - Controller", description = "申请审核动态日志", tags = {"申请审核动态日志"})
@RestController
@RequestMapping("/admin/qaApplyLog")
public class QaApplyLogController {

	@Autowired
	public QaApplyLogService qaApplyLogService;

	@Autowired
	private QaCategoryService qaCategoryService;
	
	/**
	 * @explain 查询申请审核动态日志对象  <swagger GET请求>
	 * @param   id
	 * @return  Message<QaApplyLog>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "获取申请审核动态日志信息", notes = "获取申请审核动态日志信息[qaApplyLog]，作者：DBC")
	@ApiImplicitParam(name = "id", value = "申请审核动态日志id", paramType = "path", dataType = "String")
	public Message<QaApplyLog> find(@PathVariable("id") String id){
		try {
			QaApplyLog qaApplyLog = qaApplyLogService.find(id);
			if (qaApplyLog != null) {
				return Message.success(qaApplyLog);
			} else {
				log.error("获取申请审核动态日志失败ID：" + id);
			}
		} catch (Exception e) {
			log.error("获取申请审核动态日志执行异常：", e);
		}
    	return Message.error("信息不存在");
	}
	
	/**
	 * @explain 添加申请审核动态日志对象
	 * @param   qaApplyLog
	 * @return  Message<QaApplyLog>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加申请审核动态日志", notes = "添加申请审核动态日志[qaApplyLog], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "applyId", value = "申请记录id（qa_apply_record表主键）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "userName", value = "申请用户姓名  冗余字段", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "userId", value = "用户id  冗余字段", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "categoryId", value = "申请领域（与qa_apply_record中该字段相同）冗余字段", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "type", value = "操作类型：1：评审通过    2：评审未通过", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "categoryChildId", value = "申请岗位（与qa_apply_record中该字段相同）冗余字段", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "academicTitle", value = "申请职称（与qa_apply_record中该字段相同）冗余字段", paramType="query", dataType = "String"),
    })
	public Message<QaApplyLog> add(QaApplyLog qaApplyLog){
		try {
			int rg = qaApplyLogService.save(qaApplyLog);
			if (rg > 0) {
				return Message.success(qaApplyLog);
			} else {
				log.error("添加申请审核动态日志执行失败：" + qaApplyLog.toString());
				return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("添加申请审核动态日志执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 删除申请审核动态日志对象
	 * @param   id
	 * @return  Message<Object>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除申请审核动态日志", notes = "删除申请审核动态日志, 作者：DBC")
	@ApiImplicitParam(name = "id", value = "申请审核动态日志id", paramType="query", required = true, dataType = "String")
	public Message<Object> delete(String id){
		try {
			int reg = qaApplyLogService.delete(id);
			if (reg > 0) {
    			return Message.success(id);
			} else {
				log.error("删除申请审核动态日志失败ID：" + id);
    			return Message.error("操作失败，请稍后刷新重试");
			}
		} catch (Exception e) {
			log.error("删除申请审核动态日志执行异常：", e);
		}
    	return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 修改申请审核动态日志对象
	 * @param   qaApplyLog
	 * @return  Message<QaApplyLog>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "修改申请审核动态日志", notes = "修改申请审核动态日志[qaApplyLog], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "applyId", value = "申请记录id（qa_apply_record表主键）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "userName", value = "申请用户姓名  冗余字段", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "userId", value = "用户id  冗余字段", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "categoryId", value = "申请领域（与qa_apply_record中该字段相同）冗余字段", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "type", value = "操作类型：1：评审通过    2：评审未通过", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "categoryChildId", value = "申请岗位（与qa_apply_record中该字段相同）冗余字段", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "academicTitle", value = "申请职称（与qa_apply_record中该字段相同）冗余字段", paramType="query", dataType = "String"),
    })
	@PostMapping("/update")
	public Message<QaApplyLog> update(QaApplyLog qaApplyLog){
		try {
			int reg = qaApplyLogService.update(qaApplyLog);
			if (reg > 0) {
				return Message.success(qaApplyLog);
			} else {
				log.error("修改申请审核动态日志失败ID：" + qaApplyLog.toString());
    			return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("修改申请审核动态日志执行异常："+e.getMessage());
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 获取匹配申请审核动态日志
	 * @return  Page<QaApplyLog>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "分页条件查询申请审核动态日志", notes = "条件查询[qaApplyLog], 作者：DBC")
    @ApiImplicitParams({
     	@ApiImplicitParam(name = "pageNum", value = "当前页", paramType="query", required = true, dataType = "Int", example="1"),
     	@ApiImplicitParam(name = "pageSize", value = "页行数", paramType="query", required = true, dataType = "Int", example="5")
    })
	@PostMapping("/page")
	public Message<Page<QaApplyLog>> findPage(Pageable pageable){
		try {

			List<Order> orders = pageable.getOrders();
			orders.add(Order.desc("createDate"));
			Page<QaApplyLog> page = qaApplyLogService.findPage(pageable);
			List<QaCategory> categories = qaCategoryService.findAll();
			for (QaApplyLog qaApplyLog : page.getContent()) {
				for (QaCategory category : categories) {
					if (qaApplyLog.getCategoryId() != null && qaApplyLog.getCategoryId().equals(category.getId())) {
						qaApplyLog.setCategoryName(category.getName());
						break;
					}
				}
			}

			return Message.success(page);
		} catch (Exception e) {
			log.error("获取申请审核动态日志执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
}