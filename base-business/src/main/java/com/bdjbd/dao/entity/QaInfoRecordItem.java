/**
 * @filename:QaInfoRecordItem 2020/02/20
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
import javax.persistence.Table;
import java.io.Serializable;

/**   
 *  
 * @Description:  录入信息记录项表
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_info_record_item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaInfoRecordItem implements Serializable {

	private static final long serialVersionUID = 1582705217694L;
	
    @Column(name = "record_id")
	@ApiModelProperty(name = "recordId", value = "")
	private String recordId;

    @Column(name = "item_id")
	@ApiModelProperty(name = "itemId", value = "")
	private String itemId;

	@ApiModelProperty(name = "value", value = "")
	private String value;

}
