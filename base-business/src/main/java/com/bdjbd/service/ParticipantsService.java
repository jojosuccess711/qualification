package com.bdjbd.service;

import com.bdjbd.dao.entity.QaParticipants;
import com.github.pagehelper.PageInfo;

/**
 * @Author: mnie
 * @Description:
 * @Date: Create in 4:44 PM 2020/8/26
 */
public interface ParticipantsService {

    PageInfo<QaParticipants> findList(String categoryName, String category, String type, Integer pageNum, Integer pageSize);

    int move(String id);

    int participation(String id, String status,String type);

    String findTime(String categoryName, String category);
}
