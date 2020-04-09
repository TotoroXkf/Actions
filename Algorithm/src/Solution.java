class Solution {
    public int countEval(String s, int result) {
        char[] chars = s.toCharArray();
        return countEval(chars, 0, chars.length, result);
    }

    private int countEval(char[] chars, int start, int end, int value) {
        if (start >= end) {
            return 0;
        }
        if (end - start == 1) {
            if (chars[start] - '0' == value) {
                return 1;
            } else {
                return 0;
            }
        }
        int result = 0;
        for (int i = start + 1; i < end; i++) {
            char symbol = chars[i];
            switch (symbol) {
                case '&':
                    result += getLeftAndRightSameResult(chars, start, end, i, value);
                    break;
                case '|':
                    if (value == 0) {
                        result += getLeftAndRightSameResult(chars, start, end, i, value);
                    } else {
                        result += getLeftAndRightDiffResult(chars, start, end, i);
                    }
                    break;
                case '^':
                    if (value == 0) {
                        result += getLeftAndRightSameResult(chars, start, end, i, 0);
                        result += getLeftAndRightSameResult(chars, start, end, i, 1);
                    } else {
                        result += getLeftAndRightDiffResult(chars, start, end, i);
                    }
                    break;
            }
        }
        return result;
    }

    private int getLeftAndRightDiffResult(char[] chars, int start, int end, int i) {
        int leftResult;
        int rightResult;
        int result = 0;
        leftResult = countEval(chars, start, i, 0);
        rightResult = countEval(chars, i + 1, end, 1);
        result += (leftResult * rightResult);
        leftResult = countEval(chars, start, i, 1);
        rightResult = countEval(chars, i + 1, end, 0);
        result += (leftResult * rightResult);
        return result;
    }

    private int getLeftAndRightSameResult(char[] chars, int start, int end, int i, int value) {
        int leftResult;
        int rightResult;
        leftResult = countEval(chars, start, i, value);
        rightResult = countEval(chars, i + 1, end, value);
        return leftResult * rightResult;
    }

    public static void main(String[] args) {
        String s = "0&0&0&1^1|0";
        int result = 1;
        Solution solution = new Solution();
        System.out.println(solution.countEval(s, result));
    }
}