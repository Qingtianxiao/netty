package jvm;

import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * Created by ligc on 2021/2/13 11:57
 * 类与类加载器
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        ClassLoader classLoader = new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                String fileName = name.substring(name.lastIndexOf(".") + 1) + ".class";
                InputStream is = getClass().getResourceAsStream(fileName);
                if(is == null){
                    return super.loadClass(name);
                }
                byte[] b = new byte[0];
                try {
                    b = new byte[is.available()];
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    is.read(b);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return defineClass(name, b, 0, b.length);
            }
        };
        Object obj = classLoader.loadClass("jvm.ClassLoaderTest").newInstance();
        System.out.println(obj.getClass());
        System.out.println(obj instanceof jvm.ClassLoaderTest);
    }
}
