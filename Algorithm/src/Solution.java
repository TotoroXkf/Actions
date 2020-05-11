class Solution {
    public int add(int a, int b) {
        int sum = a;
        while (b != 0) {
            sum = a ^ b;
            int up = (a & b) << 1;
            a = sum;
            b = up;
        }
        return sum;
    }
}