package com.middleware.rpc.network.server;

import com.middleware.rpc.domain.Request;
import com.middleware.rpc.util.ClassLoaderUtils;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.context.ApplicationContext;

/**
 * @author gengweiweng
 * @time 2021/8/9
 * @desc
 */
public class ServerHandler extends ChannelInboundHandlerAdapter {

    private ApplicationContext applicationContext;

    public ServerHandler(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        Request request = (Request) msg;
        // 获取调用的接口SerVice class
        Class<?> classType = ClassLoaderUtils.forName(request.getApiInterface());

    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }
}
