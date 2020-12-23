package com.bdjbd.runner;

import com.bdjbd.common.util.jwt.RsaKeyUtil;
import com.bdjbd.config.KeyConfiguration;
import com.bdjbd.config.UserAuthConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Map;

/**
  * @className AuthClientRunner
  * @description 监听启动完成时触发
  * @author dbc
  * @date 2019/10/11
  * @version 1.0
  **/
@Configuration
@Slf4j
public class AuthClientRunner implements CommandLineRunner {

    @Autowired
    private UserAuthConfig userAuthConfig;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String REDIS_USER_PRI_KEY = "SP:AUTH:JWT:PRI";
    private static final String REDIS_USER_PUB_KEY = "SP:AUTH:JWT:PUB";

    @Autowired
    private KeyConfiguration keyConfiguration;

    @Override
    public void run(String... args) throws Exception {
        refresh();

        log.info("初始化加载用户pubKey");
        try {
            refreshUserPubKey();
        }catch(Exception e){
            log.error("初始化加载用户pubKey失败,1分钟后自动重试!",e);
        }
        log.info("初始化加载客户pubKey");
    }

    public void refresh() throws Exception{
        if (redisTemplate.hasKey(REDIS_USER_PRI_KEY) && redisTemplate.hasKey(REDIS_USER_PUB_KEY)) {
            keyConfiguration.setUserPriKey(RsaKeyUtil.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PRI_KEY).toString()));
            keyConfiguration.setUserPubKey(RsaKeyUtil.toBytes(redisTemplate.opsForValue().get(REDIS_USER_PUB_KEY).toString()));
        } else {
            Map<String, byte[]> keyMap = RsaKeyUtil.generateKey(keyConfiguration.getUserSecret());
            keyConfiguration.setUserPriKey(keyMap.get("pri"));
            keyConfiguration.setUserPubKey(keyMap.get("pub"));
            redisTemplate.opsForValue().set(REDIS_USER_PRI_KEY, RsaKeyUtil.toHexString(keyMap.get("pri")));
            redisTemplate.opsForValue().set(REDIS_USER_PUB_KEY, RsaKeyUtil.toHexString(keyMap.get("pub")));
        }
    }


    /**
     * 用户公钥
     */
    public void refreshUserPubKey(){
        this.userAuthConfig.setPubKeyByte(keyConfiguration.getUserPubKey());
    }
}