/**
 * @filename:QaInfoRecord 2020/02/20
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
 * @Description:  录入信息记录表
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_info_record")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaInfoRecord implements Serializable {

	private static final long serialVersionUID = 1582705217694L;
	
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

    @Column(name = "user_id")
	@ApiModelProperty(name = "userId", value = "用户id")
	private String userId;

    @Column(name = "user_name")
	@ApiModelProperty(name = "userName", value = "用户姓名")
	private String userName;

    @Column(name = "final_category_child_id")
	@ApiModelProperty(name = "finalCategoryChildId", value = "最终审评职称id")
	private String finalCategoryChildId;

    @Column(name = "final_category_child_name")
	@ApiModelProperty(name = "finalCategoryChildName", value = "最终审评职称名称(全称 格式为：领域--岗位--职称)")
	private String finalCategoryChildName;

    @Column(name = "final_category_tree_path")
	@ApiModelProperty(name = "finalCategoryTreePath", value = "职称treepath路径")
	private String finalCategoryTreePath;

    @Column(name = "first_approve_status")
	@ApiModelProperty(name = "firstApproveStatus", value = "初次审核状态0：未通过1：通过 ")
	private Integer firstApproveStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "first_approve_time")
	@ApiModelProperty(name = "firstApproveTime", value = "初次审核时间")
	private Date firstApproveTime;

    @Column(name = "first_approve_user")
	@ApiModelProperty(name = "firstApproveUser", value = "初次审核人")
	private String firstApproveUser;

    @Column(name = "first_approve_user_id")
	@ApiModelProperty(name = "firstApproveUserId", value = "初次审核人id")
	private String firstApproveUserId;

    @Column(name = "first_approve_opinion")
	@ApiModelProperty(name = "firstApproveOpinion", value = "初次审核意见")
	private String firstApproveOpinion;

    @Column(name = "first_category_child_id")
	@ApiModelProperty(name = "firstCategoryChildId", value = "初审符合岗位（qa_category表主键）")
	private String firstCategoryChildId;

    @Column(name = "first_category_tree_path")
	@ApiModelProperty(name = "firstCategoryTreePath", value = "初审符合岗位树路径")
	private String firstCategoryTreePath;

    @Column(name = "second_approve_status")
	@ApiModelProperty(name = "secondApproveStatus", value = "复审审核状态0：未通过1：通过 2：审批中")
	private Integer secondApproveStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "second_approve_time")
	@ApiModelProperty(name = "secondApproveTime", value = "复审时间")
	private Date secondApproveTime;

    @Column(name = "second_approve_user")
	@ApiModelProperty(name = "secondApproveUser", value = "复审人")
	private String secondApproveUser;

    @Column(name = "second_approve_user_id")
	@ApiModelProperty(name = "secondApproveUserId", value = "复审人id")
	private String secondApproveUserId;

    @Column(name = "second_approve_opinion")
	@ApiModelProperty(name = "secondApproveOpinion", value = "复审意见")
	private String secondApproveOpinion;

    @Column(name = "second_category_child_id")
	@ApiModelProperty(name = "secondCategoryChildId", value = "复审给定岗位id")
	private String secondCategoryChildId;

    @Column(name = "second_category_tree_path")
	@ApiModelProperty(name = "secondCategoryTreePath", value = "复审给定岗位路径")
	private String secondCategoryTreePath;

	@ApiModelProperty(name = "status", value = "状态0： 默认保存10：待电脑审核11：电脑审核未通过12：电脑审核通过20：待人工审核21：人工审核未通过22：人工审核通过")
	private Integer status;

	@ApiModelProperty(name = "attr0", value = "")
	private String attr0;

	@ApiModelProperty(name = "attr1", value = "")
	private String attr1;

	@ApiModelProperty(name = "attr2", value = "")
	private String attr2;

	@ApiModelProperty(name = "attr3", value = "")
	private String attr3;

	@ApiModelProperty(name = "attr4", value = "")
	private String attr4;

}
