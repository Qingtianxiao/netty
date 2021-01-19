package nonaction.multy;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by ligc on 2020/12/21 17:16
 */
public class TimeServerHandler extends ChannelHandlerAdapter {
    private int counter;
    private static Logger logger = LoggerFactory.getLogger(TimeServerHandler.class);

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        String body = (String) msg;
        logger.info("The time server receive order: {}; the counter is: {}", msg, ++counter);

        String currentTime = "QUERY TIME ORDER".equalsIgnoreCase(body) ?
                new Date(System.currentTimeMillis()).toString() :
                "BAD ORDER";

        currentTime = currentTime + System.getProperty("line.separator");
        ByteBuf resq = Unpooled.copiedBuffer(currentTime.getBytes());
        ctx.writeAndFlush(resq);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}
