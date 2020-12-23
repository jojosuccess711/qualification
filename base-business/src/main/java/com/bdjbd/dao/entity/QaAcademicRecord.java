/**
 * @filename:QaAcademicRecord 2020/02/20
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
import java.util.Map;

/**
 *
 * @Description: 职称评审记录
 * @Author: DBC
 * @CreateDate: 2020/02/20
 * @Version: V1.0
 *
 */
@Table(name = "qa_academic_record1")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaAcademicRecord implements Serializable {

    private static final long serialVersionUID = 1583392332443L;

    @Id
    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_date")
    @ApiModelProperty(name = "createDate", value = "创建时间")
    private Date createDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "modify_date")
    @ApiModelProperty(name = "modifyDate", value = "修改时间")
    private Date modifyDate;

    @Column(name = "user_id")
    @ApiModelProperty(name = "userId", value = "用户id")
    private String userId;

    @Column(name = "user_name")
    @ApiModelProperty(name = "userName", value = "姓名")
    private String userName;

    @Column(name = "category_id")
    @ApiModelProperty(name = "categoryId", value = "五大类id")
    private String categoryId;

    @Column(name = "category_child_id")
    @ApiModelProperty(name = "categoryChildId", value = "领域id")
    private String categoryChildId;

    @Column(name = "category_academic_id")
    @ApiModelProperty(name = "categoryAcademicId", value = "职称id")
    private String categoryAcademicId;

    @ApiModelProperty(name = "status", value = "状态1：审核通过10：待提交审核（默认）20：待电脑审核21：电脑审核未通过30：待人工审核（电脑审核通过）31：人工审核未通过")
    private Integer status;

    @Column(name = "first_check_status")
    @ApiModelProperty(name = "firstCheckStatus", value = "电脑审核状态0：初始化状态（默认）1：审核通过2:  待审核3：电脑审核未通过")
    private Integer firstCheckStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "first_check_date")
    @ApiModelProperty(name = "firstCheckDate", value = "初审时间")
    private Date firstCheckDate;

    @Column(name = "second_check_status")
    @ApiModelProperty(name = "secondCheckStatus", value = "人工审核状态0：初始状态1：审核通过2：待人工审核3：人工审核未通过")
    private Integer secondCheckStatus;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "second_check_date")
    @ApiModelProperty(name = "secondCheckDate", value = "人工审核时间")
    private Date secondCheckDate;

    @ApiModelProperty(name = "memo", value = "审核备注")
    private String memo;

    @ApiModelProperty(name = "cur_academic_id", value = "现任职称id")
    private String curAcademicId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "cur_academic_date")
    @ApiModelProperty(name = "curAcademicDate", value = "现任职称时间")
    private Date curAcademicDate;

    @ApiModelProperty(name = "cur_category_id", value = "现领域id")
    private String curCategoryId;

    @ApiModelProperty(name = "cur_category_child_id", value = "现处岗位id")
    private String curCategoryChildId;

    @ApiModelProperty(name = "academic_type", value = "评定职称类型\\r\\n100：初职续任初职\\r\\n101：初职晋升中职\\r\\n110：中职续任中职\\r\\n111：中职晋升副高\\r\\n120：副高续任副高\\r\\n121：副高晋升正高\\r\\n130：正高续任正高")
    private String academicType;

    @ApiModelProperty(name = "has_across", value = "是否跨领域")
    private Boolean hasAcross;

    @ApiModelProperty(name = "has_user_input", value = "是否用户申请")
    private Boolean hasUserInput;

    @ApiModelProperty(name = "attr0", value = "符合的条件的item数目")
    private String attr0;

    @ApiModelProperty(name = "attr1", value = "不符合的条件的item数目")
    private String attr1;

    @ApiModelProperty(name = "attr2", value = "")
    private String attr2;

    @ApiModelProperty(name = "attr3", value = "")
    private String attr3;

    @ApiModelProperty(name = "attr4", value = "")
    private String attr4;
    @Transient
    private String categoryName;
    @Transient
    private String categoryChildName;
    @Transient
    private String categoryAcademicName;
    @Transient
    private String curCategoryName;
    @Transient
    private String curCategoryChildName;
    @Transient
    private String curAcademicName;

    // 用户未通过的条件组
    @Transient
    private List<Map<?, ?>> notPassGroups;
}
