/**
 * @filename:QaBaseProjectController 2020/02/20
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
import com.bdjbd.dao.entity.QaBaseProject;
import com.bdjbd.service.QaBaseProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

/**   
 * 
 * @Description:  项目项表接口层
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Api(value="项目项表 - Controller", description = "项目项表", tags = {"项目项表"})
@RestController
@RequestMapping("/admin//qaBaseProject")
public class QaBaseProjectController {

	@Autowired
	public QaBaseProjectService qaBaseProjectService;
	
	/**
	 * @explain 查询项目项表对象  <swagger GET请求>
	 * @param   id
	 * @return  Message<QaBaseProject>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "获取项目项表信息", notes = "获取项目项表信息[qaBaseProject]，作者：DBC")
	@ApiImplicitParam(name = "id", value = "项目项表id", paramType = "path", dataType = "String")
	public Message<QaBaseProject> find(@PathVariable("id") String id){
		try {
			QaBaseProject qaBaseProject = qaBaseProjectService.find(id);
			if (qaBaseProject != null) {
				return Message.success(qaBaseProject);
			} else {
				log.error("获取项目项表失败ID：" + id);
			}
		} catch (Exception e) {
			log.error("获取项目项表执行异常：", e);
		}
    	return Message.error("信息不存在");
	}
	
	/**
	 * @explain 添加项目项表对象
	 * @param   qaBaseProject
	 * @return  Message<QaBaseProject>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加项目项表", notes = "添加项目项表[qaBaseProject], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "name", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "orders", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "titile", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "hasList", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "category", value = "领域", paramType="query", dataType = "String"),
    })
	public Message<QaBaseProject> add(QaBaseProject qaBaseProject){
		try {
			int rg = qaBaseProjectService.save(qaBaseProject);
			if (rg > 0) {
				return Message.success(qaBaseProject);
			} else {
				log.error("添加项目项表执行失败：" + qaBaseProject.toString());
				return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("添加项目项表执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 删除项目项表对象
	 * @param   id
	 * @return  Message<Object>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除项目项表", notes = "删除项目项表, 作者：DBC")
	@ApiImplicitParam(name = "id", value = "项目项表id", paramType="query", required = true, dataType = "String")
	public Message<Object> delete(String id){
		try {
			int reg = qaBaseProjectService.delete(id);
			if (reg > 0) {
    			return Message.success(id);
			} else {
				log.error("删除项目项表失败ID：" + id);
    			return Message.error("操作失败，请稍后刷新重试");
			}
		} catch (Exception e) {
			log.error("删除项目项表执行异常：", e);
		}
    	return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 修改项目项表对象
	 * @param   qaBaseProject
	 * @return  Message<QaBaseProject>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "修改项目项表", notes = "修改项目项表[qaBaseProject], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "name", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "orders", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "titile", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "hasList", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "category", value = "领域", paramType="query", dataType = "String"),
    })
	@PostMapping("/update")
	public Message<QaBaseProject> update(QaBaseProject qaBaseProject){
		try {
			int reg = qaBaseProjectService.update(qaBaseProject);
			if (reg > 0) {
				return Message.success(qaBaseProject);
			} else {
				log.error("修改项目项表失败ID：" + qaBaseProject.toString());
    			return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("修改项目项表执行异常："+e.getMessage());
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 获取匹配项目项表
	 * @return  Page<QaBaseProject>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "分页条件查询项目项表", notes = "条件查询[qaBaseProject], 作者：DBC")
    @ApiImplicitParams({
     	@ApiImplicitParam(name = "pageNum", value = "当前页", paramType="query", required = true, dataType = "Int", example="1"),
     	@ApiImplicitParam(name = "pageSize", value = "页行数", paramType="query", required = true, dataType = "Int", example="5")
    })
	@PostMapping("/page")
	public Message<Page<QaBaseProject>> findPage(Pageable pageable){
		try {
    		Page<QaBaseProject> page = qaBaseProjectService.findPage(pageable);
			return Message.success(page);
		} catch (Exception e) {
			log.error("获取项目项表执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
}