package com.bdjbd.service.common.impl;

import com.bdjbd.service.common.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

/**
 * @author dbc
 * @version 1.0
 * @className CacheServiceImpl
 * @description TODO
 * @date 2020/2/21
 **/
@Slf4j
@Service
public class CacheServiceImpl implements CacheService {

    @CacheEvict(cacheNames = {
            "SysConfig",
            "SysAuthority",
            "BaseSimpleDefinition",
            "findAllGroups",
            "findAllParameters",
            "findParametersByGroupId"
    }, allEntries = true)
    @Override
    public void clearAll() {
    }
}
