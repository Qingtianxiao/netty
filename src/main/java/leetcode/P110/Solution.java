package leetcode.P110;

/**
 * Created by ligc on 2021/2/4 12:57
 */
public class Solution {
    public boolean isBalanced(TreeNode root) {
        return height(root) == -1 ? false : true;
    }
    public static int height(TreeNode root){
        if(root == null){
            return 0;
        }
        int leftHeight = height(root.left);
        int rightHeight = height( root.right);
        if(leftHeight == -1 || rightHeight == -1 || Math.abs(rightHeight -leftHeight) > 1){
            return -1;
        }
        return Math.max(leftHeight, rightHeight) + 1;
    }
}
