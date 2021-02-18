package jvm.proxy;

/**
 * Created by ligc on 2021/2/13 12:08
 */
public class Hello implements IHello{
    @Override
    public void sayHello() {
        System.out.println("Hello, world");
    }
}
