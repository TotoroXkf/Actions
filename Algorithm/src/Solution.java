import java.util.HashSet;

class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode pre = preHead;
        ListNode post = preHead.next;
        HashSet<Integer> hashSet = new HashSet<>();
        while (post != null) {
            int value = post.val;
            if (hashSet.add(value)) {
                pre = post;
            } else {
                pre.next = post.next;
            }
            post = post.next;
        }
        return preHead.next;
    }
}