package com.middleware.rpc.config.spring.bean;

import com.middleware.rpc.config.RegistryAutoConfig;
import com.middleware.rpc.config.RegistryProperties;
import com.middleware.rpc.domain.LocalServerInfo;
import com.middleware.rpc.network.server.RegistryServerSocket;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author gengweiweng
 * @time 2021/8/9
 * @desc
 */
@Slf4j
public class RegistryServerBean extends RegistryProperties implements ApplicationContextAware {
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

        //初始化服务端
        log.info("初始化生产端服务 ...");
        RegistryServerSocket serverSocket = new RegistryServerSocket(applicationContext);
        Thread thread = new Thread(serverSocket);
        thread.start();
        while (!serverSocket.isActiveSocketServer()) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException ignore) {
            }
        }

        log.info("初始化生产端服务完成 {} {}", LocalServerInfo.LOCAL_HOST, LocalServerInfo.LOCAL_PORT);
    }

}
