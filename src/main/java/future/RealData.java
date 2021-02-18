package future;

/**
 * Created by ligc on 2020/12/14 15:05
 * RealData，放在别的地方，就是主角了
 * 定义了一个域 content
 * 定义了一个构造器
 * 实现了接口中的getContent方法
 */
public class RealData implements Data {
    private final String content;

    public RealData(int count, char c){
        System.out.println("making RealData(" + count + ", " + c + ") BEGIN");
        char[] buffer = new char[count];
        for(int i = 0; i < count; i ++){
            buffer[i] = c;
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("making RealData(" + count + ", " + c + ") END");
        content = new String(buffer);
    }


    @Override
    public String getContent() {
        return this.content;
    }
}
