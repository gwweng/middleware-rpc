package com.middleware.rpc.util;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import io.netty.util.concurrent.DefaultThreadFactory;

import java.security.Policy;
import java.util.concurrent.*;

/**
 * @author gengweiweng
 * @time 2021/8/6
 * @desc
 */
public class ExecutorUtils {
    // TODO:获取系统的核心数量

    public static ThreadPoolExecutor getThreadPool(){
        return new ThreadPoolExecutor(1,5,5,
                TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                Executors.defaultThreadFactory(),
                (task, executor) -> {
                    BlockingQueue<Runnable> queue = executor.getQueue();
                    while (true){
                        if(executor.isShutdown()){
                            throw new RejectedExecutionException("ThreadPoolExecutor has shut down!");
                        }

                        try {
                            if(queue.offer(task, 5000, TimeUnit.MILLISECONDS)){
                                break;
                            }
                        }catch (InterruptedException e){
                            throw new AssertionError(e);
                        }
                    }
                }
        );
    }
}
