package leetcode.P9;

/**
 * Created by ligc on 2021/2/4 11:25
 * 不使用String类
 */
public class Solution2 {
    public boolean isPalindrome(int x) {
        int origin = x;
        if(x < 0){
            return false;
        }
        if(x < 10 ){
            return true;
        }

        int reverse = 0;
        while( x / 10 != 0){
            reverse = x % 10 + reverse * 10;
            x /= 10;
        }
        reverse = x % 10 + reverse * 10;

        if(reverse == origin){
            return true;
        }else{
            return false;
        }
    }
}
