/**
 * @filename:QaApplyRecordAttribute 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.Iterator;

/**   
 *  
 * @Description:  申请记录属性
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_apply_record_attribute")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaApplyRecordAttribute implements Serializable {

	private static final long serialVersionUID = 1582186295507L;
	
    @Id
	@ApiModelProperty(name = "id", value = "")
	private String id;

    @Column(name = "apply_id")
	@ApiModelProperty(name = "applyId", value = "申请id（qa_apply_record表主键）")
	private String applyId;

    @Column(name = "caluse_id")
	@ApiModelProperty(name = "caluseId", value = "条件id（qa_caluse表主键）")
	private String caluseId;

    @Column(name = "user_choice")
	@ApiModelProperty(name = "userChoice", value = "用户选择结果  1：是 0：否")
	private Integer userChoice;

    @Column(name = "annex_url")
	@ApiModelProperty(name = "annexUrl", value = "附件地址")
	private String annexUrl;

	@ApiModelProperty(name = "past", value = "是否满足业绩条件要求  1：是 0：否  （一个条件下有多个条件要求具备其一时：若至少有一个为是，则所有是否满足业绩条件要求均为是；若一个都为选中，则所有是否满足业绩条件要求均为否）")
	private Long past;

    @Column(name = "fail_reason")
	@ApiModelProperty(name = "failReason", value = "未通过原因（未选中、无附件等）")
	private String failReason;

	@Transient
	private	String	caluseContent;

}
