package com.bdjbd.dao.entity;

import com.bdjbd.bo.TrainingExperience;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;
import java.util.Map;


/**
 * 专业技术职务评审一览表(信息记录表)
 *
 * @author changpeng
 * @email 837464559@gmail.com
 * @date 2020-08-13 09:53:40
 */
@Data
@Table(name = "qa_review_professional_evaluation")
public class QaReviewProfessionalEvaluationEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @Id
    private Integer id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 培训经历
     */
    private String trainingExperience;
    /**
     * 授课情况
     */
    private String teachingSituation;
    /**
     * 教学质量
     */
    private String teachingQuality;
    /**
     * 教学奖励
     */
    private String teachingReward;


    private String teachingReward2;


    /**
     * 指导学生1
     */
    private String guideStudents1;
    /**
     * 指导学生2
     */
    private String guideStudents2;
    /**
     * 指导学生3
     */
    private String guideStudents3;
    /**
     * 指导学生4
     */
    private String guideStudents4;
    /**
     * 指导学生5
     */
    private String guideStudents5;
    private String textbookHandout;



    /**
     * 教学科技成果1
     */
    private String teachingAchievements1;
    /**
     * 教学科技成果2
     */
    private String teachingAchievements2;
    /**
     * 教学科技成果3
     */
    private String teachingAchievements3;
    /**
     * 参与科研项目情况
     */
    private String scientificProjects;
    /**
     * 专著情况1
     */
    private String monographs1;
    /**
     * 专著情况2
     */
    private String monographs2;
    /**
     * 学术论文1
     */
    private String academicPapers1;
    /**
     * 学术论文2
     */
    private String academicPapers2;

    private String talentEngineering;

    /**
     * 奖惩情况
     */
    private String rewardPunishment;
    /**
     * 完成任务情况1
     */
    private String completionTasks1;
    /**
     * 完成任务情况2
     */
    private String completionTasks2;
    /**
     * 完成任务情况3
     */
    private String completionTasks3;
    /**
     * 完成其他工作简述
     */
    private String otherWorkDescription;
    /**
     * 岗位类型
     */
    private String postType;
    /**
     * 参评职称1
     */
    private String titleType;
    /**
     * 参评职称2
     */
    private String anotherName;
    /**
     * 提交状态
     */
    private int submitStatus;

    private int education;

    private int kyobo;

    private int research;

    private int politics;

    private Map<String, Object> experienceMap;
    private Map<String, Object> textbookHandoutMap;

    List<String> guideStudents;
    List<String> teachingAchievements;
    List<String> monographs;
    List<String> academicPapers;
    List<String> completionTasks;
    List<String> teachingRewards;


}
