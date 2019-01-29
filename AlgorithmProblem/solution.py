from base import TreeNode


class Solution:
    def sortedArrayToBST(self, nums: list):
        if not nums:
            return None
