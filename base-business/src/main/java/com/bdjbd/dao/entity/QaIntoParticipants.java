/**
 * @filename:QaIntoParticipants 2020年8月24日
 * @project 人员录入  V1.0
 * Copyright(c) 2018 Mnie Co. Ltd. 
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
 * @Description:  进入参评人员
 * @Author:       Mnie   
 * @CreateDate:   2020年8月24日
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_into_participants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaIntoParticipants implements Serializable {

	private static final long serialVersionUID = 1599013206406L;
	
    @Id
	@ApiModelProperty(name = "id", value = "主键")
	private String id;

    @Column(name = "user_id")
	@ApiModelProperty(name = "userId", value = "用户id")
	private String userId;

	@ApiModelProperty(name = "category", value = "领域")
	private String category;

    @Column(name = "last_category")
	@ApiModelProperty(name = "lastCategory", value = "最后评审领域")
	private String lastCategory;

	@ApiModelProperty(name = "name", value = "姓名")
	private String name;

    @Column(name = "id_card")
	@ApiModelProperty(name = "idCard", value = "身份证号")
	private String idCard;

    @Column(name = "current_title")
	@ApiModelProperty(name = "currentTitle", value = "现职称")
	private String currentTitle;

    @Column(name = "participating_title")
	@ApiModelProperty(name = "participatingTitle", value = "参评职称")
	private String participatingTitle;

    @Column(name = "post_type")
	@ApiModelProperty(name = "postType", value = "参评岗位")
	private String postType;

    @Column(name = "post_result")
	@ApiModelProperty(name = "postResult", value = "参评结果")
	private String postResult;

    @Column(name = "finally_post")
	@ApiModelProperty(name = "finallyPost", value = "最终参评岗位")
	private String finallyPost;

	@ApiModelProperty(name = "rounds", value = "轮次")
	private String rounds;

	@ApiModelProperty(name = "tickets", value = "票数")
	private Integer tickets;

    @Column(name = "ticket_ids")
	@ApiModelProperty(name = "ticketIds", value = "专家ids")
	private String ticketIds;

	@Column(name = "ticket_status")
	@ApiModelProperty(name = "ticketStatus", value = "专家提交ids")
    private String ticketStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
	@ApiModelProperty(name = "createTime", value = "创建时间")
	private Date createTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
	@ApiModelProperty(name = "updateTime", value = "修改时间")
	private Date updateTime;

	private String type;

	@Transient
	private String next;

	@Transient
	private String nextTwo;

	@Transient
	private Integer total;

}
