/**
 * @filename:SysConfig 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

/**   
 *  
 * @Description:  系统配置
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Table(name = "sys_config")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysConfig implements Serializable {

	private static final long serialVersionUID = 1575456345081L;
	
	@ApiModelProperty(name = "des", value = "描述")
	private String des;

    @Id
	@ApiModelProperty(name = "id", value = "主键")
	private String id;

	@ApiModelProperty(name = "name", value = "名称")
	private String name;

	@ApiModelProperty(name = "status", value = "状态0 禁用1 启用")
	private Boolean status;

	@ApiModelProperty(name = "value", value = "值")
	private String value;

	@Transient
	private List<SysConfigAttributes> attributes;

	@Transient
	public String getAttributeValue(String name){
		if(StringUtils.isBlank(name))
			return null;
		for(Iterator<SysConfigAttributes> iterator = getAttributes().iterator(); iterator.hasNext();){
			SysConfigAttributes item = iterator.next();
			if(name.equals(item.getName())){
				return item.getValue();
			}
		}
		return null;
	}
}
