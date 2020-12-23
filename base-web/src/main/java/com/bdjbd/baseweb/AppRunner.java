package com.bdjbd.baseweb;

import com.bdjbd.service.common.CacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Author zekunsong
 * @CreateDate 2020/4/20
 * @Version V1.0
 */
@Component
public class AppRunner implements ApplicationRunner {
    @Autowired
    CacheService cacheService;
    /**
     * 启动时加载
     * 清理缓存
     * @param args
     * @throws Exception
     */
    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("启动--加载");
        cacheService.clearAll();
        System.out.println("缓存清理完成");
    }

}
