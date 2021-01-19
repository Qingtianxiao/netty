package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Created by ligc on 2021/1/6 7:49
 */
public class CuratorClient {
    private CuratorFramework client;
    private static final String PATH = "/ligc/2021/0106/";

    public CuratorClient(){
        this(PATH);
    }

    public CuratorClient(String childrenPath){
        client = CuratorFrameworkFactory.builder()
                .connectString("192.168.137.129:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(5000,3))
                .build();
        client.start();
    }
    public CuratorFramework get(){
        return this.client;
    }

}
