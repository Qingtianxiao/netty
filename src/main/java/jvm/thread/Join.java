package jvm.thread;

import java.util.concurrent.TimeUnit;

/**
 * Created by ligc on 2021/2/18 10:12
 */
public class Join {
    public static void main(String[] args) throws InterruptedException {
        Thread previous = Thread.currentThread();
        for(int i = 0; i < 10; i ++){
            Thread thread = new Thread(new Domino(previous), String.valueOf(i));
            thread.start();
            previous = thread;
        }

        TimeUnit.SECONDS.sleep(5);
        System.out.println(Thread.currentThread().getName() + " terminated.");
    }

    static class Domino implements  Runnable{
        private Thread thread;
        public Domino(Thread thread){
            this.thread = thread;
        }

        @Override
        public void run() {
            try{
                thread.join();
            }catch (InterruptedException e){

            }
            System.out.println(Thread.currentThread().getName() + " terminated.");
        }
    }
}
