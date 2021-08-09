package com.middleware.rpc.config;

import lombok.Data;

/**
 * @author gengweiweng
 * @time 2021/8/9
 * @desc
 */
public class ConsumerConfig {
    protected String apiInterface; //接口
    protected String alias;  //别名

    public String getApiInterface() {
        return apiInterface;
    }

    public void setApiInterface(String apiInterface) {
        this.apiInterface = apiInterface;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
