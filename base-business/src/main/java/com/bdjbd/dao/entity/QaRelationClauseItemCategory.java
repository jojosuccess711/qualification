/**
 * @filename:QaRelationClauseItemCategory 2020/02/20
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
 * @Description:  条件子项与分类关系表
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_relation_clause_item_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaRelationClauseItemCategory implements Serializable {

	private static final long serialVersionUID = 1582696505702L;
	
    @Column(name = "item_id")
	@ApiModelProperty(name = "itemId", value = "条件子项id")
	private String itemId;

    @Column(name = "category_id")
	@ApiModelProperty(name = "categoryId", value = "分类id")
	private String categoryId;

    @Column(name = "relation_id")
	@ApiModelProperty(name = "relationId", value = "关联id")
	private String relationId;

    @Column(name = "relation_value")
	@ApiModelProperty(name = "relationValue", value = "关联值")
	private String relationValue;

	@ApiModelProperty(name = "relation", value = "关联选项(select) id")
	private String relation;

	@ApiModelProperty(name = "express", value = "表达式")
	private String express;

    @Column(name = "clause_id")
	@ApiModelProperty(name = "clauseId", value = "qa_base_clause表主键")
	private String clauseId;

	@ApiModelProperty(name = "code", value = "编号")
	private String code;

}
