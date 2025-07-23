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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        List<Integer> integerList = new ArrayList();

        // list 처리
        while(list1 != null) {
            integerList.add(list1.val);

            list1 = list1.next;
        }

        while(list2 != null) {
            integerList.add(list2.val);

            list2 = list2.next;
        }

        Collections.sort(integerList);

        // null 처리
        if(integerList.isEmpty()) return null;

        // 초깃값
        ListNode answer = new ListNode(integerList.get(0));
        ListNode nextNode = answer;

        for(int i = 1; i < integerList.size(); i++) {
            nextNode.next = new ListNode(integerList.get(i));
            nextNode = nextNode.next;
        }

        return answer;
    }
}