/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {

    private int maxCount = 0;
    boolean right = true;

    public int longestZigZag(TreeNode root) {
        //initial setting
        int count = 0;

        // start with right
        dfs(root.right, count, right);

        // start with left
        dfs(root.left, count, !right);

        return maxCount;
    }

    public void dfs(TreeNode tree, int count, boolean direction) {
        // if null return 
        // update maxCount
        if(tree == null) {
            maxCount = Math.max(maxCount, count);
            return;
        }

        // change direction
        // keep direction + initalize count
        if(direction == right) {
            dfs(tree.left, count + 1, !right);
            dfs(tree.right, 0, right);
        }

        if(direction == !right) {
            dfs(tree.right, count + 1, right);
            dfs(tree.left, 0, !right);
        }
    }
}