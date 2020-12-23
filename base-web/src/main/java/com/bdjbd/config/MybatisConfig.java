package com.bdjbd.config;

import com.bdjbd.common.common.BaseEnum;
import com.bdjbd.common.handler.KeyEnumTypeHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import tk.mybatis.spring.annotation.MapperScan;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static com.bdjbd.common.constant.DefaultConfigConstants.MAPPER_XML_EXTENDS_PATH;
import static com.bdjbd.common.constant.DefaultConfigConstants.MAPPWE_XML_PATH;

/**
 * @Author: dbc
 * @Date: 2018/9/27
 * @Version: 1.0
 * @Description:
 */
@Configuration
// 管理mybatis中所有mapper接口的代理对象
@MapperScan(basePackages = {"com.bdjbd.dao.mapper"})
@EntityScan(value = {"com.bdjbd.dao.entity"})
@Slf4j
public class MybatisConfig {

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        //因为new了一个SqlSessionFactoryBean 所以yml的mapper-locations的配置没有用，需要手动配置扫描的包
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            List<Resource> resources = new ArrayList<>();
            resources.addAll(Arrays.asList(resolver.getResources(MAPPWE_XML_PATH)));
            resources.addAll(Arrays.asList(resolver.getResources(MAPPER_XML_EXTENDS_PATH)));
            sqlSessionFactoryBean.setMapperLocations(resources.toArray(new Resource[resources.size()]));
        } catch (Exception e) {
            log.error("初始化Mapper异常", e);
            throw new RuntimeException(e);
        }

        sqlSessionFactoryBean.getObject().getConfiguration()
                .getTypeHandlerRegistry().register(BaseEnum.class, KeyEnumTypeHandler.class);
        return sqlSessionFactoryBean.getObject();
    }
}
