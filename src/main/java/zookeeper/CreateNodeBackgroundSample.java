package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.BackgroundCallback;
import org.apache.curator.framework.api.CuratorEvent;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by ligc on 2021/1/6 7:31
 */
public class CreateNodeBackgroundSample {
    private static Logger logger = LoggerFactory.getLogger(CreateNodeBackgroundSample.class);
    public static void main(String[] args) throws Exception {
        String path = "/ligc/2021/0106/test";
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("192.168.137.129:2181")
                .sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        //做了一个计数器
        CountDownLatch semaphore = new CountDownLatch(1);

        //做了一个线程池
        ExecutorService tp = Executors.newFixedThreadPool(2);

        client.start();
        logger.info("Main Thread: {}",Thread.currentThread().getName());

        client.create().creatingParentsIfNeeded().inBackground(new BackgroundCallback() {
            @Override
            public void processResult(CuratorFramework curatorFramework, CuratorEvent curatorEvent) throws Exception {
                logger.info("event:[code: {}, type: {}];",curatorEvent.getResultCode(), curatorEvent.getType());
                logger.info("Thread of processResult:{}",Thread.currentThread().getName());
                semaphore.countDown();
            }
        },tp).forPath(path, "init".getBytes());
        semaphore.await();
        tp.shutdown();

    }
}
