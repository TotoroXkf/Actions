class Solution {
    public int kthToLast(ListNode head, int k) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode pre = preHead;
        ListNode post = pre;
        for (int i = 0; i < k && post != null; i++) {
            post = post.next;
        }
        while (post != null) {
            pre = pre.next;
            post = post.next;
        }
        return pre.val;
    }
}