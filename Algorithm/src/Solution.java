class Solution {
    public double myPow(double x, int n) {
        double value = getValue(x, Math.abs(n));
        if (n < 0) {
            value = 1 / value;
        }
        return value;
    }

    private double getValue(double x, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 1) {
            return x;
        }
        double half = getValue(x, n / 2);
        if (n % 2 == 0) {
            return half * half;
        }
        return half * half * x;
    }
}