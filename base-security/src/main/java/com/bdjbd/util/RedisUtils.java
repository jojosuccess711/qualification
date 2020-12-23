package com.bdjbd.util;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author dbc
 * @version 1.0
 * @className RedisUtils
 * @description TODO
 * @date 2019/10/29
 **/
public class RedisUtils {

    public static StringRedisSerializer keySerializer(){
        return new StringRedisSerializer();
    }

    /**
     * Jackson2JsonRedisSerializer 值序列化方式
     * @return
     */
    public static Jackson2JsonRedisSerializer valueSerializer() {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        return jackson2JsonRedisSerializer;
    }

    /**
     * 默认RedisTemplate
     * @return
     */
    public static RedisTemplate<String, Object> defaultRedisTemplate(){
        RedisTemplate template = new RedisTemplate();
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = RedisUtils.valueSerializer();
        template.setValueSerializer(jackson2JsonRedisSerializer);
        template.setHashValueSerializer(jackson2JsonRedisSerializer);

        StringRedisSerializer stringRedisSerializer = RedisUtils.keySerializer();
        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        return template;
    }
}
