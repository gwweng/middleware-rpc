package com.middleware.rpc.config;

import lombok.Data;

/**
 * @author gengweiweng
 * @time 2021/8/9
 * @desc
 */
@Data
public class ProviderConfig {

    protected String apiInterface; //接口
    protected String ref;    //映射
    protected String alias;  //别名
}
