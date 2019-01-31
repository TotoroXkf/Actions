from base import TreeNode, ListNode


class Solution:
    def maxProfit(self, prices: list):
        if not prices:
            return 0
        values = []
        pre_index, index = 0, 1
        while index < len(prices):
            if prices[index] < prices[index - 1]:
                value = prices[index - 1] - prices[pre_index]
                values.append(value)
                pre_index = index
            index += 1
        value = prices[index - 1] - prices[pre_index]
        values.append(value)
        values.sort()
        if len(values) == 1:
            return values[0]
        return values[-1] + values[-2]


test = [3, 3, 5, 0, 0, 3, 1, 4]
print(Solution().maxProfit(test))
