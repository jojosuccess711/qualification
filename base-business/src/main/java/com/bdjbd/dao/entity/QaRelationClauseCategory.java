/**
 * @filename:QaRelationClauseCategory 2020/02/20
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
 * @Description:  条件项与职称关系表
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_relation_clause_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaRelationClauseCategory implements Serializable {

	private static final long serialVersionUID = 1582696505702L;
	
    @Column(name = "clause_id")
	@ApiModelProperty(name = "clauseId", value = "qa_clause表主键")
	private String clauseId;

    @Column(name = "category_id")
	@ApiModelProperty(name = "categoryId", value = "qa_category表主键")
	private String categoryId;

    @Column(name = "clause_item_condition")
	@ApiModelProperty(name = "clauseItemCondition", value = "关系条件")
	private String clauseItemCondition;

}
