package zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by ligc on 2021/1/6 7:47
 * Curator引入了Cache来实现对Zookeeper服务端时间的监听
 * Cache是Curator中对事件监听的包装，其对事件的监听可以近似看作是一个本地缓存视图和远程Zookeeper视图的对比过程
 * Cache分为两种监听类型：节点监听和子节点监听
 * 此处为一：节点监听
 */
public class NodeCacheSample {
    private static final Logger logger = LoggerFactory.getLogger(NodeCacheSample.class);
    public static void main(String[] args) throws Exception {
        CuratorFramework client = new CuratorClient().get();
        String path = "/ligc/2021/0106/test4";
        client.create().creatingParentsIfNeeded()
                .forPath(path,"init".getBytes());

        NodeCache cache = new NodeCache(client, path, false);
        cache.start(true);

        cache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                ChildData data = cache.getCurrentData();
                boolean isNull = data == null;
                logger.info(isNull + "");
                logger.info("Node data update, new data:" + new String(data.getData()));
            }
        });

        client.setData().forPath(path,"u".getBytes());
        Thread.sleep(1000);
        client.delete().forPath(path);
        Thread.sleep(Integer.MAX_VALUE);
    }
}
