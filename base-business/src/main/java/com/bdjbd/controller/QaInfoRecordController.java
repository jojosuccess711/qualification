/**
 * @filename:QaInfoRecordController 2020/02/20
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
import com.bdjbd.dao.entity.QaInfoRecord;
import com.bdjbd.service.QaInfoRecordService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

/**   
 * 
 * @Description:  录入信息记录表接口层
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Api(value="录入信息记录表 - Controller", description = "录入信息记录表", tags = {"录入信息记录表"})
@RestController
@RequestMapping("/admin//qaInfoRecord")
public class QaInfoRecordController {

	@Autowired
	public QaInfoRecordService qaInfoRecordService;
	
	/**
	 * @explain 查询录入信息记录表对象  <swagger GET请求>
	 * @param   id
	 * @return  Message<QaInfoRecord>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "获取录入信息记录表信息", notes = "获取录入信息记录表信息[qaInfoRecord]，作者：DBC")
	@ApiImplicitParam(name = "id", value = "录入信息记录表id", paramType = "path", dataType = "String")
	public Message<QaInfoRecord> find(@PathVariable("id") String id){
		try {
			QaInfoRecord qaInfoRecord = qaInfoRecordService.find(id);
			if (qaInfoRecord != null) {
				return Message.success(qaInfoRecord);
			} else {
				log.error("获取录入信息记录表失败ID：" + id);
			}
		} catch (Exception e) {
			log.error("获取录入信息记录表执行异常：", e);
		}
    	return Message.error("信息不存在");
	}
	
	/**
	 * @explain 添加录入信息记录表对象
	 * @param   qaInfoRecord
	 * @return  Message<QaInfoRecord>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加录入信息记录表", notes = "添加录入信息记录表[qaInfoRecord], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "主键", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "userId", value = "用户id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "userName", value = "用户姓名", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "finalCategoryChildId", value = "最终审评职称id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "finalCategoryChildName", value = "最终审评职称名称(全称 格式为：领域--岗位--职称)", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "finalCategoryTreePath", value = "职称treepath路径", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstApproveStatus", value = "初次审核状态 0：未通过 1：通过 ", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstApproveTime", value = "初次审核时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstApproveUser", value = "初次审核人", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstApproveUserId", value = "初次审核人id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstApproveOpinion", value = "初次审核意见", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstCategoryChildId", value = "初审符合岗位（qa_category表主键）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstCategoryTreePath", value = "初审符合岗位树路径", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondApproveStatus", value = "复审审核状态 0：未通过 1：通过  2：审批中", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondApproveTime", value = "复审时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondApproveUser", value = "复审人", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondApproveUserId", value = "复审人id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondApproveOpinion", value = "复审意见", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondCategoryChildId", value = "复审给定岗位id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondCategoryTreePath", value = "复审给定岗位路径", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "状态 0： 默认保存 10：待电脑审核 11：电脑审核未通过 12：电脑审核通过 20：待人工审核 21：人工审核未通过 22：人工审核通过", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr0", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr1", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr2", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr3", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr4", value = "", paramType="query", dataType = "String"),
    })
	public Message<QaInfoRecord> add(QaInfoRecord qaInfoRecord){
		try {
			int rg = qaInfoRecordService.save(qaInfoRecord);
			if (rg > 0) {
				return Message.success(qaInfoRecord);
			} else {
				log.error("添加录入信息记录表执行失败：" + qaInfoRecord.toString());
				return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("添加录入信息记录表执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 删除录入信息记录表对象
	 * @param   id
	 * @return  Message<Object>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除录入信息记录表", notes = "删除录入信息记录表, 作者：DBC")
	@ApiImplicitParam(name = "id", value = "录入信息记录表id", paramType="query", required = true, dataType = "String")
	public Message<Object> delete(String id){
		try {
			int reg = qaInfoRecordService.delete(id);
			if (reg > 0) {
    			return Message.success(id);
			} else {
				log.error("删除录入信息记录表失败ID：" + id);
    			return Message.error("操作失败，请稍后刷新重试");
			}
		} catch (Exception e) {
			log.error("删除录入信息记录表执行异常：", e);
		}
    	return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 修改录入信息记录表对象
	 * @param   qaInfoRecord
	 * @return  Message<QaInfoRecord>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "修改录入信息记录表", notes = "修改录入信息记录表[qaInfoRecord], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "主键", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "userId", value = "用户id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "userName", value = "用户姓名", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "finalCategoryChildId", value = "最终审评职称id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "finalCategoryChildName", value = "最终审评职称名称(全称 格式为：领域--岗位--职称)", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "finalCategoryTreePath", value = "职称treepath路径", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstApproveStatus", value = "初次审核状态 0：未通过 1：通过 ", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstApproveTime", value = "初次审核时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstApproveUser", value = "初次审核人", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstApproveUserId", value = "初次审核人id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstApproveOpinion", value = "初次审核意见", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstCategoryChildId", value = "初审符合岗位（qa_category表主键）", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "firstCategoryTreePath", value = "初审符合岗位树路径", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondApproveStatus", value = "复审审核状态 0：未通过 1：通过  2：审批中", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondApproveTime", value = "复审时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondApproveUser", value = "复审人", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondApproveUserId", value = "复审人id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondApproveOpinion", value = "复审意见", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondCategoryChildId", value = "复审给定岗位id", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "secondCategoryTreePath", value = "复审给定岗位路径", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "状态 0： 默认保存 10：待电脑审核 11：电脑审核未通过 12：电脑审核通过 20：待人工审核 21：人工审核未通过 22：人工审核通过", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr0", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr1", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr2", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr3", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr4", value = "", paramType="query", dataType = "String"),
    })
	@PostMapping("/update")
	public Message<QaInfoRecord> update(QaInfoRecord qaInfoRecord){
		try {
			int reg = qaInfoRecordService.update(qaInfoRecord);
			if (reg > 0) {
				return Message.success(qaInfoRecord);
			} else {
				log.error("修改录入信息记录表失败ID：" + qaInfoRecord.toString());
    			return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("修改录入信息记录表执行异常："+e.getMessage());
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 获取匹配录入信息记录表
	 * @return  Page<QaInfoRecord>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "分页条件查询录入信息记录表", notes = "条件查询[qaInfoRecord], 作者：DBC")
    @ApiImplicitParams({
     	@ApiImplicitParam(name = "pageNum", value = "当前页", paramType="query", required = true, dataType = "Int", example="1"),
     	@ApiImplicitParam(name = "pageSize", value = "页行数", paramType="query", required = true, dataType = "Int", example="5")
    })
	@PostMapping("/page")
	public Message<Page<QaInfoRecord>> findPage(Pageable pageable){
		try {
    		Page<QaInfoRecord> page = qaInfoRecordService.findPage(pageable);
			return Message.success(page);
		} catch (Exception e) {
			log.error("获取录入信息记录表执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
}