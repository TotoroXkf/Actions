class Solution {
    public void merge(int[] a, int m, int[] b, int n) {
        int index = m + n - 1;
        m -= 1;
        n -= 1;
        while (index > -1) {
            if (n == -1 || (m > -1 && a[m] >= b[n])) {
                a[index] = a[m];
                m--;
            } else {
                a[index] = b[n];
                n--;
            }
            index--;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] a = new int[]{1, 2, 3, 0, 0, 0};
        int[] b = new int[]{2, 5, 6};
        solution.merge(a, 3, b, 3);
    }
}