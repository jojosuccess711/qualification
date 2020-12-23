/**
 * @filename:QaClause 2020/02/20
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

/**   
 *  
 * @Description:  评审条件
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_clause")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaClause implements Serializable {

	private static final long serialVersionUID = 1582186295507L;
	
    @Id
	@ApiModelProperty(name = "id", value = "")
	private String id;

    @Column(name = "category_id")
	@ApiModelProperty(name = "categoryId", value = "参评职位id（qa_category表主键）")
	private String categoryId;

    @Column(name = "clause_num")
	@ApiModelProperty(name = "clauseNum", value = "评审条件序号")
	private Integer clauseNum;

	@ApiModelProperty(name = "content", value = "评审条件内容")
	private String content;

	@ApiModelProperty(name = "type", value = "条件要求：0：不满足（否）  1：满足（是） 2：具备其一（是）")
	private Integer type;

	@ApiModelProperty(name = "annex", value = "是否需要上传附件 0：否 1：是")
	private Long annex;

	@Column(name = "repeat_index")
	@ApiModelProperty(name = "repeatIndex", value = "重复条件内容的repeat_index 相同")
	private Integer repeatIndex;

	@Transient
	private QaApplyRecordAttribute qaApplyRecordAttribute;

}
