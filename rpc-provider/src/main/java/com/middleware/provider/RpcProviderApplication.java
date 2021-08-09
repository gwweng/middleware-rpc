package com.middleware.provider;

import com.middleware.rpc.annotation.EnableRPC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@EnableRPC
@SpringBootApplication
@ImportResource(locations = {"classpath:spring-config.xml"})
public class RpcProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RpcProviderApplication.class, args);
    }

}
