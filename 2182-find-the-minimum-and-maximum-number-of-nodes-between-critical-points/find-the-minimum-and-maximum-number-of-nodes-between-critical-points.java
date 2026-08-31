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
    public int[] nodesBetweenCriticalPoints(ListNode head) {

        int[] ans = {-1, -1};

        // Need at least 3 nodes
        if (head == null || head.next == null || head.next.next == null) {
            return ans;
        }

        int position = 1;

        ListNode prev = head;
        ListNode curr = head.next;

        int first = -1;
        int prevCritical = -1;
        int minDistance = Integer.MAX_VALUE;

        while (curr.next != null) {

            int value = curr.val;
            int prevValue = prev.val;
            int nextValue = curr.next.val;

            // Check if current node is a critical point
            if ((value > prevValue && value > nextValue) ||
                (value < prevValue && value < nextValue)) {

                // First critical point
                if (first == -1) {
                    first = position;
                }

                // We already have a critical point
                if (prevCritical != -1) {
                    minDistance = Math.min(
                        minDistance,
                        position - prevCritical
                    );
                }

                prevCritical = position;
            }

            prev = curr;
            curr = curr.next;
            position++;
        }

        // Fewer than two critical points
        if (first == -1 || first == prevCritical) {
            return ans;
        }

        int maxDistance = prevCritical - first;

        return new int[] {minDistance, maxDistance};
    }
}