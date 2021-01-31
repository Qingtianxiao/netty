package leetcode.P1;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ligc on 2021/1/28 16:27
 */
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int length = nums.length;
        Map<Integer, Integer> hashMap = new HashMap<>(length);
        int lef = 0;
        int current = 0;
        for(int i = 0; i < nums.length; i ++){
            current = nums[i];
            lef = target - current;
            if(hashMap.containsKey(lef)){
                return new int[]{hashMap.get(lef), i};
            }
            hashMap.put(current, i);
        }
        return null;
    }
}
