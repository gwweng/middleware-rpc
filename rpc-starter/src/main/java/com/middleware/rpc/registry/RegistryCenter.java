package com.middleware.rpc.registry;

import com.middleware.rpc.config.RegistryProperties;

/**
 * @author gengweiweng
 * @time 2021/8/5
 * @desc
 */
public interface RegistryCenter {
    /**
     * 注册生产者
     * @param apiInterface 接口
     * @param alias 别名
     * @param info 信息
     * 注册结果
     */
    long registryProvider(String apiInterface, String alias, String info, RegistryProperties registryProperties);

    /**
     * 获取生产者
     * @param apiInterface 接口信息
     * @param alias 别名
     * @return 返回可用请求url
     */
    String obtainProvider(String apiInterface, String alias, RegistryProperties registryProperties);

}
