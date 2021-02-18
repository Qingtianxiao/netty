package jvm.proxy;

/**
 * Created by ligc on 2021/2/13 12:07
 * 动态代理技术
 */
public class DynamicProxyTest {
    public static void main(String[] args) throws InterruptedException {
        IHello hello = (IHello) new DynamicProxy().bind(new Hello());
        hello.sayHello();
        Thread.sleep(Integer.MAX_VALUE);
    }
}
