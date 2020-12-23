package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationTemplateEntity;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * 专业技术职务评审一览表（模板表）
 * 
 * @author changpeng
 * @email 837464559@gmail.com
 * @date 2020-08-13 09:53:40
 */
@Mapper
public interface QaReviewProfessionalEvaluationTemplateMapper extends BaseDao<QaReviewProfessionalEvaluationTemplateEntity> {

    /**
     * 获取专业技术职务评审一览表(模板)信息
     * @return
     */
    QaReviewProfessionalEvaluationTemplateEntity getTemplateData();
}
