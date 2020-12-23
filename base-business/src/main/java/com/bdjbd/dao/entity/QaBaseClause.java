/**
 * @filename:QaBaseClause 2020/02/20
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
import java.util.List;

/**   
 *  
 * @Description:  基本条件项
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_base_clause")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaBaseClause implements Serializable {

	private static final long serialVersionUID = 1582696505702L;
	
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

	@ApiModelProperty(name = "code", value = "编码code")
	private String code;

	@ApiModelProperty(name = "title", value = "标头")
	private String title;

	@ApiModelProperty(name = "content", value = "")
	private String content;

	@ApiModelProperty(name = "memo", value = "备注")
	private String memo;

	@ApiModelProperty(name = "condition", value = "条件表达式")
	private String condition;

	@ApiModelProperty(name = "orders", value = "排序")
	private Integer orders;

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

	@Transient
	private List<QaBaseClauseItem> clauseItemList;

}
