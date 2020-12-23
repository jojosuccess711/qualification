/**
 * @filename:QaAcademicRecord 2020/02/20
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

/**   
 *  
 * @Description:  职称评审记录
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_academic_record_item1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaAcademicRecordItem implements Serializable {

	private static final long serialVersionUID = 1L;
	
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

    @Column(name = "record_id")
	@ApiModelProperty(name = "recordId", value = "职称记录id")
	private String recordId;

    @Column(name = "clause_group_id")
	@ApiModelProperty(name = "clauseGroupId", value = "条件组")
	private String clauseGroupId;

    @Column(name = "standard_value")
	@ApiModelProperty(name = "standardValue", value = "标准值")
	private String standardValue;

    @Column(name = "actual_value")
	@ApiModelProperty(name = "actualValue", value = "实际值")
	private String actualValue;

	@Column(name = "has_pass")
	@ApiModelProperty(name = "hasPass", value = "是否满足")
	private Boolean hasPass;

	@Column(name = "inner_group")
	@ApiModelProperty(name = "innerGroup", value = "innerGroup")
	private String innerGroup;

	@Column(name = "inner_group_has_pass")
	@ApiModelProperty(name = "innerGroupHasPass", value = "innerGroupHasPass")
	private Boolean innerGroupHasPass;

	@Transient
	private String clauseGroupName;
}
