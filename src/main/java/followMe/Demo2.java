package followMe;

import java.util.concurrent.TimeUnit;

/**
 * Created by ligc on 2020/12/27 16:37
 * ShutdownHook,实现优雅的退出代码的机制
 * 少年啊，你要记住，不确定的东西就自己实验
 * kill -9 pid 用ShutdownHook无解的
 */
public class Demo2 {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("ShutdownHook execute start");
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("ShutdownHook execute end");
        }));
        System.out.println("主线程执行中");
        TimeUnit.SECONDS.sleep(10);
        System.exit(0);
    }

}
