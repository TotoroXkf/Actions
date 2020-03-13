public class Solution {
    /**
     * 链表的经典问题了。实际上更加是一个数学问题
     * 首先，要知道的是这种问题一般都是两个指针不停的跑，最后得到结果的
     * 所以这个问题是定义两个速度不同的指针，一个跑的快，一个跑的慢。速度差2倍
     * 如果存在环，那么这两个指针一定会在其中的某个地方相遇
     * 这样就可以判断有没有环
     * 在进一步，得到相遇的节点。这个就有一点数学逻辑了
     * 假设
     * 进入环之前的距离为a
     * 慢指针进入环之后走了b被追上
     * 从a被追到的位置到环开始还需要走c
     * 这里b+c为环的长度。并且慢指针进入环的时候，快指针很可能已经在环里面走了很多圈了
     * 这样的话，慢指针走了 a+b
     * 快指针走了 a+k*(b+c)+b
     * 又因为存在2倍速度关系
     * a+k*(b+c)+b = 2(a+b)
     * 整理得到 a = (k-1)(b+c) + c
     * 这个表达式的含义就是，环之前的长度 = (k-1)圈环长 + 相遇点之后的距离
     * 假设 k = 1。a = c
     * 那么还可以再进一步得到推论
     * 假设环比较长，快指针只能在环里面走1圈多，那么a = c
     * 也就是说，让一个指针从相遇点，一个指针从开始，让他们一步一走，相遇的时候就是环开始点了
     * 而如果环比较短，前面的距离长，按照上面的逻辑，一个从head,一个从相遇点走。那么从相遇点走的指针，势必会再走 k-1 圈
     * 最后再走c步，然后两个指针在环开始点相遇
     * 所以上面的步骤就可以统一了
     * 这个题是数学的威力
     */
    public ListNode detectCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        // 快慢相遇
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        if (fast == null || fast.next == null) {
            return null;
        }
        slow = head;
        // 再次相遇就是环开始点
        while (slow != fast) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }
}