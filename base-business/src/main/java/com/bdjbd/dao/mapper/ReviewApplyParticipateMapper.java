package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface ReviewApplyParticipateMapper extends BaseDao<QaStandardRecord> {

    /**
     * 培训经历
     * @param userId
     * @param categoryNameType
     * @return
     */
    List<Map<String,Object>> trainingExperience(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 授课情况
     * @param userId
     * @return
     */
    List<Map<String, Object>> teachingSituation(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 教学质量
     * @param userId
     * @return
     */
    List<Map<String, Object>> teachingQuality(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 教学奖励
     * @param userId
     * @return
     */
    List<Map<String, Object>> teachingReward(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 指导学生1
     * @param userId
     * @return
     */
    List<Map<String, Object>> guideStudents1(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 指导学生2
     * @param userId
     * @return
     */
    List<Map<String, Object>> guideStudents2(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 指导学生3
     * @param userId
     * @return
     */
    List<Map<String, Object>> guideStudents3(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 指导学生4
     * @param userId
     * @return
     */
    List<Map<String, Object>> guideStudents4(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 指导学生5
     * @param userId
     * @return
     */
    List<Map<String, Object>> guideStudents5(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 教学科技成果1
     * @param userId
     * @return
     */
    List<Map<String, Object>> teachingAchievements1(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 教学科技成果2
     * @param userId
     * @return
     */
    List<Map<String, Object>> teachingAchievements2(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 教学科技成果3
     * @param userId
     * @return
     */
    List<Map<String, Object>> teachingAchievements3(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 参与科研 项目情况
     * @param userId
     * @return
     */
    List<Map<String, Object>> scientificProjects(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 专著情况 教材信息
     * @param userId
     * @return
     */
    List<Map<String, Object>> monographs1(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 专著情况 著作信息
     * @param userId
     * @return
     */
    List<Map<String, Object>> monographs2(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 学术论文 学术论文信息
     * @param userId
     * @return
     */
    List<Map<String, Object>> academicPapers1(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 学术论文 业务建议、保障方案、咨询报告、工作建议等工作信息
     * @param userId
     * @return
     */
    List<Map<String, Object>> academicPapers2(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 完成任务情况>>参加军以上重大军事行动或部队活动信息
     * @param userId
     * @return
     */
    List<Map<String, Object>> completionTasks1(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 完成任务情况>>完成政策法规、制度办法、重大技术等工作信息
     * @param userId
     * @return
     */
    List<Map<String, Object>> completionTasks2(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    /**
     * 完成任务情况>>业务建议、保障方案、咨询报告、工作建议等工作信息
     * @param userId
     * @return
     */
    List<Map<String, Object>> completionTasks3(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    List<Map<String, Object>> talentEngineering(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);

    List<Map<String, Object>> textbookHandout(@Param("userId") String userId
            , @Param("categoryNameType") String categoryNameType
            , @Param("whereTime") String whereTime);
}
