package jvm;

/**
 * Created by ligc on 2021/2/11 10:15
 * 动态分派演示
 */
public class DynamicDispatch {
    static abstract class Human{
        protected abstract void sayHello();
    }

    static class Man extends Human{

        @Override
        protected void sayHello() {
            System.out.println("Hello, man");
        }
    }

    static class Woman extends Human{

        @Override
        protected void sayHello() {
            System.out.println("Hello, woman");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();

        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
