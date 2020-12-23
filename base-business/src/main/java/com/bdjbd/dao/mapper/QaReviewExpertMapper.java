package com.bdjbd.dao.mapper;

import com.bdjbd.dao.entity.QaReviewExpertEntity;
import com.bdjbd.dao.entity.QaReviewProfessionalEvaluationEntity;
import com.bdjbd.web.dao.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 专家管理
 * 
 * @author changpeng
 * @email 837464559@gmail.com
 * @date 2020-08-13 09:53:40
 */
@Mapper
public interface QaReviewExpertMapper extends BaseDao<QaReviewExpertEntity> {

    int addExpert(QaReviewExpertEntity entity);

    int updateExpert(QaReviewExpertEntity entity);

    int deleteExpert(@Param("id") Integer id);
}
