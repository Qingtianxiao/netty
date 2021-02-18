package jvm.thread;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * Created by ligc on 2021/2/18 10:25
 */
public class ConnectionPool {
    private LinkedList<Connection> pool = new LinkedList<>();

    public ConnectionPool(int initialSize){
        if(initialSize > 0){
            for(int i = 0; i < initialSize; i ++){
                pool.addLast(ConnectionDriver.createConnection());;
            }
        }
    }

    public void releaseConnection(Connection connection){
        if(connection != null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notifyAll();
            }
        }
    }

    //在mills内无法获取到连接，将返回null
    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            //完全超时
            if(mills <= 0){
                while(pool.isEmpty()){
                    pool.wait();
                }
                return pool.removeFirst();
            }else{
                long future = System.currentTimeMillis() + mills;
                long remaining = mills;
                while(pool.isEmpty() && remaining > 0){
                    pool.wait(remaining);
                    remaining  = future - System.currentTimeMillis();
                }
                Connection result = null;
                if(!pool.isEmpty()) {
                    result = pool.removeFirst();
                }
                return result;
            }
        }
    }
}
