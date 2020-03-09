class Solution {
    /**
     * 这一类问题都是维护一个可用的区域，然后遇到合适的值就放到可用的区域里面
     * 对于链表，可以做一个更好的操作就是插在表头，这样不用专门去记区域边界
     */
    public ListNode partition(ListNode head, int x) {
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode preNode = preHead;
        ListNode node = head;
        while (node != null) {
            // 当前值大于目标值就直接跳过了
            // 如果当前值就是head节点，也是不用处理的
            if (node == preHead.next || node.val >= x) {
                preNode = preNode.next;
                node = node.next;
            } else {
                // 找到节点，插入到表头
                ListNode temp = preHead.next;
                preNode.next = node.next;
                preHead.next = node;
                node.next = temp;
                node = preNode.next;
            }
        }
        return preHead.next;
    }
}