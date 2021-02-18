package leetcode.P11;

/**
 * Created by ligc on 2021/2/4 12:36
 */
public class Solution {
    public int maxArea(int[] height) {
        if(height.length <1){
            return 0;
        }
        int i = 0;
        int j = height.length - 1;
        int max = Math.min(height[i], height[j]) * (j - i);
        while(i < j){
            if(height[i] > height[j]){
                j --;
            }else{
                i ++;
            }
            max = Math.max(max,Math.min(height[i], height[j]) * (j - i));
        }
        return max;
    }
}
