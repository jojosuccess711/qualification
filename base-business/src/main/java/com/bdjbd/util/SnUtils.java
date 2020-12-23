package com.bdjbd.util;

import com.bdjbd.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.concurrent.TimeUnit;

/**
 * @author dbc
 * @version 1.0
 * @className SnUtils
 * @description 编号工具 (依赖Redis计数器)
 * @date 2019/11/14
 **/
@Slf4j
@Component
public class SnUtils {

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    /**
     * 获取每天从1开始计数的Long类型UUID (redis实现方式)
     * <br/> 例： length = 8 <br/>  haveDay = true <br/>运行时间 2019-11-14, 计数为 2004 <br/>运行结果 2019111400002004
     * @param key redis key前缀
     * @param length 生成UUID长度(havaDay=true时加前缀8位，返回结果值长度为 8 + length)
     * @param haveDay 是否包含日期前缀
     * @return
     */
    public Long fetchDailyUUID(String key, Integer length, Boolean haveDay) {
        try {
            String day = DateUtil.formatYMDNoUnderline(null);
            //新的一天，通过新 key 获取值，每天都能从1开始获取
            key = key + "_" + day;
            Long num = redisTemplate.opsForValue().increment(key);
            //设置 key 过期时间
            if (num == 1) {
                redisTemplate.expire(key, DateUtil.getDifftimeSecond(null, DateUtil.getDateMax(null)) + 60, TimeUnit.SECONDS);
            }
            if (haveDay) {
                return createUUID(num, day.substring(2, day.length()), length);
            } else {
                return num;
            }
        }catch (Exception e){
            log.error("生成唯一ID异常", e);
        }
        return 0l;
    }

    /**
     * 获取计数值(redis实现方式)
     * @param key redis key
     * @param length 生成UUID长度(havaDay=true时加前缀8位，返回结果值长度为 8 + length)
     * @param haveDay 是否包含日期前缀
     * @return
     */
    public Long fetchUUID(String key, Integer length, Boolean haveDay) {
        try {
            Long num = redisTemplate.opsForValue().increment(key);
            if (haveDay) {
                return createUUID(num, DateUtil.formatYMDNoUnderline(null), length);
            } else {
                return num;
            }
        } catch (Exception e){
            log.error("生成唯一ID异常", e);
        }
        return 0l;
    }

    /**
     * 格式化并返回Long类型值
     * @param num 计数值
     * @param day 年月日(格式yyyyMMdd)
     * @param length 计数值长度(补充0)
     * @return Long类型值
     */
    private Long createUUID(Long num, String day, Integer length) {
        String id = String.valueOf(num);
        if (id.length() < length) {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            nf.setMaximumIntegerDigits(length);
            nf.setMinimumIntegerDigits(length);
            id = nf.format(num);
        }
        return Long.parseLong(day + id);
    }
}
