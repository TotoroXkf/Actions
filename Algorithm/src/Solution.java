class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int upValue = 0;
        ListNode node1 = l1;
        ListNode node2 = l2;
        ListNode result = null;
        ListNode tail = null;
        while (node1 != null || node2 != null) {
            int count = (node1 == null ? 0 : node1.val) + (node2 == null ? 0 : node2.val)+upValue;
            if (count >= 10) {
                count -= 10;
                upValue = 1;
            } else {
                upValue = 0;
            }
            if (result == null) {
                result = new ListNode(count);
                tail = result;
            } else {
                tail.next = new ListNode(count);
                tail = tail.next;
            }
            if (node1 != null) {
                node1 = node1.next;
            }
            if (node2 != null) {
                node2 = node2.next;
            }
        }
        if (upValue == 1) {
            tail.next = new ListNode(1);
        }
        return result;
    }
}
