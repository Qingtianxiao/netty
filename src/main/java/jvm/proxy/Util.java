package jvm.proxy;

import java.lang.reflect.Method;
import java.lang.reflect.Type;

/**
 * Created by ligc on 2021/2/15 9:04
 */
public class Util {
    private static final String ENTER = System.getProperty("line.separator");
    /*
    利用反射是动态生成代理类，并持久化代理类到磁盘（也就是生成代理类的java源文件）
     */
    private static void generateJavaFile(Class<?> interface_, String proxyJavaFileDir){

        StringBuilder proxyJava = new StringBuilder();
//        proxyJava.append("package ").append(interface_.getPackage().getName())
//                .append(";").append(ENTER).append(ENTER)
//                .append("public class ").append(PROXY_CLASS_NAME).append(" implements ")
//                .append(method.getName()).append("{");
//        Method[] methods = interface_.getMethods();
//        for(Method method : methods){
//            Type type = method.getGenericReturnType();
//            Type
//        }
    }
}
