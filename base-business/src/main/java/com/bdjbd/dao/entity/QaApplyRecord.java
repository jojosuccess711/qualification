/**
 * @filename:QaApplyRecord 2020/02/20
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
import java.util.List;

/**   
 *  
 * @Description:  申请记录
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_apply_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaApplyRecord implements Serializable {

	private static final long serialVersionUID = 1582186295507L;
	
    @Id
	@ApiModelProperty(name = "id", value = "")
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

    @Column(name = "user_id")
	@ApiModelProperty(name = "userId", value = "申请用户（qa_user_info表主键）")
	private String userId;

    @Column(name = "user_name")
	@ApiModelProperty(name = "userName", value = "申请用户姓名")
	private String userName;

    @Column(name = "category_id")
	@ApiModelProperty(name = "categoryId", value = "申请领域（qa_category表主键）")
	private String categoryId;

    @Column(name = "category_child_id")
	@ApiModelProperty(name = "categoryChildId", value = "申请岗位（qa_category表主键）")
	private String categoryChildId;

    @Column(name = "category_academic_id")
	@ApiModelProperty(name = "categoryAcademicId", value = "申请职称 1：教授   2：副教授")
	private String categoryAcademicId;

    @Column(name = "first_approve_status")
	@ApiModelProperty(name = "firstApproveStatus", value = "初审状态  0：未通过 1：通过 ")
	private Integer firstApproveStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "first_approve_time")
	@ApiModelProperty(name = "firstApproveTime", value = "初审时间")
	private Date firstApproveTime;

    @Column(name = "second_approve_status")
	@ApiModelProperty(name = "secondApproveStatus", value = "复审状态  0：未通过 1：通过   2：审批中 ")
	private Integer secondApproveStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "second_approve_time")
	@ApiModelProperty(name = "secondApproveTime", value = "复审时间")
	private Date secondApproveTime;

	@Column(name = "approve_opinion")
	@ApiModelProperty(name = "approveOpinion", value = "复审意见")
	private String approveOpinion;

    @Column(name = "approve_user_id")
	@ApiModelProperty(name = "approveUserId", value = "审批人id")
	private String approveUserId;

    @Column(name = "approve_user_name")
	@ApiModelProperty(name = "approveUserName", value = "审批人姓名")
	private String approveUserName;
	/**
	 *	申请条件
	 */
	@Transient
    private List<QaApplyRecordAttribute> qaApplyRecordAttributes;

    @ApiModelProperty(name = "status", value = "0：仅存储   1：进入申请")
    private Integer status;

    /*user 现任专业技术职务*/
    @Transient
    private String technologyTitle;

    /*各项审核通过数目*/
    @Transient
    private Integer countPast;

}
