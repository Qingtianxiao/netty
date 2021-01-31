package testice.myCode;

import Ice.Current;
import testice.code._MyServiceDisp;

/**
 * Created by ligc on 2021/1/28 9:37
 */
public class MyServiceImpl extends _MyServiceDisp {
    @Override
    public String hellow(Current __current) {
        return  "hello, world";
    }
}
