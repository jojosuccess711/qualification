/**
 * @filename:QaStandardRecordItem 2020/02/20
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
 * @Description:  用户标准信息记录项
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_standard_record_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaStandardRecordItem implements Serializable {

	private static final long serialVersionUID = 1582853040590L;
	
    @Id
	@ApiModelProperty(name = "id", value = "主键")
	private String id;

    @Column(name = "group_category")
	@ApiModelProperty(name = "groupCategory", value = "数据组")
	private String groupCategory;

	@ApiModelProperty(name = "orders", value = "排序")
	private Integer orders;

    @Column(name = "record_id")
	@ApiModelProperty(name = "recordId", value = "采集标准申请记录id （qa_standard_record表id）")
	private String recordId;

    @Column(name = "parameter_group_id")
	@ApiModelProperty(name = "parameterGroupId", value = "base_parameter_group表id")
	private String parameterGroupId;

    @Column(name = "parameter_id")
	@ApiModelProperty(name = "parameterId", value = "base_parameter表id")
	private String parameterId;

    @Column(name = "parameter_value")
	@ApiModelProperty(name = "parameterValue", value = "参数值")
	private String parameterValue;

    @Column(name = "parameter_annex_url")
	@ApiModelProperty(name = "parameterAnnexUrl", value = "附件地址")
	private String parameterAnnexUrl;

    @Column(name = "attr0")
	@ApiModelProperty(name = "attr0", value = "数据验证")
	private String attr0;

    @Column(name = "attr1")
	@ApiModelProperty(name = "attr1", value = "")
	private Integer attr1;

    @Column(name = "attr2")
	@ApiModelProperty(name = "attr2", value = "")
	private String attr2;

    @Column(name = "attr3")
	@ApiModelProperty(name = "attr3", value = "")
	private String attr3;

    @Column(name = "attr4")
	@ApiModelProperty(name = "attr4", value = "")
	private String attr4;

	@Transient
	private String reviewStatus;

    @Transient
	private String groupId;

    @Transient
	private String type;

    @Transient
	private String typeValidate;

    @Transient
    private String name;

	@Transient
	private BaseParameter parameter;

}
