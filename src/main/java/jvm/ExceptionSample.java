package jvm;

/**
 * Created by ligc on 2021/2/6 21:38
 */
public class ExceptionSample {
    public static void main(String[] args) {
        try{
            int x;
            Thread.sleep(1000);
        }catch (Exception e){
            System.out.println(1);
        }finally {
            System.out.println(2);
        }
    }
}
