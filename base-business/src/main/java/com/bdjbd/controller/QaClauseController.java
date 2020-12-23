/**
 * @filename:QaClauseController 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller;

import com.bdjbd.service.sys.SysAdminService;
import com.bdjbd.service.common.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.bdjbd.Message;
import com.bdjbd.Page;
import com.bdjbd.Pageable;
import com.bdjbd.dao.entity.QaClause;
import com.bdjbd.service.QaClauseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

/**   
 * 
 * @Description:  评审条件接口层
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Api(value="评审条件 - Controller", description = "评审条件", tags = {"评审条件"})
@RestController
@RequestMapping("/admin/qaClause")
public class QaClauseController {

	@Autowired
	public QaClauseService qaClauseService;
	@Autowired
	UploadService uploadService;
	@Autowired
	SysAdminService sysAdminService;
	/**
	 * @explain 查询评审条件对象  <swagger GET请求>
	 * @param   id
	 * @return  Message<QaClause>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "获取评审条件信息", notes = "获取评审条件信息[qaClause]，作者：DBC")
	@ApiImplicitParam(name = "id", value = "评审条件id", paramType = "path", dataType = "String")
	public Message<QaClause> find(@PathVariable("id") String id){
		try {
			QaClause qaClause = qaClauseService.find(id);
			if (qaClause != null) {
				return Message.success(qaClause);
			} else {
				log.error("获取评审条件失败ID：" + id);
			}
		} catch (Exception e) {
			log.error("获取评审条件执行异常：", e);
		}
    	return Message.error("信息不存在");
	}
	
	/**
	 * @explain 添加评审条件对象
	 * @param   qaClause
	 * @return  Message<QaClause>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加评审条件", notes = "添加评审条件[qaClause], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "categoryId", value = "参评职位id（qa_category表主键）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "clauseNum", value = "评审条件序号", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "评审条件内容", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "type", value = "条件要求： 0：不满足（否）  1：满足（是） 2：具备其一（是）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "annex", value = "是否需要上传附件 0：否 1：是", paramType="query", dataType = "String"),
    })
	public Message<QaClause> add(QaClause qaClause){
		try {
			int rg = qaClauseService.save(qaClause);
			if (rg > 0) {
				return Message.success(qaClause);
			} else {
				log.error("添加评审条件执行失败：" + qaClause.toString());
				return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("添加评审条件执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 删除评审条件对象
	 * @param   id
	 * @return  Message<Object>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除评审条件", notes = "删除评审条件, 作者：DBC")
	@ApiImplicitParam(name = "id", value = "评审条件id", paramType="query", required = true, dataType = "String")
	public Message<Object> delete(String id){
		try {
			int reg = qaClauseService.delete(id);
			if (reg > 0) {
    			return Message.success(id);
			} else {
				log.error("删除评审条件失败ID：" + id);
    			return Message.error("操作失败，请稍后刷新重试");
			}
		} catch (Exception e) {
			log.error("删除评审条件执行异常：", e);
		}
    	return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 修改评审条件对象
	 * @param   qaClause
	 * @return  Message<QaClause>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "修改评审条件", notes = "修改评审条件[qaClause], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "categoryId", value = "参评职位id（qa_category表主键）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "clauseNum", value = "评审条件序号", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "content", value = "评审条件内容", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "type", value = "条件要求： 0：不满足（否）  1：满足（是） 2：具备其一（是）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "annex", value = "是否需要上传附件 0：否 1：是", paramType="query", dataType = "String"),
    })
	@PostMapping("/update")
	public Message<QaClause> update(QaClause qaClause){
		try {
			int reg = qaClauseService.update(qaClause);
			if (reg > 0) {
				return Message.success(qaClause);
			} else {
				log.error("修改评审条件失败ID：" + qaClause.toString());
    			return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("修改评审条件执行异常："+e.getMessage());
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 获取匹配评审条件
	 * @return  Page<QaClause>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "分页条件查询评审条件", notes = "条件查询[qaClause], 作者：DBC")
    @ApiImplicitParams({
     	@ApiImplicitParam(name = "pageNum", value = "当前页", paramType="query", required = true, dataType = "Int", example="1"),
     	@ApiImplicitParam(name = "pageSize", value = "页行数", paramType="query", required = true, dataType = "Int", example="5")
    })
	@PostMapping("/page")
	public Message<Page<QaClause>> findPage(Pageable pageable){
		try {
    		Page<QaClause> page = qaClauseService.findPage(pageable);
			return Message.success(page);
		} catch (Exception e) {
			log.error("获取评审条件执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	@ApiOperation(value = "评审条件文件上传", notes = "评审条件文件上传")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "clauseId", value = "条件id", paramType="query", required = true, dataType = "String"),
			@ApiImplicitParam(name = "file", value = "文件base64编码", paramType="query", required = true, dataType = "String")
	})
	@PostMapping("/upload")
	public Message upload(@RequestHeader("Authorization") String authorization, @RequestParam("clauseId")String	clauseId,@RequestParam("file") String	file){
		try {
			String	userId	=	sysAdminService.findUserIdByToken(authorization);
			String objectKey ="qa/qaClauseFiles/"+"_"+userId+"_"+clauseId;
			return uploadService.uploadToObs("obs-2224",objectKey,file);
		} catch (Exception e) {
			log.error("获取评审条件执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	@ApiOperation(value = "通过领域id获取条件列表", notes = "通过领域id获取条件列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "categoryId", value = "领域id", paramType="query", required = true, dataType = "String")
	})
	@PostMapping("/clauses")
	public Message findClauses(@RequestParam("categoryId") String categoryId){
		try {
			return Message.success(qaClauseService.findClauseList(categoryId));
		} catch (Exception e) {
			log.error("获取评审条件执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
}