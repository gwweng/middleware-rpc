package com.middleware.rpc.registry;

import com.middleware.rpc.config.RegistryProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 注册中心采用
 * @author gengweiweng
 * @time 2021/8/5
 * @desc
 */
@Slf4j
@Component
public class RedisRegistryCenter implements RegistryCenter {


    private static  Jedis jedis;

    private void init(RegistryProperties registryProperties){
        log.info("启动Redis模拟注册中心");
        // 池基本配置
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(5);
        config.setTestOnBorrow(false);
        JedisPool jedisPool;
        if(registryProperties.getPassword() == null){
            jedisPool =  new JedisPool(config, registryProperties.getHost(), registryProperties.getPort());
        }else {
            jedisPool = new JedisPool(config, registryProperties.getHost(),
                    registryProperties.getPort(),
                    registryProperties.getTimeout(),
                    registryProperties.getPassword());
        }
        jedis = jedisPool.getResource();

    }
    @Override
    public long registryProvider(String apiInterface, String alias, String info, RegistryProperties registryProperties) {
        if(jedis == null){
            init(registryProperties);
        }
        return jedis.sadd(apiInterface + "_" + alias, info);
    }

    @Override
    public String obtainProvider(String apiInterface, String alias, RegistryProperties registryProperties) {
        if(jedis == null){
            init(registryProperties);
        }
        return jedis.srandmember(apiInterface + "_" + alias);
    }
}
