package com.middleware.rpc.config.spring.bean;

/**
 * @author gengweiweng
 * @time 2021/8/9
 * @desc
 */

import com.alibaba.fastjson.JSON;
import com.middleware.rpc.config.ProviderConfig;
import com.middleware.rpc.config.RegistryProperties;
import com.middleware.rpc.config.RpcProviderConfig;
import com.middleware.rpc.domain.LocalServerInfo;
import com.middleware.rpc.registry.RedisRegistryCenter;
import com.middleware.rpc.registry.RegistryCenter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ProviderBean extends ProviderConfig implements ApplicationContextAware {



    @Autowired
    private RegistryProperties registryProperties;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        RegistryCenter registryCenter = new RedisRegistryCenter();
        RpcProviderConfig rpcProviderConfig = new RpcProviderConfig();
        rpcProviderConfig.setApiInterface(apiInterface);
        rpcProviderConfig.setRef(ref);
        rpcProviderConfig.setAlias(alias);
        rpcProviderConfig.setHost(LocalServerInfo.LOCAL_HOST);
        rpcProviderConfig.setPort(LocalServerInfo.LOCAL_PORT);

        //注册生产者
        long count = registryCenter.registryProvider(apiInterface, alias, JSON.toJSONString(rpcProviderConfig),registryProperties);

        log.info("注册生产者：{} {} {}", apiInterface, alias, count);
    }

}

