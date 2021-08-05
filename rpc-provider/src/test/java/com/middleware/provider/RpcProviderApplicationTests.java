package com.middleware.provider;

import com.middleware.provider.interfaces.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RpcProviderApplicationTests {

    @Autowired
    HelloService helloService;

    @Test
    void contextLoads() {
        helloService.sayMotto();
        helloService.printMore();
    }

}
