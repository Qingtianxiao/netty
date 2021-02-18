package jvm.proxy;

import mapreduce.demo2.MyReducer;

import java.lang.reflect.Method;

/**
 * Created by ligc on 2021/2/16 11:15
 */
public class MyInvocationHandler implements InvocationHandler{
    private Bird bird;

    public MyInvocationHandler(Bird bird){
        this.bird = bird;
    }
    @Override
    public void invoke(Object proxy, Method method, Object[] args) {
           long start = System.currentTimeMillis();
//           method.invoke(bird, new Ob)
    }
}
