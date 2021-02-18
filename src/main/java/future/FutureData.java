package future;

/**
 * Created by ligc on 2020/12/14 15:10
 * FutureData，封装了realData，我查查，这叫做组合，还是什么？
 * 哈哈，叫做聚合
 * UML图，箭头的关系，见《卷一》 p94
 *
 */
public class FutureData implements Data {
    private RealData realData = null;
    private boolean ready = false;

    public synchronized  void setRealData(RealData realData){
        if(ready) return;
        this.realData = realData;
        this.ready = true;
        notifyAll();
    }

    @Override
    public synchronized String getContent() {
        //死等
        while(!ready){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return realData.getContent();
    }
}
