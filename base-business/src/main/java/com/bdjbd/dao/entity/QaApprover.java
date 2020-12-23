/**
 * @filename:QaApprover 2019/11/07
 * @project 智慧停车  V1.0
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
 * @Description:  审批表
 * @Author:       DBC   
 * @CreateDate:   2019/11/07
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_approver")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaApprover implements Serializable {

	private static final long serialVersionUID = 1587977606343L;
	
    @Id
	@ApiModelProperty(name = "id", value = "主键")
	private String id;

    @Column(name = "approver_id")
	@ApiModelProperty(name = "approverId", value = "审批人id")
	private String approverId;

    @Column(name = "approver_type")
	@ApiModelProperty(name = "approverType", value = "审批类型 1政治工作处2.教务处。3科研处。4教保处")
	private String approverType;

    @Column(name = "admin_id")
	@ApiModelProperty(name = "adminId", value = "人员id")
	private String adminId;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "approver_time")
	@ApiModelProperty(name = "approverTime", value = "审批时间")
	private Date approverTime;

    @Column(name = "Approver_status")
	@ApiModelProperty(name = "ApproverStatus", value = "审批状态")
	private String ApproverStatus;

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

}
