package leetcode.P9;

/**
 * Created by ligc on 2021/2/4 11:16
 */
public class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0){
            return false;
        }
        if(x < 10 ){
            return true;
        }
        String str = x + "";
        if(str.endsWith("0")){
            return false;
        }

        int length = str.length();

        for(int i = 0; i < length / 2; i ++){
            if(str.charAt(i) != str.charAt(length - i - 1)){
                return false;
            }
        }

        return true;

    }
}
