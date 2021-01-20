package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.kerby.util.IOUtil;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;

/**
 * Created by ligc on 2021/1/20 21:14
 * Hadoop权威指南P58
 * 直接使用FileSystem以标准输出格式显示Hadoop文件系统中的文件
 */
public class FileSystemCat {
    public static void main(String[] args) throws IOException {
        String uri = args[0];
        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(uri), conf);
        InputStream in = null;
        try{
            in= fs.open(new Path(uri));
            IOUtils.copyBytes(in, System.out, 4096, false);
        }finally {
            IOUtils.closeStream(in);
        }
    }
}
