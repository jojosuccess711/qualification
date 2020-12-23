/**
 * @filename:BaseRelationParameterDefinition 2020/02/20
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
 * @Description:  录入信息记录表
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "base_relation_parameter_definition")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseRelationParameterDefinition implements Serializable {

	private static final long serialVersionUID = 1582853040590L;
	
    @Column(name = "parameter_id")
	@ApiModelProperty(name = "parameterId", value = "参数表id (base_parameter)")
	private String parameterId;

    @Column(name = "relation_id")
	@ApiModelProperty(name = "relationId", value = "简单数据定义 关联idtype=0： base_simple_definition表typetype=1:   base_simple_definition表id")
	private String relationId;

    @Column(name = "parameter_group_id")
	@ApiModelProperty(name = "parameterGroupId", value = "参数组id (base_parameter_group)")
	private String parameterGroupId;

	@ApiModelProperty(name = "required", value = "是否必填")
	private Boolean required;

	@ApiModelProperty(name = "type", value = "类型0：base_simple_definition表type1：base_simple_definition表id")
	private Integer type;

}
