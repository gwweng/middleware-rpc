package com.middleware.rpc.config.spring.bean;

import com.alibaba.fastjson.JSON;
import com.middleware.rpc.config.ConsumerConfig;
import com.middleware.rpc.config.RegistryProperties;
import com.middleware.rpc.config.RpcProviderConfig;
import com.middleware.rpc.domain.Request;
import com.middleware.rpc.network.client.ClientSocket;
import com.middleware.rpc.reflect.JDKProxy;
import com.middleware.rpc.registry.RedisRegistryCenter;
import com.middleware.rpc.registry.RegistryCenter;
import com.middleware.rpc.util.ClassLoaderUtils;
import io.netty.channel.ChannelFuture;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author gengweiweng
 * @time 2021/8/9
 * @desc
 */
@Component
public class ConsumerBean<T> extends ConsumerConfig implements FactoryBean {

    private ChannelFuture channelFuture;

    private RpcProviderConfig rpcProviderConfig;

    @Resource
    RegistryProperties redisProperties;

    @Override
    public Object getObject() throws Exception {

        RegistryCenter registryCenter = new RedisRegistryCenter();
        //从redis获取链接
        if (null == rpcProviderConfig) {
            String infoStr = registryCenter.obtainProvider(apiInterface, alias, redisProperties);
            rpcProviderConfig = JSON.parseObject(infoStr, RpcProviderConfig.class);
        }
        assert null != rpcProviderConfig;

        //获取通信channel
        if (null == channelFuture) {
            ClientSocket clientSocket = new ClientSocket(rpcProviderConfig.getHost(), rpcProviderConfig.getPort());
            new Thread(clientSocket).start();
            for (int i = 0; i < 100; i++) {
                if (null != channelFuture) {
                    break;
                }
                Thread.sleep(500);
                channelFuture = clientSocket.getFuture();
            }
        }

        Request request = new Request();
        request.setChannel(channelFuture.channel());
        request.setApiInterface(apiInterface);
        request.setRef(rpcProviderConfig.getRef());
        request.setAlias(alias);
        Object proxy = JDKProxy.getProxy(ClassLoaderUtils.forName(apiInterface), request);
        return proxy;

    }

    @Override
    public Class<?> getObjectType() {
        try {
            if(apiInterface  != null){
                return ClassLoaderUtils.forName(apiInterface);
            }
        } catch (ClassNotFoundException e) {

        }
        return null;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}

