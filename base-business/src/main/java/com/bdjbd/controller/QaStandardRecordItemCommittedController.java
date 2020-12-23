/**
 * @filename:QaStandardRecordItemCommittedController 2020年4月9日
 * @project 职称评审系统  V1.0
 * Copyright(c) 2018 songzekun Co. Ltd. 
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
import com.bdjbd.dao.entity.QaStandardRecordItemCommitted;
import com.bdjbd.service.QaStandardRecordItemCommittedService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

/**   
 * 
 * @Description:  用户已提交参数选项数据接口层
 * @Author:       songzekun   
 * @CreateDate:   2020年4月9日
 * @Version:      V1.0
 *    
 */
@Slf4j
@Api(value="用户已提交参数选项数据 - Controller", description = "用户已提交参数选项数据", tags = {"用户已提交参数选项数据"})
@RestController
@RequestMapping("/admin/committedRecordItem/qaStandardRecordItemCommitted")
public class QaStandardRecordItemCommittedController {

	@Autowired
	public QaStandardRecordItemCommittedService qaStandardRecordItemCommittedService;
	
	/**
	 * @explain 查询用户已提交参数选项数据对象  <swagger GET请求>
	 * @param   id
	 * @return  Message<QaStandardRecordItemCommitted>
	 * @author  songzekun
	 * @time    2020年4月9日
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "获取用户已提交参数选项数据信息", notes = "获取用户已提交参数选项数据信息[qaStandardRecordItemCommitted]，作者：songzekun")
	@ApiImplicitParam(name = "id", value = "用户已提交参数选项数据id", paramType = "path", dataType = "String")
	public Message find(@PathVariable("id") String id){
		try {
			QaStandardRecordItemCommitted qaStandardRecordItemCommitted = qaStandardRecordItemCommittedService.find(id);
			if (qaStandardRecordItemCommitted != null) {
				return Message.success(qaStandardRecordItemCommitted);
			} else {
				log.error("获取用户已提交参数选项数据失败ID：" + id);
			}
		} catch (Exception e) {
			log.error("获取用户已提交参数选项数据执行异常：", e);
		}
    	return Message.error("信息不存在");
	}
	
	/**
	 * @explain 添加用户已提交参数选项数据对象
	 * @param   qaStandardRecordItemCommitted
	 * @return  Message<QaStandardRecordItemCommitted>
	 * @author  songzekun
	 * @time    2020年4月9日
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加用户已提交参数选项数据", notes = "添加用户已提交参数选项数据[qaStandardRecordItemCommitted], 作者：songzekun")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "主键", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "groupCategory", value = "数据组", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "orders", value = "排序", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "recordId", value = "采集标准申请记录id （qa_standard_record表id）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "parameterGroupId", value = "base_parameter_group表id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "parameterId", value = "base_parameter表id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "parameterValue", value = "参数值", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "parameterAnnexUrl", value = "附件地址", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr0", value = "数据验证", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr1", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr2", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr3", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr4", value = "", paramType="query", dataType = "String"),
    })
	public Message add(QaStandardRecordItemCommitted qaStandardRecordItemCommitted){
		try {
			int rg = qaStandardRecordItemCommittedService.save(qaStandardRecordItemCommitted);
			if (rg > 0) {
				return Message.success(qaStandardRecordItemCommitted);
			} else {
				log.error("添加用户已提交参数选项数据执行失败：" + qaStandardRecordItemCommitted.toString());
				return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("添加用户已提交参数选项数据执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 删除用户已提交参数选项数据对象
	 * @param   id
	 * @return  Message<Object>
	 * @author  songzekun
	 * @time    2020年4月9日
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除用户已提交参数选项数据", notes = "删除用户已提交参数选项数据, 作者：songzekun")
	@ApiImplicitParam(name = "id", value = "用户已提交参数选项数据id", paramType="query", required = true, dataType = "String")
	public Message delete(String id){
		try {
			int reg = qaStandardRecordItemCommittedService.delete(id);
			if (reg > 0) {
    			return Message.success(id);
			} else {
				log.error("删除用户已提交参数选项数据失败ID：" + id);
    			return Message.error("操作失败，请稍后刷新重试");
			}
		} catch (Exception e) {
			log.error("删除用户已提交参数选项数据执行异常：", e);
		}
    	return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 修改用户已提交参数选项数据对象
	 * @param   qaStandardRecordItemCommitted
	 * @return  Message<QaStandardRecordItemCommitted>
	 * @author  songzekun
	 * @time    2020年4月9日
	 */
	@ApiOperation(value = "修改用户已提交参数选项数据", notes = "修改用户已提交参数选项数据[qaStandardRecordItemCommitted], 作者：songzekun")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "主键", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "groupCategory", value = "数据组", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "orders", value = "排序", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "recordId", value = "采集标准申请记录id （qa_standard_record表id）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "parameterGroupId", value = "base_parameter_group表id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "parameterId", value = "base_parameter表id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "parameterValue", value = "参数值", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "parameterAnnexUrl", value = "附件地址", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr0", value = "数据验证", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr1", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr2", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr3", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr4", value = "", paramType="query", dataType = "String"),
    })
	@PostMapping("/update")
	public Message update(QaStandardRecordItemCommitted qaStandardRecordItemCommitted){
		try {
			int reg = qaStandardRecordItemCommittedService.update(qaStandardRecordItemCommitted);
			if (reg > 0) {
				return Message.success(qaStandardRecordItemCommitted);
			} else {
				log.error("修改用户已提交参数选项数据失败ID：" + qaStandardRecordItemCommitted.toString());
    			return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("修改用户已提交参数选项数据执行异常："+e.getMessage());
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 获取匹配用户已提交参数选项数据
	 * @return  Page<QaStandardRecordItemCommitted>
	 * @author  songzekun
	 * @time    2020年4月9日
	 */
	@ApiOperation(value = "分页条件查询用户已提交参数选项数据", notes = "条件查询[qaStandardRecordItemCommitted], 作者：songzekun")
    @ApiImplicitParams({
     	@ApiImplicitParam(name = "pageNum", value = "当前页", paramType="query", required = true, dataType = "Int", example="1"),
     	@ApiImplicitParam(name = "pageSize", value = "页行数", paramType="query", required = true, dataType = "Int", example="5")
    })
	@PostMapping("/page")
	public Message findPage(Pageable pageable){
		try {
    		Page<QaStandardRecordItemCommitted> page = qaStandardRecordItemCommittedService.findPage(pageable);
			return Message.success(page);
		} catch (Exception e) {
			log.error("获取用户已提交参数选项数据执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
}