package com.bdjbd.service.common.impl;

import com.bdjbd.service.common.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * @Author: dbc
 * @Date: 2018/9/7
 * @Version: 1.0
 * @Description:
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 给指定键设置值，并设置有效时间
     *
     * @param k    键
     * @param v   值
     * @param time 有效时间
     */
    @Override
    public void setForValue(Object k, Object v, Long time) {
        redisTemplate.opsForValue().set(k, v);
        if(time != null)
            redisTemplate.expire(k, time, TimeUnit.SECONDS);
    }

    /**
     * 获取指定键的值
     *
     * @param k 键
     * @return
     */
    @Override
    public Object getForValue(Object k) {
        Object v = redisTemplate.opsForValue().get(k);
        log.debug(String.format("redis --> {key = %s, vlaue = %s}", k, v));
        return v;
    }

    /**
     * 删除指定键的数据
     *
     * @param k
     */
    @Override
    public void delete(Object k) {
        redisTemplate.delete(k);
    }

    /**
     * 获取RedisTemplate
     *
     * @return
     */
    @Override
    public RedisTemplate getRedisTemplate() {
        return this.redisTemplate;
    }
}
