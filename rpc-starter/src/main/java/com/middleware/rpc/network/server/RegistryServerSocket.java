package com.middleware.rpc.network.server;

import com.middleware.rpc.domain.LocalServerInfo;
import com.middleware.rpc.domain.Request;
import com.middleware.rpc.domain.Response;
import com.middleware.rpc.network.codec.RpcDecoder;
import com.middleware.rpc.network.codec.RpcEncoder;
import com.middleware.rpc.util.NetUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

/**
 * 注册中心服务端
 * @author gengweiweng
 * @time 2021/8/5
 * @desc
 */
@Slf4j
public class RegistryServerSocket implements Runnable{
    //保存异步channel操作结果
    private ChannelFuture future;

    private transient ApplicationContext applicationContext;

    public RegistryServerSocket(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // 当前channel和Selector通信的状态
    public boolean isActiveSocketServer(){
        try {
            if(future != null){
                return future.channel().isActive();
            }
        }catch (Exception e){
            log.error("==============获取通讯状态异常：{}",e.getMessage());
        }
        return false;
    }

    @Override
    public void run() {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            // 编解码处理器，发出编码，接受解码
                            socketChannel.pipeline().addLast(
                                    new RpcDecoder(Request.class),
                                    new RpcEncoder(Response.class),
                                    new ServerHandler(applicationContext)

                            );
                        }
                    });
            // 寻找可用端口
            int port = 8888;
            while (NetUtils.isPortUsing(port)) {
                port++;
            }
            LocalServerInfo.LOCAL_HOST = NetUtils.getHost();
            LocalServerInfo.LOCAL_PORT = port;

            // 注册服务
            this.future = b.bind(port).sync();
            this.future.channel().closeFuture().sync();
        }catch (Exception e){
            e.printStackTrace();
        } finally {
            // 优雅退出
            workGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }


    }
}
