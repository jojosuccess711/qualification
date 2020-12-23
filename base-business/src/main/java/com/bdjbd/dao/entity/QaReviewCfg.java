/**
 * @filename:QaReviewCfg 2020年8月24日
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
 * @Description:  评审配置
 * @Author:       Mnie   
 * @CreateDate:   2020年8月24日
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_review_cfg")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaReviewCfg implements Serializable {

	private static final long serialVersionUID = 1598516989203L;
	
    @Id
	@ApiModelProperty(name = "id", value = "主键")
	private String id;

    @Column(name = "type_status")
	@ApiModelProperty(name = "typeStatus", value = "评审职称，1 正高续正高 2副高晋正高 3 副高续副高 4 中职晋副高")
	private String typeStatus;

	@ApiModelProperty(name = "rounds", value = "轮次 1 第一轮 2第二轮")
	private String rounds;

    @Column(name = "start_time")
	@ApiModelProperty(name = "startTime", value = "评审开始时间")
	private String startTime;

    @Column(name = "end_time")
	@ApiModelProperty(name = "endTime", value = "评审结束时间")
	private String endTime;

	@ApiModelProperty(name = "experts", value = "专家ids")
	private String experts;

    @Column(name = "experts_num")
	@ApiModelProperty(name = "expertsNum", value = "专家票数")
	private String expertsNum;

    @Column(name = "promotion_quota")
	@ApiModelProperty(name = "promotionQuota", value = "晋升名额")
	private Integer promotionQuota;

    private String type;

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

	@Transient
	private String commit;

}
