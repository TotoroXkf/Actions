from base import TreeNode, ListNode


class Solution:
    def flatten(self, root: TreeNode):
        rebuild(root)


def rebuild(root: TreeNode):
    if root is None:
        return None
    if root.left is None and root.right is None:
        return root
    tail = root
    left_node, right_node = root.left, root.right
    root.left = None
    root.right = rebuild(left_node)
    while tail.right is not None:
        tail = tail.right
    tail.right = rebuild(right_node)
    return root


root = TreeNode(1)
root.left = TreeNode(2)
root.left.left = TreeNode(3)
root.left.right = TreeNode(4)
root.right = TreeNode(5)
root.right.right = TreeNode(6)
root = rebuild(root)
print("xxx")
