package jvm.thread;

/**
 * Created by ligc on 2021/2/16 16:17
 */
public class VolatileTest {
    public static volatile int race = 0;
    public static void increase(){
        race ++;
    }

    private static final int THREADS_COUNT = 20;

    public static void main(String[] args) {
        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i = 0; i < THREADS_COUNT; i ++){
            threads[i] = new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i = 0; i < 1000; i ++)
                        increase();
                }
            });
            threads[i].start();
        }
        while (Thread.activeCount() > 2){
            Thread.yield();
//            System.out.println(Thread.activeCount());
        }
        System.out.println(race);
    }
}
