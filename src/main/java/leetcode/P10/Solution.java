package leetcode.P10;

/**
 * Created by ligc on 2021/2/4 11:42
 */
public class Solution {
    public static void main(String[] args) {
        isMatch("aa", "a*");
    }
    public static boolean isMatch(String s, String p) {
        if(s == null){
            return true;
        }

        if(p.length() < 1){
            return false;
        }
        if(p.startsWith("*")){
            return false;
        }

        boolean[][] dp = new boolean[s.length()][p.length()];

        if(s.charAt(0) == p.charAt(0) || p.charAt(0) == '.'){
            dp[0][0] = true;
        }
        for(int i = 1; i < s.length(); i ++){
            for(int j = 1; j < p.length() &&j <= i; j ++){
                if(dp[i - 1][j - 1]){
                    if(s.charAt(i) == p.charAt(j) || p.charAt(j) == '.' ||
                            (s.charAt(i) == s.charAt(i - 1) && p.charAt(j) =='*') ||
                            (p.charAt(j - 1) == '.' && p.charAt(j) == '*')
                    )
                        dp[i][j] = true;
                }
                dp[i][j] = false;
            }
        }
        for(int j = 0; j < p.length(); j ++){
            if(dp[s.length() - 1][j]){
                return true;
            }
        }
        return false;
    }
}
