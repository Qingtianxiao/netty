package leetcode.P3;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligc on 2021/1/29 8:49
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Map<Character, Integer> hashMap = new HashMap<>();
        int index = 0;
        int ret = 0;
        int currentLength = 0;
        if(length<=1){
            return length;
        }
        for(int i =0, j = 0; j < length; j ++){
            if(hashMap.containsKey(s.charAt(j)) && hashMap.get(s.charAt(j)) >= i){
                index = hashMap.get(s.charAt(j));
                currentLength = j - index;
                i  = index + 1;
            }else{
                currentLength ++;
            }
            hashMap.put(s.charAt(j), j);
            ret =  Math.max(ret, currentLength);
        }
        return ret;
    }
}
