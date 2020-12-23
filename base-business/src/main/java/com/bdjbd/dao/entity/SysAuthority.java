/**
 * @filename:SysAuthority 2019/12/03
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
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**   
 *  
 * @Description:  权限
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Table(name = "sys_authority")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAuthority implements Serializable {

	public enum Type{
		/** 管理端 */
		ADMIN
	}

	private static final long serialVersionUID = 1575429493173L;

	@Id
	@ApiModelProperty(name = "id", value = "主键")
	private String id;

	@ApiModelProperty(name = "name", value = "权限名称")
	private String name;
	
	@ApiModelProperty(name = "authority", value = "权限值")
	private String authority;

	@ApiModelProperty(name = "description", value = "描述")
	private String description;

	@ApiModelProperty(name = "type", value = "类型0: admin 1: user")
	private Integer type;

	@ApiModelProperty(name = "url", value = "权限url")
	private String url;

	@ApiModelProperty(name = "method", value = "")
	private String method;

	@ApiModelProperty(name = "icon", value = "图标")
	private String icon;

	@ApiModelProperty(name = "status", value = "状态")
	private Boolean status;

	@ApiModelProperty(name = "orders", value = "排序")
	private Integer orders;

	@ApiModelProperty(name = "parent", value = "父级id")
	private String parent;

    @Column(name = "tree_path")
	@ApiModelProperty(name = "treePath", value = "树路径")
	private String treePath;

	@ApiModelProperty(name = "grade", value = "层级")
	private Integer grade;

	@Column(name = "link_url")
	@ApiModelProperty(name = "linkUrl", value = "菜单链接")
	private String linkUrl;

}
