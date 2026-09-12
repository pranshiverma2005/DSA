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
    public ListNode oddEvenList(ListNode head) {

        if (head == null || head.next == null) {
            return head;
        }

        ListNode odd = head;
        ListNode even = head.next;
        ListNode evenHead = even;

        while (even != null && even.next != null) {

            // Connect odd node to next odd node
            odd.next = even.next;
            odd = odd.next;

            // Connect even node to next even node
            even.next = odd.next;
            even = even.next;
        }

        // Attach even list after odd list
        odd.next = evenHead;

        return head;
    }
}