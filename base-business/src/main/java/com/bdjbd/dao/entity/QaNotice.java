/**
 * @filename:QaNotice 2020/02/20
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
 * @Description:  系统公告
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_notice")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaNotice implements Serializable {

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

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "begin_date")
	@ApiModelProperty(name = "beginDate", value = "开始时间")
	private Date beginDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "end_date")
	@ApiModelProperty(name = "endDate", value = "结束时间")
	private Date endDate;

	@ApiModelProperty(name = "content", value = "公告内容")
	private String content;

	@ApiModelProperty(name = "title", value = "标题")
	private String title;

    @Column(name = "has_list")
	@ApiModelProperty(name = "hasList", value = "是否列出 0：否  1：是")
	private Boolean hasList;

	@ApiModelProperty(name = "orders", value = "排序")
	private Integer orders;

    @Column(name = "has_top")
	@ApiModelProperty(name = "hasTop", value = "是否置顶 0 默认 1 置顶")
	private Boolean hasTop;

}
