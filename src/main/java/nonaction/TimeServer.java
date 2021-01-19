package nonaction;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.SocketAddress;

/**
 * Created by ligc on 2020/12/21 16:02
 * netty编写时间服务器
 *
 */
public class TimeServer {
    public void bind(int port) throws InterruptedException {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 1024)
                    .childHandler(new ChildChannelHandler())
            .handler(new ChannelHandler() {
                @Override
                public void handlerAdded(ChannelHandlerContext channelHandlerContext) throws Exception {
                    System.out.println("我是链接的");
                    Thread.sleep(3000);
                }

                @Override
                public void handlerRemoved(ChannelHandlerContext channelHandlerContext) throws Exception {

                }

                @Override
                public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) throws Exception {

                }

                @Override
                public void channelRegistered(ChannelHandlerContext channelHandlerContext) throws Exception {

                }

                @Override
                public void channelActive(ChannelHandlerContext channelHandlerContext) throws Exception {

                }

                @Override
                public void channelInactive(ChannelHandlerContext channelHandlerContext) throws Exception {

                }

                @Override
                public void channelRead(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

                }

                @Override
                public void channelReadComplete(ChannelHandlerContext channelHandlerContext) throws Exception {

                }

                @Override
                public void userEventTriggered(ChannelHandlerContext channelHandlerContext, Object o) throws Exception {

                }

                @Override
                public void channelWritabilityChanged(ChannelHandlerContext channelHandlerContext) throws Exception {

                }

                @Override
                public void bind(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, ChannelPromise channelPromise) throws Exception {

                }

                @Override
                public void connect(ChannelHandlerContext channelHandlerContext, SocketAddress socketAddress, SocketAddress socketAddress1, ChannelPromise channelPromise) throws Exception {

                }

                @Override
                public void disconnect(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {

                }

                @Override
                public void close(ChannelHandlerContext channelHandlerContext, ChannelPromise channelPromise) throws Exception {

                }

                @Override
                public void read(ChannelHandlerContext channelHandlerContext) throws Exception {

                }

                @Override
                public void write(ChannelHandlerContext channelHandlerContext, Object o, ChannelPromise channelPromise) throws Exception {

                }

                @Override
                public void flush(ChannelHandlerContext channelHandlerContext) throws Exception {

                }
            });


            ChannelFuture f = b.bind(port).sync();
            f.channel().closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel>{

        protected void initChannel(SocketChannel socketChannel) throws Exception {
            socketChannel.pipeline().addLast(new TimeServerHandler());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int port  = 8090;
        new TimeServer().bind(port);
    }
}
