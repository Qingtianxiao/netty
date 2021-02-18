package leetcode.P110;

/**
 * Created by ligc on 2021/2/4 12:57
 */
/**
 * Definition for a binary tree node.
 * public class TreeNode {

 * }
 */
public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
