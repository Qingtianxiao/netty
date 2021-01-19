package followMe;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;

/**
 * Created by ligc on 2020/12/27 17:16
 * java原生NIO的使用
 * 客户端的
 */
public class Demo3 {
    public static void main(String[] args) throws IOException {
        //1.打开SocketChannel，绑定客户端的本地地址
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress(8888));
        //2.设置SocketChannel为非阻塞模式
        socketChannel.configureBlocking(false);
    }
}
