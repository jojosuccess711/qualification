package com.bdjbd.service.sys;

import com.bdjbd.dao.entity.QaReviewExpertEntity;
import com.github.pagehelper.PageInfo;

import java.lang.reflect.InvocationTargetException;

/**
 * 专家管理
 *
 * @author changpeng
 * @email 837464559@gmail.com
 * @date 2020-08-13 09:53:40
 */
public interface QaReviewExpertService {


    PageInfo<QaReviewExpertEntity> findExpertList(Integer pageNum, Integer pageSize, QaReviewExpertEntity entity);

    int addExpert(QaReviewExpertEntity entity);

    QaReviewExpertEntity findById(Integer id);

    int updateExpert(QaReviewExpertEntity entity);

    int deleteExpert(Integer id);
}

