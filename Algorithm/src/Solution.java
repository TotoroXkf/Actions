import struct.ListNode;

class Solution {
    public ListNode partition(ListNode head, int x) {
        ListNode preHead = new ListNode(x);
        preHead.next = head;
        ListNode leftEdge = preHead;
        ListNode pre = preHead;
        ListNode node = head;
        while (node != null) {
            if (node.val < x) {
                if (leftEdge.next == node) {
                    pre = pre.next;
                    node = node.next;
                    leftEdge = leftEdge.next;
                } else {
                    pre.next = node.next;
                    node.next = leftEdge.next;
                    leftEdge.next = node;
                    leftEdge = leftEdge.next;
                    node = pre.next;
                }
            } else {
                pre = pre.next;
                node = node.next;
            }
        }
        return preHead.next;
    }
}