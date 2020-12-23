/**
 * @filename:QaBaseProject 2020/02/20
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
 * @Description:  项目项表
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_base_project")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaBaseProject implements Serializable {

	private static final long serialVersionUID = 1582696505702L;
	
    @Id
	@ApiModelProperty(name = "id", value = "")
	private String id;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date")
	@ApiModelProperty(name = "createDate", value = "")
	private Date createDate;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "modify_date")
	@ApiModelProperty(name = "modifyDate", value = "")
	private Date modifyDate;

	@ApiModelProperty(name = "name", value = "")
	private String name;

	@ApiModelProperty(name = "orders", value = "")
	private Integer orders;

	@ApiModelProperty(name = "titile", value = "")
	private String titile;

	@ApiModelProperty(name = "content", value = "")
	private String content;

    @Column(name = "has_list")
	@ApiModelProperty(name = "hasList", value = "")
	private Boolean hasList;

	@ApiModelProperty(name = "category", value = "领域")
	private String category;

}
