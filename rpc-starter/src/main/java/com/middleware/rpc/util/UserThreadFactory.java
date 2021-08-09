package com.middleware.rpc.util;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author gengweiweng
 * @time 2021/8/7
 * @desc
 */
public class UserThreadFactory implements ThreadFactory {
    private final String namePrefix;
    private final AtomicInteger nextId = new AtomicInteger();

    public UserThreadFactory(String namePrefix) {
        this.namePrefix = "From UserThreadFactory";
    }

    @Override
    public Thread newThread(Runnable r) {
        return null;
    }
}
