package hdfs;

import org.apache.hadoop.fs.FsUrlStreamHandlerFactory;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * Created by ligc on 2021/1/20 20:51
 * hadoop权威指南 P57
 * 通过URLStreamHandler实例以标准输出方式显示Hadoop文件系统中的文件
 * IDEA之前使用的JDK版本为12
 * maven打包在服务器运行报错：
 * this version of the Java Runtime only recognizes class file versions up to
 * 在maven plugin中，将build的版本降低即可
 * 注意，这个是cat的api，所以在服务器输入的时候，需要是文件
 * export HADOOP_CLASSPATH=netty-1.0-SNAPSHOT.jar
 * hadoop hdfs.URLCat hdfs://192.168.137.129:9000/inputdata/bulkload/test1.txt
 */
public class URLCat {
    static{
        URL.setURLStreamHandlerFactory(new FsUrlStreamHandlerFactory());
    }

    public static void main(String[] args) throws IOException {
        InputStream in = null;
        try{
            in = new URL(args[0]).openStream();
            //在输入流和输出流之间复制数据
            //复制的缓冲区大小、结束后视奏自动关闭输入流
            IOUtils.copyBytes(in, System.out, 4096, false);
        }finally {
            IOUtils.closeStream(in);
        }
    }
}
