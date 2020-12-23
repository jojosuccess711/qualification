package com.bdjbd.util;

import com.bdjbd.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
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
public class SnRandomUtils {

    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    public static final String DAILY_KEY = "DAILY_KEY";
    public static final String INIT_KEY = "INIT_KEY";
    public static final Long ADD_COUNT = 10000L;

    private Long getKey(){
        Boolean bool = redisTemplate.hasKey(DAILY_KEY);

        if(!bool){
            List<Long> list = new ArrayList<>();
            for(Long i = 0L; i < 10000L; i ++){
                list.add(i);
            }
            Collections.shuffle(list);
            redisTemplate.opsForList().rightPushAll(DAILY_KEY, list);
            redisTemplate.opsForValue().set(INIT_KEY, ADD_COUNT);
            long expire = DateUtil.getDifftimeSecond(null, DateUtil.getDateMax(null)) + 60;
            redisTemplate.expire(DAILY_KEY, expire, TimeUnit.SECONDS);
            redisTemplate.expire(INIT_KEY, expire, TimeUnit.SECONDS);
        }else{
            Long size = redisTemplate.opsForList().size(DAILY_KEY);
            Long init = (Long)redisTemplate.opsForValue().get(INIT_KEY);
            if(size < 500){
                List<Long> list = new ArrayList<>();
                for(Long i = init; i < init + ADD_COUNT; i++){
                    list.add(i);
                }
                Collections.shuffle(list);
                redisTemplate.opsForList().rightPushAll(DAILY_KEY, list);
                redisTemplate.opsForValue().set(INIT_KEY, init + ADD_COUNT);
            }
        }
        Object o1 = redisTemplate.opsForList().leftPop(DAILY_KEY);
        return Long.valueOf(o1.toString());
    }

    public Long randomCode(Integer length){
        try{
            Long num = getKey();
            String day = DateUtil.formatYMDNoUnderline(null);
            return createUUID(num, day.substring(2, day.length()), length);
        }catch (Exception e){
            log.error("生成优惠码异常", e);
        }
        return 0L;
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
