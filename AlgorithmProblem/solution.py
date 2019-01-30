from base import TreeNode, ListNode


class Solution:
    def getRow(self, row: int):
        result = [1 for i in range(0, row + 1)]
        for i in range(2, row + 1):
            pre = 1
            for j in range(1, i):
                value = pre + result[j]
                pre = result[j]
                result[j] = value
        return result


test = Solution().getRow(3)
print(test)
