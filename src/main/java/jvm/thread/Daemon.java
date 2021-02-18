package jvm.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by ligc on 2021/2/17 18:53
 */
public class Daemon {
    public static void main(String[] args) throws InterruptedException {
        System.out.print("运行中");
        Thread thread = new Thread(new DaemonTest());
        thread.setDaemon(true);
        thread.start();
        TimeUnit.SECONDS.sleep(10);
        System.out.println();
        System.out.println("完成");
    }
    static class DaemonTest implements Runnable{

        @Override
        public void run() {
            for(;;){
                System.out.print(".");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
