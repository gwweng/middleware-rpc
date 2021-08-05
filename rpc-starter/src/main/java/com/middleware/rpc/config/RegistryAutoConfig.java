package com.middleware.rpc.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

/**
 * @author gengweiweng
 * @time 2021/8/5
 * @desc
 */
@Configuration
@EnableConfigurationProperties(RegistryProperties.class)
@Slf4j
public class RegistryAutoConfig implements BeanFactoryPostProcessor, ApplicationContextAware {



    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        log.info("初始化启动注册中心服务端");
        // TODO:异步启动注册中心服务端
        

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("测试");
    }
}
