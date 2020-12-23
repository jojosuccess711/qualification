/**
 * @filename:QaExpertCommit 2020年8月24日
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
import java.io.Serializable;
import java.util.Date;

/**   
 *  
 * @Description:  专家提交信息
 * @Author:       Mnie   
 * @CreateDate:   2020年8月24日
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_expert_commit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaExpertCommit implements Serializable {

	private static final long serialVersionUID = 1599705429571L;
	
    @Id
	@ApiModelProperty(name = "id", value = "主键")
	private String id;

    @Column(name = "user_id")
	@ApiModelProperty(name = "userId", value = "专家id")
	private String userId;

	@ApiModelProperty(name = "rounds", value = "轮次")
	private String rounds;

    @Column(name = "type_status")
	@ApiModelProperty(name = "typeStatus", value = "评审职称")
	private String typeStatus;

    private String lastCategory;

	@ApiModelProperty(name = "commit", value = "提交状态 0 未提交 1 已提交")
	private String commit;

	private String pic;

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

}
