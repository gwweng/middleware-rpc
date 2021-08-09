package com.middleware.rpc.config.spring;

import com.middleware.rpc.config.spring.bean.ConsumerBean;
import com.middleware.rpc.config.spring.bean.ProviderBean;
import com.middleware.rpc.config.spring.bean.RegistryServerBean;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;


/**
 * @author gengweiweng
 * @time 2021/8/9
 * @desc
 */
public class MyNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("registry", new MyBeanDefinitionParser(RegistryServerBean.class));
        registerBeanDefinitionParser("provider", new MyBeanDefinitionParser(ProviderBean.class));
        registerBeanDefinitionParser("consumer", new MyBeanDefinitionParser(ConsumerBean.class));
    }
}
