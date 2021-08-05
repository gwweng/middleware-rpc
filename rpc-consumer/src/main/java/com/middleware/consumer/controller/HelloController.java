package com.middleware.consumer.controller;

import com.middleware.provider.interfaces.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author gengweiweng
 * @time 2021/8/5
 * @desc
 */
@RestController
@RequestMapping("/api/hello")
public class HelloController {

    // TODO：远程服务注入
    //@Autowired
    //private HelloService helloService;

    @GetMapping("sayHello")
    public String sayHello(){
      //  helloService.sayMotto();
        return "Hello world";
    }
}
