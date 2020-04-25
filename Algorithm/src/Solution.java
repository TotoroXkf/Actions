class Solution {
    public int maximum(int a, int b) {
        // 假设a>b k=1 ,反之，k=0 则最后返回a * k + b * (k ^ 1)即可
        // 假设没有溢出时的情况，计算 b - a 的最高位。
        // k = 1 时 a > b，即 b - a 为负
        // 溢出的情况会在下面处理掉，这里不用管
        int k = b - a >>> 31;
        // 取出两个数字的符号
        int aSign = a >>> 31, bSign = b >>> 31;
        // diff = 0 时同号，diff = 1 时异号
        int diff = aSign ^ bSign;
        // 当diff为0时，也就是同号的时候，这个表达式为k。同号是不会溢出的，所以k直接就是有效的，按照上面的推论返回即可
        // 当diff为1时，也就是异号的时候，这个表达式为bSign。如果为1，说明b是负数。两个数字异号，那么a一定大于b。所以k=bSign，就是k=1。按照上面的返回表达式，a就被返回了
        // bSign如果为0结论反过来
        k = k & (diff ^ 1) | bSign & diff;
        // 结果表达式
        return a * k + b * (k ^ 1);
    }
}