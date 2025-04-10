/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode result = root;
        List<TreeNode> pList = new ArrayList<>();
        List<TreeNode> qList = new ArrayList<>();

        // 초기값 설정
        pList.add(root);
        qList.add(root);

        // p 찾으면서 리스트 기록하기
        dfs(root, pList, p);

        // q 찾으면서 리스트 기록하기
        dfs(root, qList, q);

        // p q 리스트 순서대로 마지막으로 일치하는거 찾기
        for(TreeNode pLevel : pList) {
            for(TreeNode qLevel : qList) {
                if(pLevel.equals(qLevel)) {
                    result = pLevel;
                    break;
                }
            }
        }
        
        return result;
    }

    public boolean dfs(TreeNode node, List<TreeNode> list, TreeNode target) {
        // null check
        if(node == null) return false;

        // 목록에 추가
        list.add(node);
        
        // 타겟에 도달시
        if(node.equals(target)) return true;

        // 좌우 모두 탐색
        if(dfs(node.right, list, target)) return true;
        if(dfs(node.left, list, target)) return true;

        // 탐색했는데도 아무것도 없을 경우 -> 백 트래킹
        list.remove(list.size() - 1);
        return false;
    }
}