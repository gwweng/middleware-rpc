package com.middleware.rpc.config;

import com.middleware.rpc.domain.LocalServerInfo;
import com.middleware.rpc.util.ExecutorUtils;
import lombok.extern.slf4j.Slf4j;
import com.middleware.rpc.network.server.RegistryServerSocket;
import org.springframework.beans.BeansException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * 开启RPC功能，自动连接注册中心
 * @author gengweiweng
 * @time 2021/8/5
 * @desc
 */
@Slf4j
@Configuration
@EnableConfigurationProperties(RegistryProperties.class)
public class RegistryAutoConfig implements ApplicationContextAware {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        log.info("初始化启动注册中心服务端");
        // 异步启动注册中心服务端
        ThreadPoolExecutor threadPool = ExecutorUtils.getThreadPool();

        RegistryServerSocket registryServerSocket = new RegistryServerSocket(applicationContext);
        threadPool.execute(registryServerSocket);
        // 异步线程执行未完成
        while (!registryServerSocket.isActiveSocketServer()){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {

            }
        }
        log.info("初始化注册中心服务端完成，ip=>{}, port=>{}", LocalServerInfo.LOCAL_HOST, LocalServerInfo.LOCAL_PORT);
    }
}
