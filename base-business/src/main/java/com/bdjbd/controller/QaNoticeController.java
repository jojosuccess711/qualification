/**
 * @filename:QaNoticeController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.*;
import org.junit.runner.FilterFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bdjbd.dao.entity.QaNotice;
import com.bdjbd.service.QaNoticeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.List;

/**   
 * 
 * @Description:  系统公告接口层
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Api(value="系统公告 - Controller", description = "系统公告", tags = {"系统公告"})
@RestController
@RequestMapping("/admin/qaNotice")
public class QaNoticeController {

	@Autowired
	public QaNoticeService qaNoticeService;
	
	/**
	 * @explain 查询系统公告对象  <swagger GET请求>
	 * @param   id
	 * @return  Message<QaNotice>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "获取系统公告信息", notes = "获取系统公告信息[qaNotice]，作者：DBC")
	@ApiImplicitParam(name = "id", value = "系统公告id", paramType = "path", dataType = "String")
	public Message<QaNotice> find(@PathVariable("id") String id){
		try {
			QaNotice qaNotice = qaNoticeService.find(id);
			if (qaNotice != null) {
				return Message.success(qaNotice);
			} else {
				log.error("获取系统公告失败ID：" + id);
			}
		} catch (Exception e) {
			log.error("获取系统公告执行异常：", e);
		}
    	return Message.error("信息不存在");
	}
	
	/**
	 * @explain 添加系统公告对象
	 * @param   qaNotice
	 * @return  Message<QaNotice>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加系统公告", notes = "添加系统公告[qaNotice], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "beginDate", value = "开始时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "公告内容", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "title", value = "标题", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "hasList", value = "是否列出 0：否  1：是", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "orders", value = "排序", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "hasTop", value = "是否置顶 0 默认 1 置顶", paramType="query", dataType = "String"),
    })
	public Message<QaNotice> add(QaNotice qaNotice){
		try {
			int rg = qaNoticeService.save(qaNotice);
			if (rg > 0) {
				return Message.success(qaNotice);
			} else {
				log.error("添加系统公告执行失败：" + qaNotice.toString());
				return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("添加系统公告执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 删除系统公告对象
	 * @param   id
	 * @return  Message<Object>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除系统公告", notes = "删除系统公告, 作者：DBC")
	@ApiImplicitParam(name = "id", value = "系统公告id", paramType="query", required = true, dataType = "String")
	public Message<Object> delete(String id){
		try {
			int reg = qaNoticeService.delete(id);
			if (reg > 0) {
    			return Message.success(id);
			} else {
				log.error("删除系统公告失败ID：" + id);
    			return Message.error("操作失败，请稍后刷新重试");
			}
		} catch (Exception e) {
			log.error("删除系统公告执行异常：", e);
		}
    	return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 修改系统公告对象
	 * @param   qaNotice
	 * @return  Message<QaNotice>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "修改系统公告", notes = "修改系统公告[qaNotice], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "beginDate", value = "开始时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "endDate", value = "结束时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "公告内容", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "title", value = "标题", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "hasList", value = "是否列出 0：否  1：是", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "orders", value = "排序", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "hasTop", value = "是否置顶 0 默认 1 置顶", paramType="query", dataType = "String"),
    })
	@PostMapping("/update")
	public Message<QaNotice> update(QaNotice qaNotice){
		try {
			int reg = qaNoticeService.update(qaNotice);
			if (reg > 0) {
				return Message.success(qaNotice);
			} else {
				log.error("修改系统公告失败ID：" + qaNotice.toString());
    			return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("修改系统公告执行异常："+e.getMessage());
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 获取匹配系统公告
	 * @return  Page<QaNotice>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "分页条件查询系统公告", notes = "条件查询[qaNotice], 作者：DBC")
    @ApiImplicitParams({
     	@ApiImplicitParam(name = "pageNum", value = "当前页", paramType="query", required = true, dataType = "Int", example="1"),
     	@ApiImplicitParam(name = "pageSize", value = "页行数", paramType="query", required = true, dataType = "Int", example="5")
    })
	@PostMapping("/page")
	public Message<Page<QaNotice>> findPage(Pageable pageable){
		try {
			List<Filter> filterList = pageable.getFilterList();
			filterList.add(Filter.eq("hasList", 1));
			filterList.add(Filter.le("beginDate", new Date()));
			filterList.add(Filter.gt("endDate", new Date()));
			pageable.setFilterList(filterList);
			List<Order> orders = pageable.getOrders();
			orders.add(Order.desc("orders"));
			Page<QaNotice> page = qaNoticeService.findPage(pageable);
			return Message.success(page);
		} catch (Exception e) {
			log.error("获取系统公告执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
}