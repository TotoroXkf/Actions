from base import TreeNode, ListNode


class Solution:
    def generate(self, rows: int):
        result = []
        for i in range(0, rows):
            new_answer = []
            for j in range(0, i + 1):
                if j == 0:
                    new_answer.append(1)
                elif j == i:
                    new_answer.append(1)
                else:
                    new_answer.append(result[i - 1][j] + result[i - 1][j - 1])
            result.append(new_answer)
        return result
