package jvm.proxy;

import java.lang.reflect.Method;

/**
 * Created by ligc on 2021/2/16 11:00
 */
/*
proxy:指定动态生成的代理类，这里是TimeProxy
method：传入接口中的所有Method对象
args：对应当前method方法中的参数苏
 */
public interface InvocationHandler {
    void invoke(Object proxy, Method method, Object[] args) throws InterruptedException;
}
