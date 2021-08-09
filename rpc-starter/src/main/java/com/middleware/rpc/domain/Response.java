package com.middleware.rpc.domain;

import lombok.Data;

import java.io.Serializable;
import java.nio.channels.Channel;

/**
 * @author gengweiweng
 * @time 2021/8/6
 * @desc
 */
@Data
public class Response implements Serializable {
    private transient Channel channel;

    private String requestId;

    private Object result;


}
