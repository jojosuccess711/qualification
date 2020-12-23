/**
 * @filename:QaRelationClauseGroup 2020/02/20
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
 * @Description:  条件分组与条件项关系表
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_relation_clause_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaRelationClauseGroup implements Serializable {

	private static final long serialVersionUID = 1582696505702L;
	
    @Column(name = "group_id")
	@ApiModelProperty(name = "groupId", value = "分组id")
	private String groupId;

    @Column(name = "clause_id")
	@ApiModelProperty(name = "clauseId", value = "条件项id")
	private String clauseId;

}
