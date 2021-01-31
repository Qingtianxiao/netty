package nonaction;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by ligc on 2021/1/28 18:15
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("pwwkew"));
    }
    public static int lengthOfLongestSubstring(String s) {
        int length = s.length();
        Map<Character, Integer> hashMap = new HashMap<>();
        int index = 0;
        int ret = 0;
        int currentLength = 0;
        if(length<=1){
            return length;
        }
        //"pwwkew"
        for(int i =0, j = 0; j < length; j ++){
            if(hashMap.containsKey(s.charAt(j))){
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
