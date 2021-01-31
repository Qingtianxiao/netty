package leetcode.P5;

/**
 * Created by ligc on 2021/1/31 13:19
 */
public class Solution {
    public static void main(String[] args) {
        System.out.println(longestPalindrome("aacabdkacaa"));
        System.out.println("babad".substring(0, 2));

    }
    /*
    这里有一种情况需要说明一下：
    (i != 0 && j -i > 2 && dp[i][j - 1] && s.charAt(i -1) == s.charAt(j))
    其实这个证明的是dp[i- 1][j]是回文串，而不是i -> j，所以这个条件需要舍弃
     */
    public static String longestPalindrome(String s) {
        int maxLength = 0;
        int length = s.length();


        if(length <= 1){
            return s;
        }
        String ret = "";

        boolean[][] dp = new boolean[length][length];

        for(int j = 0; j < length; j ++){
            for(int i = 0; i <= j ; i ++){
                if((j - i > 2 && dp[i+1][j-1] && s.charAt(i) == s.charAt(j))
                        ||
                        (j - i <= 2 && s.charAt(i) == s.charAt(j))
                ){
                    dp[i][j] = true;
                    if(j - i +1 > maxLength){
                        maxLength = j -i + 1;
                        ret = s.substring(i, j+1);
                    }
                }else{
                    dp[i][j] = false;
                }
            }
        }
        return ret;
    }
}

