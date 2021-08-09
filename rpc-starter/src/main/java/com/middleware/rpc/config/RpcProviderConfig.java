package com.middleware.rpc.config;

import lombok.Data;

/**
 * @author gengweiweng
 * @time 2021/8/9
 * @desc
 */
@Data
public class RpcProviderConfig {
    private String apiInterface; //接口
    private String ref;    //映射
    private String alias;  //别名
    private String host;   //ip
    private int port;      //端口
}
