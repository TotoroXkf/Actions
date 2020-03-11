public class Solution {
    /**
     * 链表的经典问题了
     * 实际上，这种问题都可以使用两个指针不停地跑，得到结果
     * 首先假设两个链表不相交
     * 1-2-3-4-5
     * a-b-c
     * 那么他们的总和就是8。这个总和对于连个链表是一样的
     * 当第一个链表跑到头的时候，从第二个链表开始重新跑
     * 当第二个链表跑到头的时候，从第一个链表开始重新跑
     * 这样的话，都跑完8次之后，就都会遇到null,这个时候就可以判断出来无相交节点
     * 现在假设相交
     * 1-2-3-4-5
     * a-b-c/
     * 这样来看的话，还是上面的思路，1,2,3,4,5,a,b,c这8个节点对于两个链表来说，就是一致的
     * 还是上面的处理
     * 当第一个链表跑到头的时候，从第二个链表开始重新跑
     * 当第二个链表跑到头的时候，从第一个链表开始重新跑
     * 这样，第一个链表跑完8次之后，将会从c到4
     * 第二个链表将会从3走到4.这样就相等了，判断有环
     * 总和上面的两个情况，判断退出的结果就是第一个链表的节点和第二个链表的节点相同即可
     * 而且一定会出现相同，不论想不想交
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode nodeA = headA;
        ListNode nodeB = headB;
        while (nodeA != nodeB) {
            if (nodeA == null) {
                nodeA = headB;
            } else {
                nodeA = nodeA.next;
            }
            if (nodeB == null) {
                nodeB = headA;
            } else {
                nodeB = nodeB.next;
            }
        }
        return nodeA;
    }
}