/**
 * @filename:QaApplyLog 2020/02/20
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
 * @Description:  申请审核动态日志
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_apply_log")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaApplyLog implements Serializable {

	private static final long serialVersionUID = 1582186295507L;
	
    @Id
	@ApiModelProperty(name = "id", value = "")
	private String id;

    @Column(name = "apply_id")
	@ApiModelProperty(name = "applyId", value = "申请记录id（qa_apply_record表主键）")
	private String applyId;

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

    @Column(name = "user_name")
	@ApiModelProperty(name = "userName", value = "申请用户姓名  冗余字段")
	private String userName;

    @Column(name = "user_id")
	@ApiModelProperty(name = "userId", value = "用户id  冗余字段")
	private String userId;

    @Column(name = "category_id")
	@ApiModelProperty(name = "categoryId", value = "申请领域（与qa_apply_record中该字段相同）冗余字段")
	private String categoryId;

	@ApiModelProperty(name = "type", value = "操作类型：1：评审通过    2：评审未通过")
	private Integer type;

    @Column(name = "category_child_id")
	@ApiModelProperty(name = "categoryChildId", value = "申请岗位（与qa_apply_record中该字段相同）冗余字段")
	private String categoryChildId;

    @Column(name = "category_academic_id")
	@ApiModelProperty(name = "categoryAcademicId", value = "申请职称（与qa_apply_record中该字段相同）冗余字段")
	private String categoryAcademicId;

    @Transient
    private String categoryName = "";

}
