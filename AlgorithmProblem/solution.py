from base import TreeNode, ListNode


class Solution:
    def pathSum(self, root: TreeNode, target: int):
        result = []
        dfs(root, target, [], result)
        return result


def dfs(root: TreeNode, target: int, nums: list, result: list):
    if root is None:
        return
    target -= root.val
    nums.append(root.val)
    if root.left is None and root.right is None and target == 0:
        result.append(nums)
        return
    dfs(root.left, target, nums[:], result)
    dfs(root.right, target, nums[:], result)
