package com.bdjbd.service.impl;

import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationTemplateEntity;
import com.bdjbd.dao.mapper.QaReviewProfessionalEvaluationTemplateMapper;
import com.bdjbd.service.QaReviewProfessionalEvaluationTemplateService;
import com.bdjbd.web.service.impl.BaseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("qaReviewProfessionalEvaluationTemplateService")
public class QaReviewProfessionalEvaluationTemplateServiceImpl extends BaseServiceImpl<QaReviewProfessionalEvaluationTemplateEntity, String> implements QaReviewProfessionalEvaluationTemplateService {

    @Autowired
    private QaReviewProfessionalEvaluationTemplateMapper qaReviewProfessionalEvaluationTemplateMapper;
    /**
     * 获取专业技术职务评审一览表(模板)信息
     * @return
     */
    @Override
    public QaReviewProfessionalEvaluationTemplateEntity getTemplateData() {
        return qaReviewProfessionalEvaluationTemplateMapper.getTemplateData();
    }
}