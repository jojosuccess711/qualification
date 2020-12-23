/**
 * @filename:QaAcademicRecordLog 2020/02/20
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
 * @Description:  职称评审记录日志
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_academic_record_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaAcademicRecordLog implements Serializable {

	private static final long serialVersionUID = 1583392332443L;
	
    @Id
	@ApiModelProperty(name = "id", value = "主键")
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

    @Column(name = "academic_record_id")
	@ApiModelProperty(name = "academicRecordId", value = "职称记录id")
	private String academicRecordId;

    @Column(name = "operator_id")
	@ApiModelProperty(name = "operatorId", value = "操作人id")
	private String operatorId;

	@ApiModelProperty(name = "operator", value = "操作人")
	private String operator;

	@ApiModelProperty(name = "memo", value = "备注")
	private String memo;

	@ApiModelProperty(name = "content", value = "审批内容")
	private String content;

    @Column(name = "before_info")
	@ApiModelProperty(name = "beforeInfo", value = "之前信息")
	private String beforeInfo;

    @Column(name = "after_info")
	@ApiModelProperty(name = "afterInfo", value = "之后信息")
	private String afterInfo;

	@ApiModelProperty(name = "type", value = "操作类型1. 电脑审批2. 人工审批")
	private Integer type;

}
