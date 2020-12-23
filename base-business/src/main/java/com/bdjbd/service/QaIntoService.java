package com.bdjbd.service;

import com.bdjbd.bo.ScheduleVO;
import com.bdjbd.bo.TimeResVO;
import com.bdjbd.bo.TimeVO;
import com.bdjbd.dao.entity.QaExpertCommit;
import com.bdjbd.dao.entity.QaIntoParticipants;
import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationEntity;
import com.bdjbd.dao.entity.SysConfigAttributes;

import java.util.List;
import java.util.Map;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 9:40 AM 2020/9/8
 */
public interface QaIntoService {
    Map<String, Object> findList(String userId, String rounds, String typeStatus, String lastCategory,String type);

    int vote(String id, String userId);

    int unVote(String id, String userId);

    int commitVote(String userId, String rounds, String typeStatus, String lastCategory, String pic,String type);

    List<QaIntoParticipants> findResult(String userId, String rounds, String typeStatus, String lastCategory, String type);

    List<ScheduleVO> findSchedule(String userId, String rounds, String typeStatus);

    void intoAgain(String userId, String rounds, String userIds, String typeStatus);

    List<QaIntoParticipants> exportInfo(String userId, String userIds);

    void repairData(String userId);

    Map<String, TimeVO> timeData();

    QaReviewProfessionalEvaluationEntity findDataByUserId(String userId);

    void updateDataByUserId(QaReviewProfessionalEvaluationEntity entity);

    QaExpertCommit findImg(String userId,String rounds,String type,String typeStatus);
}
