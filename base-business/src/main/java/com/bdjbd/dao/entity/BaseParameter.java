/**
 * @filename:BaseParameter 2020/02/20
 * @project BDJBD  V1.0
 * Copyright(c) 2018 DBC Co. Ltd. 
 * All right reserved. 
 */
package com.bdjbd.dao.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 *
 * @Description: 参数表
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Table(name = "base_parameter")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseParameter implements Serializable, Cloneable {

    private static final long serialVersionUID = 1582853040590L;
    @Id
    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @Column(name = "group_id")
    @ApiModelProperty(name = "groupId", value = "参数组id")
    private String groupId;

    @ApiModelProperty(name = "name", value = "参数名称")
    private String name;

    @ApiModelProperty(name = "title", value = "标题")
    private String title;

    @ApiModelProperty(name = "type", value = "类型select 下拉框text 文本框select&text 下拉框可输入date 日期")
    private String type;

    @Column(name = "type_validate")
    @ApiModelProperty(name = "typeValidate", value = "类型校验type      ---  valueselect    关联definition表type值text       integer 正整数            name 姓名            default 默认（小于255）            number 小数date     yyyy.MM.dd            yyyy.MM            yyyy.MM-yyyy.MM|至今            yyyy")
    private String typeValidate;

    @ApiModelProperty(name = "orders", value = "排序")
    private Integer orders;

    @ApiModelProperty(name = "attr0", value = "")
    private String attr0;

    @ApiModelProperty(name = "attr1", value = "")
    private String attr1;

    @ApiModelProperty(name = "attr2", value = "是否必填字段 1：必填 0：非必填")
    private String attr2;

    @ApiModelProperty(name = "attr3", value = "前端显示参数之间的关联关系使用，json字符串")
    private String attr3;

    @ApiModelProperty(name = "attr4", value = "后端验证逻辑，json字符串")
    private String attr4;

    @ApiModelProperty(name = "attr5", value = "正则")
    private String attr5;

    @ApiModelProperty(name = "status", value = "状态 0 禁用 1 启用")
    private Integer status;
    /**
     * 下拉列包含的选项值
     */
    @Transient
    private List<BaseSimpleDefinition> options;
    /**
     * 用户选项
     */
    @Transient
    private List<QaStandardRecordItem> userSelection;

    @Transient
    private List<BaseParameter> childParameters;

    @Transient
    private List<BaseParameterGroup> baseParameterGroups;
    @Transient
    private String maxOrder;

    @Transient
    private String userParameterValue;

    @Transient
    private String recordId;
    @Transient
    private List<Integer> orderList;
    @Transient
    private String isLevel; // 2有二级表，1没有


    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
