/**
 * @filename:QaStandardRecordCommitted 2020年4月9日
 * @project 职称评审系统  V1.0
 * Copyright(c) 2018 songzekun Co. Ltd. 
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
 * @Description:  已提交的用户信息
 * @Author:       songzekun   
 * @CreateDate:   2020年4月9日
 * @Version:      V1.0
 *    
 */
@Table(name = "qa_standard_record_committed")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QaStandardRecordCommitted implements Serializable {

	private static final long serialVersionUID = 1587878460722L;
	
    @Id
	@ApiModelProperty(name = "id", value = "")
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

    @Column(name = "user_id")
	@ApiModelProperty(name = "userId", value = "用户id")
	private String userId;

	@ApiModelProperty(name = "mobile", value = "手机号码")
	private String mobile;

	@ApiModelProperty(name = "name", value = "姓名")
	private String name;

	@ApiModelProperty(name = "sex", value = "性别 0：女  1：男")
	private Long sex;

    @Column(name = "id_card")
	@ApiModelProperty(name = "idCard", value = "身份证号")
	private String idCard;

    @Column(name = "personnel_category")
	@ApiModelProperty(name = "personnelCategory", value = "人员类别军人干部文职人员")
	private String personnelCategory;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
	@ApiModelProperty(name = "birth", value = "出生日期")
	private Date birth;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "enlistment_time")
	@ApiModelProperty(name = "enlistmentTime", value = "入伍时间（军人干部）")
	private Date enlistmentTime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "enter_employment_time")
	@ApiModelProperty(name = "enterEmploymentTime", value = "参加工作时间（文职人员）")
	private Date enterEmploymentTime;

    @Column(name = "political_affiliation")
	@ApiModelProperty(name = "politicalAffiliation", value = "政治面貌党员")
	private String politicalAffiliation;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "caucus_time")
	@ApiModelProperty(name = "caucusTime", value = "党团时间")
	private Date caucusTime;

    @Column(name = "last_education")
	@ApiModelProperty(name = "lastEducation", value = "最高学历大学硕士研究生博士研究生")
	private String lastEducation;

	@ApiModelProperty(name = "major", value = "专业（最高学历）")
	private String major;

    @Column(name = "highest_degree")
	@ApiModelProperty(name = "highestDegree", value = "最高学位学士硕士博士")
	private String highestDegree;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "highest_degree_time")
	@ApiModelProperty(name = "highestDegreeTime", value = "学位时间")
	private Date highestDegreeTime;

    @Column(name = "last_school_major")
	@ApiModelProperty(name = "lastSchoolMajor", value = "最后毕业学校")
	private String lastSchoolMajor;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "graduate_time")
	@ApiModelProperty(name = "graduateTime", value = "毕业时间")
	private Date graduateTime;

    @Column(name = "highest_school_major")
	@ApiModelProperty(name = "highestSchoolMajor", value = "最高学位授予学校、专业")
	private String highestSchoolMajor;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "highest_get_time")
	@ApiModelProperty(name = "highestGetTime", value = "最高学位授予时间")
	private Date highestGetTime;

    @Column(name = "school_category")
	@ApiModelProperty(name = "schoolCategory", value = "院校类别 211985普通全日制军内院校")
	private String schoolCategory;

    @Column(name = "technology_title")
	@ApiModelProperty(name = "technologyTitle", value = "现任专业技术职务助教讲师副教授教授研究实习员助理研究员副研究员研究员实验员助理实验师实验师高级实验师正高级实验师助理工程师技术员工程师高级工程师正高级工程师会计员助理会计师会计师高级会计师正高级会计师助理编辑编辑副编审编审管理员助理馆员馆员副研究馆员研究馆员")
	private String technologyTitle;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "appoint_time")
	@ApiModelProperty(name = "appointTime", value = "聘任时间（现专业技术职务时间）")
	private Date appointTime;

    @Column(name = "appoint_annex")
	@ApiModelProperty(name = "appointAnnex", value = "聘任相关附件地址")
	private String appointAnnex;

    @Column(name = "technology_level")
	@ApiModelProperty(name = "technologyLevel", value = "现技术等级专业技术一级专业技术二级专业技术三级专业技术四级专业技术五级专业技术六级专业技术七级专业技术八级专业技术九级专业技术十级专业技术十一级专业技术十二级专业技术十三级")
	private String technologyLevel;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "technology_level_time")
	@ApiModelProperty(name = "technologyLevelTime", value = "现技术等级时间")
	private Date technologyLevelTime;

    @Column(name = "military_rank")
	@ApiModelProperty(name = "militaryRank", value = "现军衔/级别大校上将中将少将上校中校少校上尉中尉少尉转改特级转改1级转改2级转改3级转改4级转改5级转改6级转改7级转改8级特级1级2级3级4级……25级")
	private String militaryRank;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "military_rank_time")
	@ApiModelProperty(name = "militaryRankTime", value = "现军衔/级别时间")
	private Date militaryRankTime;

    @Column(name = "technology_category")
	@ApiModelProperty(name = "technologyCategory", value = "现技职类别初职中职副高职正高职")
	private String technologyCategory;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "technology_category_time")
	@ApiModelProperty(name = "technologyCategoryTime", value = "现技职类别时间")
	private Date technologyCategoryTime;

    @Column(name = "official_rank")
	@ApiModelProperty(name = "officialRank", value = "现部级别训练管理系助教")
	private String officialRank;

    @Column(name = "academic_title")
	@ApiModelProperty(name = "academicTitle", value = "申请职称，存储下拉框的选择值")
	private String academicTitle;

    @Column(name = "break_rule")
	@ApiModelProperty(name = "breakRule", value = "是否破格 0：否  1：是")
	private Long breakRule;

    @Column(name = "break_rule_annex")
	@ApiModelProperty(name = "breakRuleAnnex", value = "破格相关附件地址")
	private String breakRuleAnnex;

	@ApiModelProperty(name = "department", value = "工作部门")
	private String department;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "get_qualifications_time")
	@ApiModelProperty(name = "getQualificationsTime", value = "获得资格时间")
	private Date getQualificationsTime;

    @Column(name = "get_qualifications_annex")
	@ApiModelProperty(name = "getQualificationsAnnex", value = "获得资格相关附件地址")
	private String getQualificationsAnnex;

    @Column(name = "tech_num")
	@ApiModelProperty(name = "techNum", value = "编号")
	private String techNum;

	@ApiModelProperty(name = "category", value = "现所属技职领域")
	private String category;

    @Column(name = "category_type")
	@ApiModelProperty(name = "categoryType", value = "现所属技职领域类型")
	private String categoryType;

    @Column(name = "category_title")
	@ApiModelProperty(name = "categoryTitle", value = "现所属技职职称")
	private String categoryTitle;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "category_title_date")
	@ApiModelProperty(name = "categoryTitleDate", value = "现职称获取时间")
	private Date categoryTitleDate;

	@ApiModelProperty(name = "attr0", value = "实际工作单位")
	private String attr0;

	@ApiModelProperty(name = "attr1", value = "现所属技职领域id")
	private String attr1;

	@ApiModelProperty(name = "attr2", value = "现所属技职领域类型id")
	private String attr2;

	@ApiModelProperty(name = "attr3", value = "现所属技职职称id")
	private String attr3;

	@ApiModelProperty(name = "attr4", value = "奖惩情况")
	private String attr4;

	@ApiModelProperty(name = "attr5", value = "照片")
	private String attr5;

	@ApiModelProperty(name = "attr6", value = "")
	private String attr6;

	@ApiModelProperty(name = "attr7", value = "")
	private String attr7;

	@ApiModelProperty(name = "attr8", value = "")
	private String attr8;

	@ApiModelProperty(name = "attr9", value = "")
	private String attr9;

    @Column(name = "commit_status")
	@ApiModelProperty(name = "commitStatus", value = "是否已提交 0 未提交 1已提交")
	private Boolean commitStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "last_technology_category_time")
	@ApiModelProperty(name = "lastTechnologyCategoryTime", value = "最近一次续任通过时间")
	private Date lastTechnologyCategoryTime;

    @Column(name = "user_want_category_title_id")
	@ApiModelProperty(name = "userWantCategoryTitleId", value = "用户想要申请参评的职称id")
	private String userWantCategoryTitleId;

}
