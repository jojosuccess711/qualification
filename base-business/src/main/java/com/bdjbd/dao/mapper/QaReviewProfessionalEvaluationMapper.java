package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationEntity;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 专业技术职务评审一览表(信息记录表)
 * 
 * @author changpeng
 * @email 837464559@gmail.com
 * @date 2020-08-13 09:53:40
 */
@Mapper
public interface QaReviewProfessionalEvaluationMapper extends BaseDao<QaReviewProfessionalEvaluationEntity> {
    /**
     * 查询用户是否保存了记录
     * @param userId
     * @return
     */
    QaReviewProfessionalEvaluationEntity findByUserId(@Param("userId") String userId);

    /**
     * 更新/提交
     * @param entity
     * @return
     */
    int updateByUserId(QaReviewProfessionalEvaluationEntity entity);

    /**
     * 保存
     * @param entity
     * @return
     */
    int saveProfessionalEvaluation(QaReviewProfessionalEvaluationEntity entity);
    /**
     * 首次参评人员管理
     * @param anotherName  参评职称
     * @param category    五大类型
     * @return
     */
    List<Map<String, Object>> firstParticipateManagement(@Param("anotherName")String anotherName, @Param("category") String category);
}
