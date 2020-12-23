/**
 * @filename:QaRelationClauseGroupCategory 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Table;
import java.io.Serializable;

/**   
 *  
 * @Description:  条件分组与类别关系表
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_relation_clause_group_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaRelationClauseGroupCategory implements Serializable {

	private static final long serialVersionUID = 1582696505702L;
	
    @Column(name = "group_id")
	@ApiModelProperty(name = "groupId", value = "条件分组id")
	private String groupId;

    @Column(name = "category_id")
	@ApiModelProperty(name = "categoryId", value = "类别id")
	private String categoryId;

	@ApiModelProperty(name = "type", value = "类型 0 与关系 1 组内或关系")
	private Integer type;

    @Column(name = "inner_group")
	@ApiModelProperty(name = "innerGroup", value = "组内分组编号(用于组内或关系)")
	private Integer innerGroup;

    @Column(name = "clause_condition")
	@ApiModelProperty(name = "clauseCondition", value = "同一group下所有clause之间关系")
	private String clauseCondition;

}
