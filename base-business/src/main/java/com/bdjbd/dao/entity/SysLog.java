/**
 * @filename:SysLog 2019/12/03
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
import java.io.Serializable;
import java.util.Date;

/**   
 *  
 * @Description:  系统日志
 * @Author:       DBC   
 * @CreateDate:   2019/12/03
 * @Version:      V1.0
 *    
 */
@Table(name = "sys_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysLog implements Serializable {

	private static final long serialVersionUID = 1576230725092L;
	
    @Id
	@ApiModelProperty(name = "id", value = "")
	private String id;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date")
	@ApiModelProperty(name = "createDate", value = "")
	private Date createDate;

    @Column(name = "operator_id")
	@ApiModelProperty(name = "operatorId", value = "")
	private String operatorId;

	@ApiModelProperty(name = "operator", value = "")
	private String operator;

	@ApiModelProperty(name = "content", value = "")
	private String content;

	@ApiModelProperty(name = "memo", value = "")
	private String memo;

	@ApiModelProperty(name = "type", value = "")
	private Integer type;

    @Column(name = "relation_id")
	@ApiModelProperty(name = "relationId", value = "")
	private String relationId;

    @Column(name = "before_info")
	@ApiModelProperty(name = "beforeInfo", value = "")
	private String beforeInfo;

    @Column(name = "after_info")
	@ApiModelProperty(name = "afterInfo", value = "")
	private String afterInfo;

}
