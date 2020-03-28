class Solution {
    /**
     * 举个例子
     * 10 * 4 = (2+2+2+2+2) * 4 = (2^3+2^1) *4  = 4<<3 + 4<<1
     * 所以这个题的关键在于怎么找到这个3和1
     * 10的二进制正好代表了这个
     * 1010
     * 对应的位减去1就是2的阶乘数。逐步取出来，左移，相加即可
     */
    public int multiply(int a, int b) {
        if (a == 0 || b == 0) {
            return 0;
        }
        int mask = 1;
        int pow = 0;
        int result = 0;
        while (mask != 0) {
            // 不断左移取出1
            if ((a & mask) != 0) {
                // 加上结果
                result += (b << pow);
            }
            mask <<= 1;
            pow++;
        }
        return result;
    }
}