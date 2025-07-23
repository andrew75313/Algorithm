/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode removeElements(ListNode head, int val) {
        // null check
        if(head == null) return null;
        
        List<Integer> integerList = new ArrayList();

        while(head != null) {
            integerList.add(head.val);

            head = head.next;
        }

        integerList.removeIf(n -> n == val);

        if(integerList.isEmpty()) return null;

        ListNode answer = new ListNode(integerList.get(0));
        ListNode nextNode = answer;

        for(int i = 1; i < integerList.size(); i++) {
                nextNode.next = new ListNode(integerList.get(i));
                nextNode = nextNode.next;
        }

        return answer;
    }
}