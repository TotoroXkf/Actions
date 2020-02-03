class Solution {
    public String getPermutation(int n, int k) {
        int[] flags = new int[n + 1];
        return getPermutation(n, k, flags);
    }

    private String getPermutation(int n, int k, int[] flags) {
        if (n == 1) {
            for (int i = 1; i < flags.length; i++) {
                if (flags[i] != -1) {
                    return String.valueOf(i);
                }
            }
        }
        int number = getNumber(n - 1);
        int groupNumber = k / number + 1;
        int i;
        for (i = 1; i < flags.length; i++) {
            if (flags[i] != -1) {
                groupNumber--;
            }
            if (groupNumber == 0) {
                break;
            }
        }
        flags[i] = -1;
        k = k % number;
        return i + getPermutation(n-1, k, flags);
    }

    private int getNumber(int n) {
        int count = 1;
        for (int i = 2; i <= n; i++) {
            count *= i;
        }
        return count;
    }

    public static void main(String[] args) {
        new Solution().getPermutation(3, 3);
    }
}