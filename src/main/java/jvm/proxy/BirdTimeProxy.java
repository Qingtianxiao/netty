package jvm.proxy;

/**
 * Created by ligc on 2021/2/15 19:42
 */
public class BirdTimeProxy implements Flyable{
    private Flyable flyable;
    public BirdTimeProxy(Flyable flyable){
        this.flyable = flyable;
    }
    @Override
    public void fly() {
        long start = System.currentTimeMillis();
        flyable.fly();
        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}
