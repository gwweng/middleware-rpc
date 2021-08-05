package com.middleware.provider.service.impl;

import com.middleware.provider.domain.HelloWorld;
import com.middleware.provider.interfaces.HelloService;
import org.springframework.stereotype.Component;

/**
 * @author gengweiweng
 * @time 2021/8/5
 * @desc
 */
@Component
public class HelloServiceImpl implements HelloService {
    @Override
    public void sayMotto() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setMotto("Hello world");
        System.out.println(helloWorld.getMotto());
    }

    @Override
    public void printMore() {
        HelloWorld helloWorld = new HelloWorld();
        helloWorld.setAge("20");
        helloWorld.setName("David Weng");
        helloWorld.setMotto("Hello world!!!");
        System.out.println(helloWorld.toString());
    }
}
