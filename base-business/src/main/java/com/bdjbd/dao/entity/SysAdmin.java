/**
 * @filename:SysAdmin 2019/12/03
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.entity;

import com.bdjbd.common.util.FieldValidateUtil;
import com.bdjbd.common.valid.SaveGroup;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**   
 *  
 * @Description:  管理员
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Table(name = "sys_admin")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAdmin implements Serializable {

	private static final long serialVersionUID = 1575429493173L;

	@Id
	@ApiModelProperty(name = "id", value = "主键ID")
	private String id;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date")
	@ApiModelProperty(name = "createDate", value = "创建时间")
	private Date createDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@Column(name = "modify_date")
	@ApiModelProperty(name = "modifyDate", value = "修改时间")
	private Date modifyDate;

	@ApiModelProperty(name = "department", value = "部门（负责业务）")
	private String department;

	@ApiModelProperty(name = "mobile", value = "")
	private String mobile;

	@ApiModelProperty(name = "email", value = "邮箱")
	@Email
	@NotBlank
	private String email;

    @Column(name = "is_enabled")
	@ApiModelProperty(name = "hasEnabled", value = "是否可用")
	private Boolean hasEnabled;

    @Column(name = "is_locked")
	@ApiModelProperty(name = "isLocked", value = "")
	private Boolean isLocked;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "locked_date")
	@ApiModelProperty(name = "lockedDate", value = "")
	private Date lockedDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "login_date")
	@ApiModelProperty(name = "loginDate", value = "")
	private Date loginDate;

    @Column(name = "login_failure_count")
	@ApiModelProperty(name = "loginFailureCount", value = "")
	private Integer loginFailureCount;

    @Column(name = "login_ip")
	@ApiModelProperty(name = "loginIp", value = "")
	private String loginIp;

	@ApiModelProperty(name = "name", value = "姓名")
	@NotBlank
	@Pattern(regexp = FieldValidateUtil.REGEX_CHINESE)
	@Length(min = 2, max = 6)
	private String name;

	@ApiModelProperty(name = "password", value = "密码")
	@NotBlank(groups = {SaveGroup.class})
	@Pattern(groups = {SaveGroup.class}, regexp = FieldValidateUtil.REGEX_PASSWORD)
	@Length(groups = {SaveGroup.class}, min = 4, max = 20)
	private String password;

	@ApiModelProperty(name = "username", value = "账号")
	@NotBlank
	@Length(min = 4, max = 20)
	private String username;

	@ApiModelProperty(name = "memo", value = "")
	private String memo;

	@ApiModelProperty(name = "roles", value = "角色项")
	private String roles;

	@Transient
	private List<SysRole> roleLists;

	@Transient
	private String attr0;

	@Transient
	private String category;
	@Transient
	private String technologyTitle;

	@Transient
	public Boolean hasRole(String id){
		if(roleLists != null){
			for(Iterator<SysRole> iterator = roleLists.iterator(); iterator.hasNext();){
				if(iterator.next().getId().equals(id))
					return true;
			}
		}
		return false;
	}
}
