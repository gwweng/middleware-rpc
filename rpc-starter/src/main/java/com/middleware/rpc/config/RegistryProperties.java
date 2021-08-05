package com.middleware.rpc.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 注册中心配置
 * @author gengweiweng
 * @time 2021/8/5
 * @desc
 */
@ConfigurationProperties(prefix = "rpc.registry")
@Data
public class RegistryProperties {
    /**
     * 注册中心地址
      */
    private String host;

    /**
     * 注册中心端口
     */
    private int port;

    /**
     * 注册中心密码（redis可能需要）
     */
    private String password;

    /**
     * 注册中心连接超时时长（redis需要）
     */
    private int timeout;
}
