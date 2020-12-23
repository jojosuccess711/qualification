/**
 * @filename:QaAcademicRecordLogController 2020/02/20
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
import com.bdjbd.dao.entity.QaAcademicRecordLog;
import com.bdjbd.service.QaAcademicRecordLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

/**   
 * 
 * @Description:  职称评审记录日志接口层
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Api(value="V2职称评审记录日志 - Controller", description = "V2职称评审记录日志", tags = {"V2职称评审记录日志"})
@RestController
@RequestMapping("/admin//qaAcademicRecordLog")
public class QaAcademicRecordLogController {

	@Autowired
	public QaAcademicRecordLogService qaAcademicRecordLogService;
	
	/**
	 * @explain 查询职称评审记录日志对象  <swagger GET请求>
	 * @param   id
	 * @return  Message<QaAcademicRecordLog>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "获取职称评审记录日志信息", notes = "获取职称评审记录日志信息[qaAcademicRecordLog]，作者：DBC")
	@ApiImplicitParam(name = "id", value = "职称评审记录日志id", paramType = "path", dataType = "String")
	public Message<QaAcademicRecordLog> find(@PathVariable("id") String id){
		try {
			QaAcademicRecordLog qaAcademicRecordLog = qaAcademicRecordLogService.find(id);
			if (qaAcademicRecordLog != null) {
				return Message.success(qaAcademicRecordLog);
			} else {
				log.error("获取职称评审记录日志失败ID：" + id);
			}
		} catch (Exception e) {
			log.error("获取职称评审记录日志执行异常：", e);
		}
    	return Message.error("信息不存在");
	}
	
	/**
	 * @explain 添加职称评审记录日志对象
	 * @param   qaAcademicRecordLog
	 * @return  Message<QaAcademicRecordLog>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加职称评审记录日志", notes = "添加职称评审记录日志[qaAcademicRecordLog], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "主键", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "academicRecordId", value = "职称记录id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "operatorId", value = "操作人id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "operator", value = "操作人", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "memo", value = "备注", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "审批内容", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "beforeInfo", value = "之前信息", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "afterInfo", value = "之后信息", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "type", value = "操作类型 1. 电脑审批 2. 人工审批", paramType="query", dataType = "String"),
    })
	public Message<QaAcademicRecordLog> add(QaAcademicRecordLog qaAcademicRecordLog){
		try {
			int rg = qaAcademicRecordLogService.save(qaAcademicRecordLog);
			if (rg > 0) {
				return Message.success(qaAcademicRecordLog);
			} else {
				log.error("添加职称评审记录日志执行失败：" + qaAcademicRecordLog.toString());
				return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("添加职称评审记录日志执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 删除职称评审记录日志对象
	 * @param   id
	 * @return  Message<Object>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除职称评审记录日志", notes = "删除职称评审记录日志, 作者：DBC")
	@ApiImplicitParam(name = "id", value = "职称评审记录日志id", paramType="query", required = true, dataType = "String")
	public Message<Object> delete(String id){
		try {
			int reg = qaAcademicRecordLogService.delete(id);
			if (reg > 0) {
    			return Message.success(id);
			} else {
				log.error("删除职称评审记录日志失败ID：" + id);
    			return Message.error("操作失败，请稍后刷新重试");
			}
		} catch (Exception e) {
			log.error("删除职称评审记录日志执行异常：", e);
		}
    	return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 修改职称评审记录日志对象
	 * @param   qaAcademicRecordLog
	 * @return  Message<QaAcademicRecordLog>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "修改职称评审记录日志", notes = "修改职称评审记录日志[qaAcademicRecordLog], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "主键", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "academicRecordId", value = "职称记录id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "operatorId", value = "操作人id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "operator", value = "操作人", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "memo", value = "备注", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "审批内容", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "beforeInfo", value = "之前信息", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "afterInfo", value = "之后信息", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "type", value = "操作类型 1. 电脑审批 2. 人工审批", paramType="query", dataType = "String"),
    })
	@PostMapping("/update")
	public Message<QaAcademicRecordLog> update(QaAcademicRecordLog qaAcademicRecordLog){
		try {
			int reg = qaAcademicRecordLogService.update(qaAcademicRecordLog);
			if (reg > 0) {
				return Message.success(qaAcademicRecordLog);
			} else {
				log.error("修改职称评审记录日志失败ID：" + qaAcademicRecordLog.toString());
    			return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("修改职称评审记录日志执行异常："+e.getMessage());
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 获取匹配职称评审记录日志
	 * @return  Page<QaAcademicRecordLog>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "分页条件查询职称评审记录日志", notes = "条件查询[qaAcademicRecordLog], 作者：DBC")
    @ApiImplicitParams({
     	@ApiImplicitParam(name = "pageNum", value = "当前页", paramType="query", required = true, dataType = "Int", example="1"),
     	@ApiImplicitParam(name = "pageSize", value = "页行数", paramType="query", required = true, dataType = "Int", example="5")
    })
	@PostMapping("/page")
	public Message<Page<QaAcademicRecordLog>> findPage(Pageable pageable){
		try {
    		Page<QaAcademicRecordLog> page = qaAcademicRecordLogService.findPage(pageable);
			return Message.success(page);
		} catch (Exception e) {
			log.error("获取职称评审记录日志执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
}