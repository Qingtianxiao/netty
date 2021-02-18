package jvm.proxy;

/**
 * Created by ligc on 2021/2/15 19:38
 * 使用聚合，增强原始方法
 */
public class Bird3 implements Flyable{
    private Bird bird;
    public Bird3(Bird bird){
        this.bird = bird;
    }
    @Override
    public void fly() {
        long start = System.currentTimeMillis();
        this.bird.fly();
        long end = System.currentTimeMillis();
        System.out.println("Fly time = " + (end - start));
    }
}
