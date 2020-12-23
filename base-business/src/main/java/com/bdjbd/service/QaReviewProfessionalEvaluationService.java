package com.bdjbd.service;

import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationEntity;

import java.util.List;
import java.util.Map;

/**
 * 专业技术职务评审一览表(信息记录表)
 *
 * @author changpeng
 * @email 837464559@gmail.com
 * @date 2020-08-13 09:53:40
 */
public interface QaReviewProfessionalEvaluationService {

    int saveProfessionalEvaluation(String userId, QaReviewProfessionalEvaluationEntity entity);

    QaReviewProfessionalEvaluationEntity findByUserId(String userId);

    int submitProfessionalEvaluation(String userId, QaReviewProfessionalEvaluationEntity entity);

    List<Map<String, Object>> firstParticipateManagement(String anotherName, String category);

    Integer getPersonStatus(String userId);

    int unSubmit(String userId);

    int adminSubmit(String userId,Integer id);

    QaReviewProfessionalEvaluationEntity adminFind(String userId, Integer id);
}

