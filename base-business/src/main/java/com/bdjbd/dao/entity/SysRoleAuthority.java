/**
 * @filename:SysRoleAuthority 2019/12/03
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
 * @Description:  角色权限
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Table(name = "sys_role_authority")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysRoleAuthority implements Serializable {

	private static final long serialVersionUID = 1575429493173L;
	
    @Column(name = "authority_id")
	@ApiModelProperty(name = "authorityId", value = "权限id")
	private String authorityId;

    @Column(name = "role_id")
	@ApiModelProperty(name = "roleId", value = "角色id")
	private String roleId;

}
