/**
 * @filename:QaBaseClauseItem 2020/02/20
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
import java.io.Serializable;

/**   
 *  
 * @Description:  条件子项
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_base_clause_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaBaseClauseItem implements Serializable {

	private static final long serialVersionUID = 1582696505702L;
	
    @Id
	@ApiModelProperty(name = "id", value = "")
	private String id;

    @Column(name = "clause_id")
	@ApiModelProperty(name = "clauseId", value = "条件项id")
	private String clauseId;

	@ApiModelProperty(name = "code", value = "编号")
	private String code;

	@ApiModelProperty(name = "title", value = "标头")
	private String title;

	@ApiModelProperty(name = "content", value = "内容")
	private String content;

	@ApiModelProperty(name = "memo", value = "备注")
	private String memo;

	@ApiModelProperty(name = "type", value = "类别 select 选择框 input 输入框")
	private String type;

    @Column(name = "type_validate")
	@ApiModelProperty(name = "typeValidate", value = "类型校验 null 默认 select 下拉框(relation参数校验) text 文本 number 数字")
	private String typeValidate;

    @Column(name = "location_index")
	@ApiModelProperty(name = "locationIndex", value = "位置下标")
	private Integer locationIndex;

	@ApiModelProperty(name = "value", value = "值")
	private String value;

	@ApiModelProperty(name = "relation", value = "关系引用表(select) id")
	private String relation;

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
