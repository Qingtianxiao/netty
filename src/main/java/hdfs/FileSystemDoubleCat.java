package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;

import java.io.IOException;
import java.net.URI;

/**
 * Created by ligc on 2021/1/20 21:23
 * Hadoop权威指南P60
 * FileSystem.open()方法返回的是一个FSDataInputStream对象
 *  该类继承了java.io.DataInputStream，支持随机访问
 *  使用seek()方法，将hadoop文件系统中的一个文件在标准输出上显示两次
 */
public class FileSystemDoubleCat {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);

        FSDataInputStream in = null;
        try{
            in = fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
            in.seek(0);
            IOUtils.copyBytes(in, System.out, 4096, false);
        }finally {
            IOUtils.closeStream(in);
        }
    }
}
