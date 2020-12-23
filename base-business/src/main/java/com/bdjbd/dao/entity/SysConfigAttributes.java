/**
 * @filename:SysConfigAttributes 2019/12/03
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
 * @Description:  系统配置项
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Table(name = "sys_config_attributes")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysConfigAttributes implements Serializable {

	private static final long serialVersionUID = 1575456345081L;
	
    @Column(name = "config_id")
	@ApiModelProperty(name = "configId", value = "配置id")
	private String configId;

	@ApiModelProperty(name = "name", value = "名称")
	private String name;

	@ApiModelProperty(name = "value", value = "值")
	private String value;

}
