/**
 * @filename:BaseParameterGroup 2020/02/20
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
import org.springframework.beans.factory.annotation.Autowired;
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
 * @Description:  参数组表
 * @Author:       DBC   
 * @CreateDate:   2020/02/20
 * @Version:      V1.0
 *    
 */
@Table(name = "base_parameter_group")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseParameterGroup implements Serializable {

	private static final long serialVersionUID = 1582853040590L;
	
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

	@ApiModelProperty(name = "name", value = "组名称")
	private String name;

	@ApiModelProperty(name = "type", value = "组类型0. 默认1. 基本信息（暂时未启用）2. 课堂教学信息采集标准3. 教学奖励信息采集标准4. 教学评价信息采集标准5. 教学指导信息采集标准6. 科研项目信息采集标准7. 专利信息采集标准8. 科技奖励信息采集标准9. 学术论文信息采集标准10.著作信息采集标准11.教材信息采集标准12.军标信息采集标准13.咨询报告信息采集标准14.参加学术会议信息采集标准15.参加学术团体信息采集标准16.担任收录期刊审稿职务信息采集标准17.部队任（代）职经历信息采集标准18.受训经历信息采集标准19.维和援外任务经历信息采集标准20.出国留学经历信息采集标准21.参加重大军事行动/部队活动信息采集标准22.服务部队信息采集标准23.获人才工程计划和奖励表彰项目信息采集标准")
	private Integer type;

	@ApiModelProperty(name = "titile", value = "标题")
	private String titile;

	@ApiModelProperty(name = "introduction", value = "介绍")
	private String introduction;

	@ApiModelProperty(name = "orders", value = "排序")
	private Integer orders;

	@ApiModelProperty(name = "memo", value = "备注")
	private String memo;

	@ApiModelProperty(name = "url", value = "链接")
	private String url;

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

	@ApiModelProperty(name = "grade", value = "层级")
	private Integer grade;

	@ApiModelProperty(name = "status", value = "状态 0 禁用 1 启用")
	private Integer status;

	/**
	 * 当前分类录入数据中包含的下拉选项
	 */
	@Transient
	private List<BaseParameter> parameters;

	@Transient
	private String recordId;

	/**
	 * 前端是否完整显示
	 */
	@Transient
	private boolean intact = true;

}
