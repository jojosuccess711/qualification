/**
 * @filename:QaParticipants 2020年8月24日
 * @project 人员录入  V1.0
 * Copyright(c) 2018 Mnie Co. Ltd.
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
 * @Description: 参评人员
 * @Author: Mnie
 * @CreateDate: 2020年8月24日
 * @Version: V1.0
 *
 */
@Table(name = "qa_participants")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaParticipants implements Serializable {

    private static final long serialVersionUID = 1598412643401L;

    @Id
    @ApiModelProperty(name = "id", value = "主键")
    private String id;

    @Column(name = "user_id")
    @ApiModelProperty(name = "userId", value = "用户id")
    private String userId;

    @ApiModelProperty(name = "category", value = "领域")
    private String category;

    @Column(name = "last_category")
    @ApiModelProperty(name = "lastCategory", value = "最后申请领域")
    private String lastCategory;

    @ApiModelProperty(name = "name", value = "姓名")
    private String name;

    @Column(name = "id_card")
    @ApiModelProperty(name = "idCard", value = "身份证号")
    private String idCard;

    @Column(name = "current_title")
    @ApiModelProperty(name = "currentTitle", value = "现职称")
    private String currentTitle;

    @Column(name = "participating_title")
    @ApiModelProperty(name = "participatingTitle", value = "参评职称")
    private String participatingTitle;

    @Column(name = "post_type")
    @ApiModelProperty(name = "postType", value = "参评岗位")
    private String postType;

    @Column(name = "post_result")
    @ApiModelProperty(name = "postResult", value = "参评结果")
    private String postResult;

    private String finallyPost;

    private String status;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "create_time")
    @ApiModelProperty(name = "createTime", value = "创建时间")
    private Date createTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "update_time")
    @ApiModelProperty(name = "updateTime", value = "修改时间")
    private Date updateTime;

}
