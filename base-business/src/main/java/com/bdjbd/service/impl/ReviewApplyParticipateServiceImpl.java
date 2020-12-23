package com.bdjbd.service.impl;

import com.bdjbd.bo.TrainingExperience;
import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationEntity;
import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationTemplateEntity;
import com.bdjbd.dao.entity.QaStandardRecord;
import com.bdjbd.dao.entity.QaStandardRecordItem;
import com.bdjbd.dao.mapper.QaReviewProfessionalEvaluationMapper;
import com.bdjbd.dao.mapper.QaStandardRecordItemMapper;
import com.bdjbd.dao.mapper.ReviewApplyParticipateMapper;
import com.bdjbd.service.QaReviewProfessionalEvaluationService;
import com.bdjbd.service.QaReviewProfessionalEvaluationTemplateService;
import com.bdjbd.service.QaStandardRecordService;
import com.bdjbd.service.ReviewApplyParticipateService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ReviewApplyParticipateServiceImpl extends BaseServiceImpl<QaStandardRecord, String> implements ReviewApplyParticipateService {

    @Autowired
    private QaStandardRecordService qaStandardRecordService;
    @Autowired
    private ReviewApplyParticipateMapper reviewApplyParticipateMapper;
    @Autowired
    private QaReviewProfessionalEvaluationTemplateService qaReviewProfessionalEvaluationTemplateService;
    @Autowired
    private QaReviewProfessionalEvaluationService qaReviewProfessionalEvaluationService;
    @Autowired
    private QaReviewProfessionalEvaluationMapper qaReviewProfessionalEvaluationMapper;
    @Autowired
    private QaStandardRecordItemMapper itemMapper;

    /**
     * 进入申请参评
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> applyGoInto(String userId, String categoryNameType, String intfaceType) {
        Map<String, Object> baseUserInfo = getBaseUserInfo(userId);
        String whereTime;
        /**
         * 晋1续2
         * 晋升：使用 现技职类别时间 以后数据
         * 续任：使用 最近一次续任时间 以后数据
         */
        if ("1".equals(categoryNameType)) {
            //现技职类别时间
            whereTime = baseUserInfo.get("technology_category_time").toString();
        } else {
            //最近一次续任通过时间
            whereTime = baseUserInfo.get("last_technology_category_time").toString();
        }
        return intfaceType(userId, categoryNameType, intfaceType, whereTime);
    }

    @Override
    public Map<String, Object> preview(String userId, String categoryNameType, String intfaceType) {
        QaReviewProfessionalEvaluationEntity info = qaReviewProfessionalEvaluationMapper.findByUserId(userId);
        if (info == null) {
            Map<String, Object> ret = new HashMap<>();
            ret.put("msg", "未保存 无法预览~~");
            return ret;
        }
        Map<String, Object> baseUserInfo = getBaseUserInfo(userId);
//        baseUserInfo.put("id",info.getId());
        Example e = new Example(QaStandardRecordItem.class);
        Example.Criteria criteria = e.createCriteria();
        criteria.andEqualTo("recordId", (String) baseUserInfo.get("id")).andEqualTo("parameterId", "10100-005");

        List<QaStandardRecordItem> list22 = itemMapper.selectByExample(e);
        for (QaStandardRecordItem qaStandardRecordItem : list22) {
            baseUserInfo.put("birth", qaStandardRecordItem.getParameterValue());

        }
        String whereTime;
        /**
         * 晋1续2
         * 晋升：使用 现技职类别时间 以后数据
         * 续任：使用 最近一次续任时间 以后数据
         */
        if ("1".equals(categoryNameType)) {
            //现技职类别时间
            whereTime = baseUserInfo.get("technology_category_time").toString();
        } else {
            //最近一次续任通过时间
            whereTime = baseUserInfo.get("last_technology_category_time").toString();
        }


        List<String> teachingRewards = new ArrayList<>();
        List<String> guideStudents = new ArrayList<>();
        List<String> teachingAchievements = new ArrayList<>();
        List<String> monographs = new ArrayList<>();
        List<String> academicPapers = new ArrayList<>();
        List<String> completionTasks = new ArrayList<>();
        guideStudents.add(info.getGuideStudents1());
        guideStudents.add(info.getGuideStudents2());
        guideStudents.add(info.getGuideStudents3());
        guideStudents.add(info.getGuideStudents4());
        guideStudents.add(info.getGuideStudents5());

        teachingRewards.add(info.getTeachingReward());
        teachingRewards.add(info.getTeachingReward2());


        teachingAchievements.add(info.getTeachingAchievements1());
        teachingAchievements.add(info.getTeachingAchievements2());
        teachingAchievements.add(info.getTeachingAchievements3());

        monographs.add(info.getMonographs1());
        monographs.add(info.getMonographs2());

        academicPapers.add(info.getAcademicPapers1());
        academicPapers.add(info.getAcademicPapers2());

        completionTasks.add(info.getCompletionTasks1());
        completionTasks.add(info.getCompletionTasks2());
        completionTasks.add(info.getCompletionTasks3());

        info.setAcademicPapers(academicPapers);
        info.setGuideStudents(guideStudents);
        info.setTeachingAchievements(teachingAchievements);
        info.setCompletionTasks(completionTasks);
        info.setMonographs(monographs);
        info.setTeachingRewards(teachingRewards);
        Map<String, Object> stringObjectMap = trainingExperience(userId, categoryNameType, whereTime, info);
        Map<String, Object> map = textbookHandout(userId, categoryNameType, whereTime, info);

        baseUserInfo.put("titleType", info.getTitleType());
        baseUserInfo.put("anotherName", info.getAnotherName());
        info.setExperienceMap(stringObjectMap);
        info.setTextbookHandoutMap(map);
        Map<String, Object> result = new HashMap<>();
        result.put("baseUserInfo", baseUserInfo);
        result.put("info", info);
        return result;
    }

    @Override
    public String get(String userId) {

        Example example = new Example(QaReviewProfessionalEvaluationEntity.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("userId", userId);


        List<QaReviewProfessionalEvaluationEntity> qaReviewProfessionalEvaluationEntities = qaReviewProfessionalEvaluationMapper.selectByExample(example);

        String str = qaReviewProfessionalEvaluationEntities.get(0).getTitleType();
        if (str.startsWith("晋"))
            str = "1";
        else str = "2";

        return str;
    }

    /**
     * 已经录入的数据
     *
     * @param userId
     * @return
     */
    public QaReviewProfessionalEvaluationEntity findByUserId(String userId) {
        return qaReviewProfessionalEvaluationService.findByUserId(userId);
    }

    /**
     * 根据类型识别接口调用
     *
     * @param userId
     * @param categoryNameType
     * @param intfaceType
     * @param whereTime
     * @return
     */
    public Map<String, Object> intfaceType(String userId, String categoryNameType, String intfaceType, String whereTime) {
        Map<String, Object> result = new HashedMap<>();
        QaReviewProfessionalEvaluationEntity byUserId = findByUserId(userId);
        if ("baseUserInfo".equals(intfaceType)) {
            //用户基本信息
            Map<String, Object> map = getBaseUserInfo(userId);

            Example e = new Example(QaStandardRecordItem.class);
            Example.Criteria criteria = e.createCriteria();
            criteria.andEqualTo("recordId", (String) map.get("id")).andEqualTo("parameterId", "10100-005");

            List<QaStandardRecordItem> list22 = itemMapper.selectByExample(e);
            for (QaStandardRecordItem qaStandardRecordItem : list22) {
                map.put("birth", qaStandardRecordItem.getParameterValue());

            }
            if (byUserId != null){
                map.put("id", byUserId.getId());
                List<QaReviewProfessionalEvaluationEntity> list = qaReviewProfessionalEvaluationMapper.selectAll();
                if (!CollectionUtils.isEmpty(list)) {
                    for (QaReviewProfessionalEvaluationEntity entity : list) {
                        if (entity.getUserId().equals(userId)) {
                            map.put("titleType", entity.getTitleType());
                        }
                    }
                }
            }
            result.put("baseUserInfo", map);
        } else if ("trainingExperience".equals(intfaceType)) {
            //培训经历
            result.put("trainingExperience", trainingExperience(userId, categoryNameType, whereTime, byUserId));
            trainingExperience(userId, categoryNameType, whereTime, byUserId);
        } else if ("teachingSituation".equals(intfaceType)) {
            //授课情况
            result.put("teachingSituation", teachingSituation(userId, categoryNameType, whereTime, byUserId));
        } else if ("teachingQuality".equals(intfaceType)) {
            //教学质量
            result.put("teachingQuality", teachingQuality(userId, categoryNameType, whereTime, byUserId));
        } else if ("teachingReward".equals(intfaceType)) {
            //教学奖励
            result.put("teachingReward2", teachingAchievements1(userId, categoryNameType, whereTime, byUserId));
            result.put("teachingReward", teachingReward(userId, categoryNameType, whereTime, byUserId));
        } else if ("guideStudents".equals(intfaceType)) {
            //指导学生1
            result.put("guideStudents1", guideStudents1(userId, categoryNameType, whereTime, byUserId));
            //指导学生2
            result.put("guideStudents2", guideStudents2(userId, categoryNameType, whereTime, byUserId));
            //指导学生3
            result.put("guideStudents3", guideStudents3(userId, categoryNameType, whereTime, byUserId));
            //指导学生4
            result.put("guideStudents4", guideStudents4(userId, categoryNameType, whereTime, byUserId));
            //指导学生5
            result.put("guideStudents5", guideStudents5(userId, categoryNameType, whereTime, byUserId));
            //教材讲义
        } else if ("textbookHandout".equals(intfaceType)) {
            result.put("textbookHandout", textbookHandout(userId, categoryNameType, whereTime, byUserId));
        } else if ("teachingAchievements".equals(intfaceType)) {
//            //教学科技成果1
//            result.put("teachingAchievements1", teachingAchievements1(userId, categoryNameType, whereTime, byUserId));
            //教学科技成果2
            result.put("teachingAchievements2", teachingAchievements2(userId, categoryNameType, whereTime, byUserId));
            //教学科技成果3
            result.put("teachingAchievements3", teachingAchievements3(userId, categoryNameType, whereTime, byUserId));
        } else if ("scientificProjects".equals(intfaceType)) {
            //参与科研项目情况
            result.put("scientificProjects", scientificProjects(userId, categoryNameType, whereTime, byUserId));
        } else if ("monographs".equals(intfaceType)) {
            //专著情况 教材信息
            result.put("monographs1", monographs1(userId, categoryNameType, whereTime, byUserId));
            //专著情况 著作信息
            result.put("monographs2", monographs2(userId, categoryNameType, whereTime, byUserId));
        } else if ("academicPapers".equals(intfaceType)) {
            //学术论文 学术论文信息
            result.put("academicPapers1", academicPapers1(userId, categoryNameType, whereTime, byUserId));
            //学术论文 业务建议、保障方案、咨询报告、工作建议等工作信息
            result.put("academicPapers2", academicPapers2(userId, categoryNameType, whereTime, byUserId));
        } else if ("talentEngineering".equals(intfaceType)) {
            result.put("talentEngineering", talentEngineering(userId, categoryNameType, whereTime, byUserId));
        } else if ("rewardPunishment".equals(intfaceType)) {
            //奖惩情况
            result.put("rewardPunishment", rewardPunishment(byUserId));
        } else if ("completionTasks".equals(intfaceType)) {
            //完成任务情况>>参加军以上重大军事行动或部队活动信息
            result.put("completionTasks1", completionTasks1(userId, categoryNameType, whereTime, byUserId));
            //完成任务情况>>完成政策法规、制度办法、重大技术等工作信息
            result.put("completionTasks2", completionTasks2(userId, categoryNameType, whereTime, byUserId));
            //完成任务情况>>业务建议、保障方案、咨询报告、工作建议等工作信息
            result.put("completionTasks3", completionTasks3(userId, categoryNameType, whereTime, byUserId));
        } else if ("otherWorkDescription".equals(intfaceType)) {
            //完成其他工作简述
            result.put("otherWorkDescription", otherWorkDescription(byUserId));
        }
        return result;
    }


    /**
     * 获取专业技术职务评审一览表(模板)信息
     *
     * @return
     */
    public QaReviewProfessionalEvaluationTemplateEntity getTemplateData() {
        return qaReviewProfessionalEvaluationTemplateService.getTemplateData();
    }

    /**
     * 查询用户基本信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> getBaseUserInfo(String userId) {
        return qaStandardRecordService.getBaseUserInfo(userId);
    }

    /**
     * 培训经历
     *
     * @param userId
     * @return
     */
    public Map<String, Object> trainingExperience(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> trainingExperience = reviewApplyParticipateMapper.trainingExperience(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(trainingExperience)) {
            result.put("listReview", trainingExperience);
            result.put("listReviewWord", getTemplateData().getTrainingExperience());
            result.put("listReviewCallback", byUserId != null ? byUserId.getTrainingExperience() : null);
//            result.put("listReviewWord", "");
            return result;
        }
        return null;
    }

    /**
     * 授课情况
     *
     * @param userId
     * @return
     */
    public Map<String, Object> teachingSituation(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> teachingSituation = reviewApplyParticipateMapper.teachingSituation(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(teachingSituation)) {
//            //总数
//            int subCount = teachingSituation.size();
//            //本科总数
//            int undergraduateCount = 0;
//            //总净课时量
//            double classSum = 0;
//            for (Map<String, Object> hashMap : teachingSituation) {
//                for (Map.Entry<String, Object> m : hashMap.entrySet()) {
//                    System.out.println(m.getKey()+"------key--------"+m.getValue());
//                    if(m.getKey().equals("学院层次") && m.getValue().equals("本科")){
//                        undergraduateCount++;
//                    }
//                    if(m.getKey().equals("净课时量")){
//                        classSum = classSum + Double.parseDouble(m.getValue().toString());
//                    }
//                }
//            }
//            //平均净课时量
//            double avg = classSum / subCount;
//            //按照顺序，依次填入统计的值，key1开始
//            Map<String,Object> value = new HashMap<>();
//            value.put("key1",subCount);
//            value.put("key2",undergraduateCount);
//            value.put("key3",avg);
//            //表格中黑体文字组装值
//            String resultStr = stringJoin(getTemplateData().getTeachingSituation(), value);
            result.put("listReview", teachingSituation);
            result.put("listReviewWord", getTemplateData().getTeachingSituation());
            result.put("listReviewCallback", byUserId != null ? byUserId.getTeachingSituation() : null);
            return result;
        }
        return null;
    }

    /**
     * 教学质量
     *
     * @param userId
     * @return
     */
    public Map<String, Object> teachingQuality(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> teachingQuality = reviewApplyParticipateMapper.teachingQuality(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(teachingQuality)) {
            result.put("listReview", teachingQuality);
            result.put("listReviewWord", getTemplateData().getTeachingQuality());
            result.put("listReviewCallback", byUserId != null ? byUserId.getTeachingQuality() : null);
            return result;
        }
        return null;
    }

    /**
     * 教学奖励
     *
     * @param userId
     * @return
     */
    public Map<String, Object> teachingReward(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> teachingReward = reviewApplyParticipateMapper.teachingReward(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(teachingReward)) {
            result.put("listReview", teachingReward);
            result.put("listReviewWord", getTemplateData().getTeachingReward());
            result.put("listReviewCallback", byUserId != null ? byUserId.getTeachingReward() : null);
            return result;
        }
        return null;
    }

    /**
     * 指导学生1    指导的青年教员获得校级以上教学比赛奖励
     *
     * @param userId
     * @return
     */
    public Map<String, Object> guideStudents1(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> guideStudents1 = reviewApplyParticipateMapper.guideStudents1(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(guideStudents1)) {
            result.put("listReview", guideStudents1);
            result.put("listReviewWord", getTemplateData().getGuideStudents1());
            result.put("listReviewCallback", byUserId != null ? byUserId.getGuideStudents1() : null);
            return result;
        }
        return null;
    }

    /**
     * 指导学生2    指导学员获得优秀毕业设计情况  优秀级别等于：校级优秀
     *
     * @param userId
     * @return
     */
    public Map<String, Object> guideStudents2(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> guideStudents2 = reviewApplyParticipateMapper.guideStudents2(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(guideStudents2)) {
            result.put("listReview", guideStudents2);
            result.put("listReviewWord", getTemplateData().getGuideStudents2());
            result.put("listReviewCallback", byUserId != null ? byUserId.getGuideStudents2() : null);
            return result;
        }
        return null;
    }

    /**
     * 指导学生3    担任本科全程导师或指导本科优异生或本科学员毕业设计  优秀级别等于：院级优秀
     *
     * @param userId
     * @return
     */
    public Map<String, Object> guideStudents3(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> guideStudents3 = reviewApplyParticipateMapper.guideStudents3(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(guideStudents3)) {
            result.put("listReview", guideStudents3);
            result.put("listReviewWord", getTemplateData().getGuideStudents3());
            result.put("listReviewCallback", byUserId != null ? byUserId.getGuideStudents3() : null);
            return result;
        }
        return null;
    }

    /**
     * 指导学生4    指导研究生情况
     *
     * @param userId
     * @return
     */
    public Map<String, Object> guideStudents4(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> guideStudents4 = reviewApplyParticipateMapper.guideStudents4(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(guideStudents4)) {
            result.put("listReview", guideStudents4);
            result.put("listReviewWord", getTemplateData().getGuideStudents4());
            result.put("listReviewCallback", byUserId != null ? byUserId.getGuideStudents4() : null);
            return result;
        }
        return null;
    }

    /**
     * 指导学生5    指导博士后情况
     *
     * @param userId
     * @return
     */
    public Map<String, Object> guideStudents5(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> guideStudents5 = reviewApplyParticipateMapper.guideStudents5(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(guideStudents5)) {
            result.put("listReview", guideStudents5);
            result.put("listReviewWord", getTemplateData().getGuideStudents5());
            result.put("listReviewCallback", byUserId != null ? byUserId.getGuideStudents5() : null);
            return result;
        }
        return null;
    }


    /**
     * 教材讲义
     *
     * @param userId
     * @return
     */
    public Map<String, Object> textbookHandout(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> trainingExperience = reviewApplyParticipateMapper.textbookHandout(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(trainingExperience)) {
            result.put("listReview", trainingExperience);
//            result.put("listReviewWord", "");
            result.put("listReviewWord", getTemplateData().getTextbookHandout());
            result.put("listReviewCallback", byUserId != null ? byUserId.getTextbookHandout() : null);
            return result;
        }
        return null;
    }

    /**
     * 教学科技成果  教学奖励信息>奖励类别：教学成果奖
     *
     * @param userId
     * @return
     */
    public Map<String, Object> teachingAchievements1(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> teachingAchievements1 = reviewApplyParticipateMapper.teachingAchievements1(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(teachingAchievements1)) {
            result.put("listReview", teachingAchievements1);
            result.put("listReviewWord", getTemplateData().getTeachingReward2());
            result.put("listReviewCallback", byUserId != null ? byUserId.getTeachingReward2() : null);
            return result;
        }
        return null;
    }

    /**
     * 教学科技成果   科技奖励信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> teachingAchievements2(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> teachingAchievements2 = reviewApplyParticipateMapper.teachingAchievements2(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(teachingAchievements2)) {
            result.put("listReview", teachingAchievements2);
            result.put("listReviewWord", getTemplateData().getTeachingAchievements2());
            result.put("listReviewCallback", byUserId != null ? byUserId.getTeachingAchievements2() : null);
            return result;
        }
        return null;
    }

    /**
     * 教学科技成果   专利信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> teachingAchievements3(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> teachingAchievements3 = reviewApplyParticipateMapper.teachingAchievements3(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(teachingAchievements3)) {
            result.put("listReview", teachingAchievements3);
            result.put("listReviewWord", getTemplateData().getTeachingAchievements3());
            result.put("listReviewCallback", byUserId != null ? byUserId.getTeachingAchievements3() : null);
            return result;
        }
        return null;
    }

    /**
     * 参与科研 项目情况
     *
     * @param userId
     * @return
     */
    public Map<String, Object> scientificProjects(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        List<Map<String, Object>> scientificProjects = reviewApplyParticipateMapper.scientificProjects(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(scientificProjects)) {
            Map<String, Object> result = new HashMap<>(16);
            result.put("listReview", scientificProjects);
            result.put("listReviewWord", getTemplateData().getScientificProjects());
            result.put("listReviewCallback", byUserId != null ? byUserId.getScientificProjects() : null);
            return result;
        }
        return null;
    }

    /**
     * 专著情况 教材信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> monographs1(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> monographs1 = reviewApplyParticipateMapper.monographs1(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(monographs1)) {
            result.put("listReview", monographs1);
            result.put("listReviewWord", getTemplateData().getMonographs1());
            result.put("listReviewCallback", byUserId != null ? byUserId.getMonographs1() : null);
            return result;
        }
        return null;
    }

    /**
     * 专著情况 著作信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> monographs2(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> monographs2 = reviewApplyParticipateMapper.monographs2(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(monographs2)) {
            result.put("listReview", monographs2);
            result.put("listReviewWord", getTemplateData().getMonographs2());
            result.put("listReviewCallback", byUserId != null ? byUserId.getMonographs2() : null);
            return result;
        }
        return null;
    }

    /**
     * 学术论文 学术论文信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> academicPapers1(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> academicPapers1 = reviewApplyParticipateMapper.academicPapers1(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(academicPapers1)) {
            result.put("listReview", academicPapers1);
            result.put("listReviewWord", getTemplateData().getAcademicPapers1());
            result.put("listReviewCallback", byUserId != null ? byUserId.getAcademicPapers1() : null);
            return result;
        }
        return null;
    }

    /**
     * 学术论文 业务建议、保障方案、咨询报告、工作建议等工作信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> academicPapers2(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> academicPapers2 = reviewApplyParticipateMapper.academicPapers2(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(academicPapers2)) {
            result.put("listReview", academicPapers2);
            result.put("listReviewWord", getTemplateData().getAcademicPapers2());
            result.put("listReviewCallback", byUserId != null ? byUserId.getAcademicPapers2() : null);
            return result;
        }
        return null;
    }

    /**
     * 获人才工程和计划情况
     *
     * @return
     */
    public Map<String, Object> talentEngineering(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> talentEngineering = reviewApplyParticipateMapper.talentEngineering(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(talentEngineering)) {
            result.put("listReview", talentEngineering);
            result.put("listReviewWord", getTemplateData().getTalentEngineering());
            result.put("listReviewCallback", byUserId != null ? byUserId.getTalentEngineering() : null);
            return result;
        }
        return null;
    }

    /**
     * 奖惩情况
     *
     * @return
     */
    public Map<String, Object> rewardPunishment(QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        result.put("listReview", getTemplateData().getRewardPunishment());
        result.put("listReviewCallback", byUserId != null ? byUserId.getRewardPunishment() : null);
        return result;
    }

    /**
     * 完成任务情况>>参加军以上重大军事行动或部队活动信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> completionTasks1(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> completionTasks1 = reviewApplyParticipateMapper.completionTasks1(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(completionTasks1)) {
            result.put("listReview", completionTasks1);
            result.put("listReviewWord", getTemplateData().getCompletionTasks1());
            result.put("listReviewCallback", byUserId != null ? byUserId.getCompletionTasks1() : null);
            return result;
        }
        return null;
    }

    /**
     * 完成任务情况>>完成政策法规、制度办法、重大技术等工作信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> completionTasks2(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> completionTasks2 = reviewApplyParticipateMapper.completionTasks2(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(completionTasks2)) {
            result.put("listReview", completionTasks2);
            result.put("listReviewWord", getTemplateData().getCompletionTasks2());
            result.put("listReviewCallback", byUserId != null ? byUserId.getCompletionTasks2() : null);
            return result;
        }
        return null;
    }

    /**
     * 完成任务情况>>业务建议、保障方案、咨询报告、工作建议等工作信息
     *
     * @param userId
     * @return
     */
    public Map<String, Object> completionTasks3(String userId, String categoryNameType, String whereTime, QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        List<Map<String, Object>> completionTasks3 = reviewApplyParticipateMapper.completionTasks3(userId, categoryNameType, whereTime);
        if (CollectionUtils.isNotEmpty(completionTasks3)) {
            result.put("listReview", completionTasks3);
            result.put("listReviewWord", getTemplateData().getCompletionTasks3());
            result.put("listReviewCallback", byUserId != null ? byUserId.getCompletionTasks3() : null);

            return result;
        }
        return null;
    }

    /**
     * 完成其他工作简述
     *
     * @return
     */
    public Map<String, Object> otherWorkDescription(QaReviewProfessionalEvaluationEntity byUserId) {
        Map<String, Object> result = new HashMap<>(16);
        result.put("listReviewWord", getTemplateData().getOtherWorkDescription());
        result.put("listReviewCallback", byUserId != null ? byUserId.getOtherWorkDescription() : null);
        return result;
    }

    /**
     * 例如：系统讲授过_门课程（含MOOC课程）,年均为本科学员讲授_门课程,年均教学净课时量_学时
     * _替换为值并且拼接完整
     *
     * @param longStr
     * @param value
     * @return
     */
    public static String stringJoin(String longStr, Map<String, Object> value) {
        String shortStr = "_";
//        longStr = "系统讲授过_门课程（含MOOC课程）,年均为本科学员讲授_门课程,年均教学净课时量_学时";
        if (StringUtils.isNotEmpty(longStr)) {
            String[] strArr = longStr.split(shortStr);
            String resultStr = "";
            for (int i = 0; i < strArr.length; i++) {
                if ((i + 1) == strArr.length) {
                    resultStr = resultStr + strArr[i];
                } else {
                    resultStr = resultStr + strArr[i] + value.get("key" + (i + 1));
                }
            }
            return resultStr;
        }
        return "";
    }
}
