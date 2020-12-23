/**
 * @filename:QaCategory 2020/02/20
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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;

/**   
 *  
 * @Description:  评审分类
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaCategory implements Serializable {

	private static final long serialVersionUID = 1582186295507L;
	
    @Id
	@ApiModelProperty(name = "id", value = "")
	private String id;

	@ApiModelProperty(name = "name", value = "职业领域或岗位名称")
	private String name;

	@Column(name = "another_name")
	@ApiModelProperty(name = "anotherName", value = "别称")
	private String anotherName;

	@ApiModelProperty(name = "type", value = "类型  0：职业领域  1：岗位类型  2：职称")
	private Integer type;

	@ApiModelProperty(name = "title", value = "标题")
	private String title;

	@ApiModelProperty(name = "description", value = "描述")
	private String description;

	@ApiModelProperty(name = "icon", value = "图标")
	private String icon;

	@ApiModelProperty(name = "orders", value = "排序")
	private Integer orders;

	@ApiModelProperty(name = "parent", value = "父级")
	private String parent;

    @Column(name = "apply_num")
	@ApiModelProperty(name = "applyNum", value = "岗位职称申请累计人数（有新增申请时变更申请总数）")
	private Integer applyNum;

	@Column(name = "first_approve_num")
	@ApiModelProperty(name = "firstApproveNum", value = "初审通过人数")
	private Integer firstApproveNum;

	@Column(name = "approve_num")
	@ApiModelProperty(name = "approveNum", value = "审核通过人数")
	private Integer approveNum;

	@Transient
	List<QaCategory> childList;

	// 条件列表
	@Transient
	List<QaClause> qaClauseList;

	// 是否申请过
	@Transient
	private boolean hasApply;

	@Transient
	private long firstApproveCount;

	@Transient
	private long approvedCount;


}
