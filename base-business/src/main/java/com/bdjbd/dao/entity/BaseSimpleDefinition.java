/**
 * @filename:BaseSimpleDefinition 2020/02/20
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
 * @Description:  简单数据定义
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "base_simple_definition")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseSimpleDefinition implements Serializable {

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

	@ApiModelProperty(name = "orders", value = "排序")
	private Integer orders;

	@ApiModelProperty(name = "status", value = "状态 0禁用 1启用")
	private Boolean status;

    @Column(name = "category_id")
	@ApiModelProperty(name = "categoryId", value = "领域ID qa_category表主键")
	private String categoryId;

	@ApiModelProperty(name = "name", value = "名称")
	private String name;

	@ApiModelProperty(name = "memo", value = "备注")
	private String memo;

	@ApiModelProperty(name = "type", value = "类型（根据实际场景定义）")
	private String type;

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
