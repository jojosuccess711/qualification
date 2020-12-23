/**
 * @filename:SysAdminController 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.controller.test;

import com.bdjbd.Message;
import com.bdjbd.annotation.IgnoreAuthority;
import com.bdjbd.annotation.IgnoreUserToken;
import com.bdjbd.bo.*;
import com.bdjbd.dao.entity.BaseParameter;
import com.bdjbd.dao.entity.BaseParameterGroup;
import com.bdjbd.dao.entity.BaseSimpleDefinition;
import com.bdjbd.dao.entity.QaCategory;
import com.bdjbd.service.*;
import com.bdjbd.service.matches.DataCategoryHandlerService;
import com.bdjbd.service.matches.MatchClauseItemService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.bdjbd.common.CommonConstants.EX_AUTHORITY_CODE;
import static com.bdjbd.common.CommonConstants.EX_TOKEN_ERROR_CODE;

/**   
 * 
 * @Description:  通用接口层
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Slf4j
@RestController
@RequestMapping("/test")
@Api(value = "V1 测试接口 - Controller", description = "V1 测试接口", tags = {"V1 测试接口"})
public class TestController {

	@Autowired
	private AssessorsService assessorsService;

	@RequestMapping(value = "/assessors", method = {RequestMethod.GET, RequestMethod.POST})
	@IgnoreAuthority
	@IgnoreUserToken
	public Message<?> assessors(String userId){
		assessorsService.saveOrUpdateAcademicRecord(userId);
		return Message.error(EX_TOKEN_ERROR_CODE, "token错误");
	}

	@Autowired
	private DataCategoryHandlerService dataCategoryHandlerService;

	@Autowired
	private QaBaseClauseItemService qaBaseClauseItemService;

	@Autowired
	private MatchClauseItemService matchClauseItemService;

	@Autowired
	QaCategoryService qaCategoryService;

	@Autowired
	BaseParameterService parameterService;

	@Autowired
	BaseParameterGroupService parameterGroupService;

	@Autowired
	BaseSimpleDefinitionService baseSimpleDefinitionService;

	@GetMapping("/data")
	public Message<?> data() {
		try {
			QaCategory category = qaCategoryService.find("101001");
			List<MapperClause> mapperClauseList = qaBaseClauseItemService.findItemsByCategory(category.getId());
			Category analysis = dataCategoryHandlerService.analysis(category, mapperClauseList);


			List<ClauseCategory> clauseCategories = analysis.getClauseCategories();
			for (ClauseCategory clauseCategory : clauseCategories) {
				for (Clause clause : clauseCategory.getClauses()) {
					for (ClauseItem clauseItem : clause.getClauseItems()) {
						String itemType = clauseItem.getItemType();
						if(itemType.equals("text")){
							String parameterId = clauseItem.getItemAttr0();
							BaseParameter baseParameter = parameterService.find(parameterId);
							BaseParameterGroup baseParameterGroup = parameterGroupService.find(baseParameter.getGroupId());
							clauseItem.setItemAttr2(baseParameterGroup.getName());
							clauseItem.setItemAttr3(baseParameter.getName());
						}
						if(itemType.equals("select") || itemType.equals("select&text") || itemType.equals("radio")){
							String parameterId = clauseItem.getItemRelationId();
							BaseSimpleDefinition baseSimpleDefinition = baseSimpleDefinitionService.find(parameterId);
							if(baseSimpleDefinition != null){
								clauseItem.setItemAttr2(baseSimpleDefinition.getMemo());
								clauseItem.setItemAttr3(baseSimpleDefinition.getName());
							}
						}
					}
				}
			}

//			matchClauseItemService.academicIsMatch(analysis, record);

			return Message.success(clauseCategories);
		} catch (Exception e) {
			log.error("{}", e);
		}
		return Message.error("error");
	}

	public static void main(String[] args) {

		String s = "1-1-1";

		System.out.println(s.substring(0, 1));

	}
}