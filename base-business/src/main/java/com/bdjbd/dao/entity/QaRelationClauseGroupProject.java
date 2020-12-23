/**
 * @filename:QaRelationClauseGroupProject 2020/02/20
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
 * @Description:  条件分组与项目关系表
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_relation_clause_group_project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaRelationClauseGroupProject implements Serializable {

	private static final long serialVersionUID = 1582696505702L;
	
    @Column(name = "project_id")
	@ApiModelProperty(name = "projectId", value = "项目id")
	private String projectId;

    @Column(name = "group_id")
	@ApiModelProperty(name = "groupId", value = "条件组id")
	private String groupId;

}
