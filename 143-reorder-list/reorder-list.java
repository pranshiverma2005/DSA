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
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while(fast!=null&& fast.next!=null){
            slow = slow.next;
            fast = fast.next.next;

        }
       
        ListNode prev = null;
        ListNode curr = slow.next;
         slow.next = null;
        while(curr!=null){
        ListNode next = curr.next;
        curr.next =  prev;
        prev = curr;
        curr = next;
        }
        

       ListNode pt1 = head;
ListNode pt2 = prev;

while (pt1 != null && pt2 != null) {

    // save
    ListNode next1 = pt1.next;
    ListNode next2 = pt2.next;

    // connect
    pt1.next = pt2;
    pt2.next = next1;

    // move
    pt1 = next1;
    pt2 = next2;
}
         }
    }
