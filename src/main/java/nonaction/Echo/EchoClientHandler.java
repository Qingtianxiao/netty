package nonaction.Echo;

import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelHandlerInvoker;
import io.netty.util.concurrent.EventExecutorGroup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ligc on 2020/12/21 22:22
 */
public class EchoClientHandler extends ChannelHandlerAdapter {
    private int counter;
    private static final String ECHO_REQ = "Hi, LiGC, welcome to Netty.$_";
    private static final Logger logger = LoggerFactory.getLogger(EchoClientHandler.class);
    public EchoClientHandler(){

    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        for(int i = 0; i < 10; i ++){
            ctx.writeAndFlush(Unpooled.copiedBuffer(ECHO_REQ.getBytes()));
        }
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        logger.info("This is {} times receive server: {}", ++counter, msg);
    }
}
