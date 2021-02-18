package jvm.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Struct;

/**
 * Created by ligc on 2021/2/13 12:08
 */
public class DynamicProxy implements InvocationHandler {
    Object originalObj;
    Object bind(Object originalObj){
        this.originalObj = originalObj;
        return Proxy.newProxyInstance(originalObj.getClass().getClassLoader(),
                originalObj.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "ture");
        System.out.println("welcome");
        return method.invoke(originalObj, args);
    }
}
