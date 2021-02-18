package future;

/**
 * Created by ligc on 2020/12/14 15:13
 */
public class Host {
    //这是封装了一个统一的入口咯
    public Data request(final int count, final char c){
        System.out.println("request(" + count + ", " + c + ") BEGIN");
        //构造了一个FutureData，FutureData没有构造器
        final FutureData futureData = new FutureData();
        //在新的线程里，set了一下Data
        //究其原因，其实就是这个东西咯，新起一个线程，去执行阻塞的操作，主线程返回了
        new Thread(){
            public void run(){
                RealData realData = new RealData(count, c);
                futureData.setRealData(realData);
            }
        }.start();
        System.out.println("request(" + count + ", " + c + ") END");
        return futureData;
    }
}
