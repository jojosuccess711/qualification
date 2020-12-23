package com.bdjbd.service;

import com.bdjbd.bo.TimeVO;
import com.bdjbd.dao.entity.QaReviewCfg;
import com.bdjbd.dao.entity.SysAdmin;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 5:02 PM 2020/8/27
 */
public interface ReviewCfgService {
    int addCfg(QaReviewCfg cfg);

    List<SysAdmin> findExperts();

    int updateCfg(QaReviewCfg cfg);

    TreeMap<Integer, Object> findCfg(QaReviewCfg cfg);

    List<QaReviewCfg> findExpertTask(String userId);

    int deleteCfg(QaReviewCfg cfg);

    TimeVO findCfgTime(QaReviewCfg cfg);
}
