package com.bdjbd.service.common;

import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author: dbc
 * @Date: 2018/9/7
 * @Version: 1.0
 * @Description:
 */
public interface RedisService {

    /**
     * 给指定键设置值，并设置有效时间
     * @param k 键
     * @param v 值
     * @param time 有效时间 ( 为null不设置有效时间 )
     */
    void setForValue(Object k, Object v, Long time);

    /**
     * 获取指定键的值
     * @param k 键
     * @return
     */
    Object getForValue(Object k);

    /**
     * 删除指定键的数据
     * @param k
     */
    void delete(Object k);

    /**
     * 获取RedisTemplate
     * @return
     */
    RedisTemplate getRedisTemplate();
}
