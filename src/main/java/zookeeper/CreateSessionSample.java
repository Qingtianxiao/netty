package zookeeper;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by ligc on 2021/1/5 21:47
 * 使用Curator创建会话
 */
public class CreateSessionSample {
    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.137.129:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(retryPolicy)
                .build();

        client.start();
        client.create()
                .creatingParentsIfNeeded()
                .forPath("/ligc/2021/0105/test", "init".getBytes());
//        Thread.sleep(Integer.MAX_VALUE);
    }
}
