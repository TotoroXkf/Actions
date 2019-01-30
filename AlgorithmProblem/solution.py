from base import TreeNode, ListNode


class Solution:
    def minimumTotal(self, triangle: list):
        length = len(triangle)
        dp = [0 for i in range(0, length)]
        dp[0] = triangle[0][0]
        min_value = dp[0]
        for i in range(1, length):
            min_value = 2147483647
            new_list = []
            for j in range(0, i + 1):
                if j == 0:
                    value = dp[j] + triangle[i][j]
                elif j == i:
                    value = dp[j - 1] + triangle[i][j]
                else:
                    value = min(dp[j - 1] + triangle[i][j], dp[j] + triangle[i][j])
                new_list.append(value)
                min_value = min(value, min_value)
            dp = new_list
        return min_value


test = [
    [2],
    [3, 4],
    [6, 5, 7],
    [4, 1, 8, 3]
]
print(Solution().minimumTotal(test))
