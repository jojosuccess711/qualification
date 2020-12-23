/**
 * @filename:BaseSimpleDefinitionController 2020/02/20
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
import com.bdjbd.dao.entity.BaseSimpleDefinition;
import com.bdjbd.service.BaseSimpleDefinitionService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**   
 * 
 * @Description:  简单数据定义接口层
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Slf4j
@Api(value="简单数据定义 - Controller", description = "简单数据定义", tags = {"简单数据定义"})
@RestController
@RequestMapping("/admin//baseSimpleDefinition")
public class BaseSimpleDefinitionController {

	@Autowired
	public BaseSimpleDefinitionService baseSimpleDefinitionService;
	
	/**
	 * @explain 查询简单数据定义对象  <swagger GET请求>
	 * @param   id
	 * @return  Message<BaseSimpleDefinition>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@GetMapping("/{id}")
	@ApiOperation(value = "获取简单数据定义信息", notes = "获取简单数据定义信息[baseSimpleDefinition]，作者：DBC")
	@ApiImplicitParam(name = "id", value = "简单数据定义id", paramType = "path", dataType = "String")
	public Message<BaseSimpleDefinition> find(@PathVariable("id") String id){
		try {
			BaseSimpleDefinition baseSimpleDefinition = baseSimpleDefinitionService.find(id);
			if (baseSimpleDefinition != null) {
				return Message.success(baseSimpleDefinition);
			} else {
				log.error("获取简单数据定义失败ID：" + id);
			}
		} catch (Exception e) {
			log.error("获取简单数据定义执行异常：", e);
		}
    	return Message.error("信息不存在");
	}
	
	/**
	 * @explain 添加简单数据定义对象
	 * @param   baseSimpleDefinition
	 * @return  Message<BaseSimpleDefinition>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/add")
	@ApiOperation(value = "添加简单数据定义", notes = "添加简单数据定义[baseSimpleDefinition], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "主键", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "orders", value = "排序", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "状态 0禁用 1启用", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "categoryId", value = "领域ID qa_category表主键", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "name", value = "名称", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "memo", value = "备注", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "type", value = "类型（根据实际场景定义）1001 渠道表; 1002 站点区域; 1003 设备使用平台; 1004 road_type;  1005 停车卡类型", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr0", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr1", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr2", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr3", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr4", value = "", paramType="query", dataType = "String"),
    })
	public Message<BaseSimpleDefinition> add(BaseSimpleDefinition baseSimpleDefinition){
		try {
			int rg = baseSimpleDefinitionService.save(baseSimpleDefinition);
			if (rg > 0) {
				return Message.success(baseSimpleDefinition);
			} else {
				log.error("添加简单数据定义执行失败：" + baseSimpleDefinition.toString());
				return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("添加简单数据定义执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 删除简单数据定义对象
	 * @param   id
	 * @return  Message<Object>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@PostMapping("/delete")
	@ApiOperation(value = "删除简单数据定义", notes = "删除简单数据定义, 作者：DBC")
	@ApiImplicitParam(name = "id", value = "简单数据定义id", paramType="query", required = true, dataType = "String")
	public Message<Object> delete(String id){
		try {
			int reg = baseSimpleDefinitionService.delete(id);
			if (reg > 0) {
    			return Message.success(id);
			} else {
				log.error("删除简单数据定义失败ID：" + id);
    			return Message.error("操作失败，请稍后刷新重试");
			}
		} catch (Exception e) {
			log.error("删除简单数据定义执行异常：", e);
		}
    	return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 修改简单数据定义对象
	 * @param   baseSimpleDefinition
	 * @return  Message<BaseSimpleDefinition>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "修改简单数据定义", notes = "修改简单数据定义[baseSimpleDefinition], 作者：DBC")
    @ApiImplicitParams({
			@ApiImplicitParam(name = "id", value = "主键", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "createDate", value = "创建时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "modifyDate", value = "修改时间", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "orders", value = "排序", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "status", value = "状态 0禁用 1启用", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "categoryId", value = "领域ID qa_category表主键", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "name", value = "名称", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "memo", value = "备注", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "type", value = "类型（根据实际场景定义）1001 渠道表; 1002 站点区域; 1003 设备使用平台; 1004 road_type;  1005 停车卡类型", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr0", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr1", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr2", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr3", value = "", paramType="query", dataType = "String"),
			@ApiImplicitParam(name = "attr4", value = "", paramType="query", dataType = "String"),
    })
	@PostMapping("/update")
	public Message<BaseSimpleDefinition> update(BaseSimpleDefinition baseSimpleDefinition){
		try {
			int reg = baseSimpleDefinitionService.update(baseSimpleDefinition);
			if (reg > 0) {
				return Message.success(baseSimpleDefinition);
			} else {
				log.error("修改简单数据定义失败ID：" + baseSimpleDefinition.toString());
    			return Message.error("操作失败，请稍后重试");
			}
		} catch (Exception e) {
			log.error("修改简单数据定义执行异常："+e.getMessage());
		}
		return Message.error("操作异常，请稍后重试");
	}
	
	/**
	 * @explain 获取匹配简单数据定义
	 * @return  Page<BaseSimpleDefinition>
	 * @author  DBC
	 * @time    2020/02/20
	 */
	@ApiOperation(value = "分页条件查询简单数据定义", notes = "条件查询[baseSimpleDefinition], 作者：DBC")
    @ApiImplicitParams({
     	@ApiImplicitParam(name = "pageNum", value = "当前页", paramType="query", required = true, dataType = "Int", example="1"),
     	@ApiImplicitParam(name = "pageSize", value = "页行数", paramType="query", required = true, dataType = "Int", example="5")
    })
	@PostMapping("/page")
	public Message<Page<BaseSimpleDefinition>> findPage(Pageable pageable){
		try {
    		Page<BaseSimpleDefinition> page = baseSimpleDefinitionService.findPage(pageable);
			return Message.success(page);
		} catch (Exception e) {
			log.error("获取简单数据定义执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
	@ApiOperation(value = "通过type获取下拉选项数据", notes = "通过type获取下拉选项数据")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "基本信息类型	", paramType="query", required = true, dataType = "String", example="201002"),
	})
	@PostMapping("/selectOptions")
	public Message findSelectOptions(String		type){
		try {
			return baseSimpleDefinitionService.findSelectOptionsByType(type);
		} catch (Exception e) {
			log.error("获取简单数据定义执行异常：", e);
		}
		return Message.error("操作异常，请稍后重试");
	}

	@ApiOperation(value = "查询列表", notes = "查询列表")
	@ApiImplicitParams({
			@ApiImplicitParam(name = "type", value = "类型：1010002901"),
	})
	@GetMapping("/findListByType")
	public Message<?> findListByType(String type) {
		try {
			List<BaseSimpleDefinition> list = baseSimpleDefinitionService.findList(type);
			return Message.success(list);
		} catch (Exception e) {
			log.error("查询失败：{}", e);
		}
		return Message.error("操作异常，请稍后重试");
	}
}