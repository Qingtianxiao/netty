package jvm.proxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by ligc on 2021/2/15 19:43
 */
public class Test {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        URL[] urls = new URL[]{new URL("file:\\" + "src\\main\\java")};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass("jvm.proxy.TimeProxy");
        Constructor constructor = clazz.getConstructor(Flyable.class);
        Flyable flyable = (Flyable) constructor.newInstance(new Bird());
        flyable.fly();
    }
}
