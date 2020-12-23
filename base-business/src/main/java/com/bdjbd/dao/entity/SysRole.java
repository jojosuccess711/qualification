/**
 * @filename:SysRole 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**   
 *  
 * @Description:  角色
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Table(name = "sys_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRole implements Serializable {

	private static final long serialVersionUID = 1575429493173L;

	@Id
	@ApiModelProperty(name = "id", value = "主键")
	private String id;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date")
	@ApiModelProperty(name = "createDate", value = "创建日期")
	private Date createDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "modify_date")
	@ApiModelProperty(name = "modifyDate", value = "修改日期")
	private Date modifyDate;

	@ApiModelProperty(name = "description", value = "描述")
	private String description;

    @Column(name = "is_system")
	@ApiModelProperty(name = "isSystem", value = "是否系统角色")
	private Boolean isSystem;

	@ApiModelProperty(name = "name", value = "角色名")
	private String name;

	@ApiModelProperty(name = "type", value = "")
	private Integer type;

	@Transient
	private List<SysAuthority> authorities;

	@Transient
	public Boolean hasAuthority(String id){
		if(authorities != null){
			for(Iterator<SysAuthority> iterator = authorities.iterator(); iterator.hasNext();){
				if(iterator.next().getId().equals(id))
					return true;
			}
		}
		return false;
	}

}
