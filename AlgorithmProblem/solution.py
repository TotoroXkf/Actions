from base import TreeNode


class Solution:
    def sortedArrayToBST(self, nums: list):
        length = len(nums)
        if length == 0:
            return None
        if length == 1:
            return nums[0]
        index = length // 2
        node = TreeNode(nums[index])
        node.left = self.sortedArrayToBST(nums[0:index])
        node.right = self.sortedArrayToBST(nums[index + 1:length])
        return node


Solution().sortedArrayToBST([-10, -3, 0, 5, 9])
