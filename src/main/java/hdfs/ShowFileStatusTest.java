package hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.junit.Before;

import java.net.URI;

/**
 * Created by ligc on 2021/1/20 22:00
 * Hadoop权威指南P63
 * 展示文件信息
 * 这里的话缺少源码编译环境
 * 缺少MiniDFSCluster等
 */
public class ShowFileStatusTest {
    private FileSystem fs;

    public static void main(String[] args) {

    }
    @Before
    public void setUp() {
        Configuration conf = new Configuration();
        if(System.getProperty("test.build.data") == null){
            System.setProperty("test.build.data", "/tmp");
//            fs = FileSystem.get(URI.create("/tmp"), )
        }
    }
}
