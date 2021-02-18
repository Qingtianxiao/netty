package jvm.proxy;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.kafka.common.utils.Java;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * Created by ligc on 2021/2/15 20:15
 * 第一步，生成TimeProxy源码
 */
public class Proxy2 {
    public static Object newProxyInstance(Class inf, InvocationHandler handler) throws IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        //这里生成的是title
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("TimeProxy")
                .addSuperinterface(inf);
        //这里生成的是字段
        FieldSpec fieldSpec = FieldSpec.builder(Flyable.class, "handler", Modifier.PRIVATE).build();
        typeSpecBuilder.addField(fieldSpec);
        //这里给的是构造器器
        MethodSpec consturctorMethodSpec = MethodSpec.constructorBuilder().
                addModifiers(Modifier.PUBLIC)
                .addParameter(Flyable.class, "handler")
                .addStatement("this.handler = handler")
                .build();
        typeSpecBuilder.addMethod(consturctorMethodSpec);
        //这里给的是代理对象，对每个方法的处理逻辑
        Method[] methods = inf.getDeclaredMethods();
        for(Method method : methods){
            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(method.getReturnType())
                    .addCode("try{ \n")
                    .addStatement("\t$T method = " + inf.getName() + ".class.getMethod(\"" +
                            method.getName() + "\")", Method.class)
                    .addStatement("\tthis.handler.invok(this, method, null)")
                    .addCode("} catch (Exception e {\n")
                    .addCode("\te.printStackTrace();\n")
                    .addCode("}\n")
                    .build();
            typeSpecBuilder.addMethod(methodSpec);
        }
//        for(Method method : methods){
//            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
//                    .addModifiers(Modifier.PUBLIC)
//                    .addAnnotation(Override.class)
//                    .returns(method.getReturnType())
//                    .addStatement("long start = $T.currentTimeMillis()", System.class)
//                    .addCode("\n")
//                    .addStatement("this.flyable." + method.getName() + "()")
//                    .addCode("\n")
//                    .addStatement("long end = $T.currentTimeMillis()", System.class)
//                    .addStatement("$T.out.println(\"Fly time =\" + (end - start))", System.class)
//                    .build();
//            typeSpecBuilder.addMethod(methodSpec);
//        }
        String sourcePath = "C:\\Users\\LiJX\\Desktop";
        JavaFile javaFile = JavaFile.builder("jvm.proxy", typeSpecBuilder.build()).build();
        javaFile.writeTo(new File(sourcePath));

        //编译
        JavaCompiler.compile(new File(sourcePath + "\\jvm\\proxy\\TimeProxy.java"));

        //使用反射load到内存
        URL[] urls = new URL[]{new URL("file\\" + sourcePath)};
        URLClassLoader classLoader = new URLClassLoader(urls);
        Class clazz = classLoader.loadClass("jvm.proxy.TimeProxy");
        Constructor constructor = clazz.getConstructor(InvocationHandler.class);
        Object obj = constructor.newInstance(handler);
        return obj;
    }

    public static void main(String[] args) throws IOException {
//        Proxy2.newProxyInstance(Flyable.class, )
    }
}
