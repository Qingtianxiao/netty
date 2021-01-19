package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * Created by ligc on 2021/1/5 22:55
 * Curator更新数据
 */
public class SetDataSample {
    public static void main(String[] args) throws Exception {
        String path = "/ligc/2021/0105/test";
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.137.129:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000,3 ))
                .build();

        client.start();
        Stat stat = new Stat();

        System.out.println(new String(client.getData().storingStatIn(stat).forPath(path)));

        System.out.println("Succescc set node for: " + path + ", new version:" +
                client.setData().withVersion(stat.getVersion()).forPath(path));
    }
}
