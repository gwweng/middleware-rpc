package com.middleware.rpc.annotation;

import com.middleware.rpc.config.RegistryAutoConfig;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 是否开启RPC调用
 * @author gengweiweng
 * @time 2021/8/5
 * @desc
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ComponentScan("com.middleware.rpc.*")
@Import(RegistryAutoConfig.class)
public @interface EnableRPC {
}
