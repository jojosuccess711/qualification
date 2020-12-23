package com.bdjbd.service;

import com.bdjbd.dao.entity.BaseParameterGroup;

import java.util.List;

/**
 * @Description:
 * @Author zekunsong
 * @CreateDate 2020/4/20
 * @Version V1.0
 */
public interface BCacheService {

    List<BaseParameterGroup> findAllGroups();

}
