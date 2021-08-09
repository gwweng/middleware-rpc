package com.middleware.rpc.domain;

import io.netty.channel.Channel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author gengweiweng
 * @time 2021/8/6
 * @desc 请求对象
 */
@Data
public class Request implements Serializable {
    private transient Channel channel;

    /**
     * 请求id
     */
    private String requestId;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 参数类型
     */
    private Class[] paramTypes;

    /**
     * 入参
     */
    private Object[] args;

    /**
     * 接口
     */
    private String apiInterface;

    /**
     * 实现类
     */
    private String ref;

    /**
     * 别名
     */
    private String alias;
}
