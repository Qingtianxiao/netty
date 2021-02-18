package jvm.proxy;

import java.util.Random;

/**
 * Created by ligc on 2021/2/15 19:36
 */
public class Bird implements Flyable{
    @Override
    public void fly() {
        System.out.println("Bird is flying...");
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
