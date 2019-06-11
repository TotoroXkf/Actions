class Solution:
    def maxProfit(self, prices: list) -> int:
        max_value = 0
        memory = [0, 0]
        for i in range(2, len(prices)):
            memory.append(self.max_profit(prices[i:]))

        for i in range(len(prices) - 1):
            for j in range(i + 1, len(prices)):
                if prices[j] - prices[i] > 0:
                    if j == len(prices) - 1:
                        value = prices[j] - prices[i]
                    else:
                        value = (prices[j] - prices[i]) + memory[j + 1]
                    max_value = max(value, max_value)
        return max_value

    def max_profit(self, prices: list):
        min_buy = 2 ** 31
        result = 0
        for value in prices:
            min_buy = min(min_buy, value)
            result = max(result, value - min_buy)
        return result


print(Solution().maxProfit([1, 2, 3, 4]))
