package jvm.proxy;

/**
 * Created by ligc on 2021/2/15 19:41
 */
public class BirdLogProxy implements Flyable{
    private Flyable flyable;
    public BirdLogProxy(Flyable flyable){
        this.flyable = flyable;
    }

    @Override
    public void fly() {
        System.out.println("Bird fly start");
        flyable.fly();
        System.out.println("Bird fly end...");
    }
}
