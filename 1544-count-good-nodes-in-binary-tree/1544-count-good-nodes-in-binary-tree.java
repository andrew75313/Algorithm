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

    private int count = 0;

    public int goodNodes(TreeNode root) {
        // initial setting
        int maxVal = root.val;
        count++;

        dfs(root.right, maxVal);
        dfs(root.left, maxVal);

        return count;
    }

    public void dfs(TreeNode treeNode, int maxVal) {
        // null check
        if(treeNode == null) return;

        // find val & count
        if(treeNode.val >= maxVal) {
            count++;
            maxVal = treeNode.val;
        }

        // recursion
        TreeNode rightNode = treeNode.right;
        TreeNode leftNode = treeNode.left;
        if(rightNode != null) dfs(treeNode.right, maxVal);
        if(leftNode != null) dfs(treeNode.left, maxVal);
    }
}