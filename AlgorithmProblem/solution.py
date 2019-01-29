from base import TreeNode, ListNode


class Solution:
    def sortedListToBST(self, head: ListNode):
        if head is None:
            return None
        if head.next is None:
            return TreeNode(head.val)
        pre_head = ListNode(0)
        pre_head.next = head
        pre, fast = pre_head, head
        while fast.next is not None and fast.next.next is not None:
            fast = fast.next.next
            pre = pre.next
        mid = pre.next
        node = TreeNode(mid.val)
        if mid is not head:
            pre.next = None
            node.left = self.sortedListToBST(head)
        node.right = self.sortedListToBST(mid.next)
        return node
