/**
 * @filename:SysUserRole 2019/12/03
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
 * @Description:  用户角色
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Table(name = "sys_admin_role")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysAdminRole implements Serializable {

	private static final long serialVersionUID = 1575429493173L;
	
    @Column(name = "admin_id")
	@ApiModelProperty(name = "adminId", value = "管理员主键id")
	private String adminId;

    @Column(name = "roles_id")
	@ApiModelProperty(name = "rolesId", value = "角色id")
	private String rolesId;

	@ApiModelProperty(name = "type", value = "类型0:admin 1:user")
	private Integer type;

}
