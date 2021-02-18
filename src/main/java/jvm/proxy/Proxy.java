package jvm.proxy;

import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;
import org.apache.kafka.common.utils.Java;

import javax.lang.model.element.Modifier;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * Created by ligc on 2021/2/15 20:15
 * 第一步，生成TimeProxy源码
 */
public class Proxy {
    public static Object newProxyInstance() throws IOException {
        //这里生成的是title
        TypeSpec.Builder typeSpecBuilder = TypeSpec.classBuilder("TimeProxy")
                .addSuperinterface(Flyable.class);
        //这里生成的是字段
        FieldSpec fieldSpec = FieldSpec.builder(Flyable.class, "flyable", Modifier.PRIVATE).build();
        typeSpecBuilder.addField(fieldSpec);
        //这里给的是构造器器
        MethodSpec consturctorMethodSpec = MethodSpec.constructorBuilder().
                addModifiers(Modifier.PUBLIC)
                .addParameter(Flyable.class, "flyable")
                .addStatement("this.flyable = flyable")
                .build();
        typeSpecBuilder.addMethod(consturctorMethodSpec);
        //这里给的是代理对象，对每个方法的处理逻辑
        Method[] methods = Flyable.class.getDeclaredMethods();
        for(Method method : methods){
            MethodSpec methodSpec = MethodSpec.methodBuilder(method.getName())
                    .addModifiers(Modifier.PUBLIC)
                    .addAnnotation(Override.class)
                    .returns(method.getReturnType())
                    .addStatement("long start = $T.currentTimeMillis()", System.class)
                    .addCode("\n")
                    .addStatement("this.flyable." + method.getName() + "()")
                    .addCode("\n")
                    .addStatement("long end = $T.currentTimeMillis()", System.class)
                    .addStatement("$T.out.println(\"Fly time =\" + (end - start))", System.class)
                    .build();
            typeSpecBuilder.addMethod(methodSpec);
        }
        JavaFile javaFile = JavaFile.builder("jvm.proxy", typeSpecBuilder.build()).build();
        javaFile.writeTo(new File("C:\\Users\\LiJX\\Desktop"));
        return null;
    }

    public static void main(String[] args) throws IOException {
//        Proxy.newProxyInstance();
        JavaCompiler.compile(new File("src\\main\\java\\jvm\\proxy\\TimeProxy.java"));
    }
}
