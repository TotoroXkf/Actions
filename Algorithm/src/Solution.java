class Solution {
    /**
     * 思路的话就是分别取出奇数位和偶数位，移动后做或运算。
     * 题目规定 num 是int范围的数
     * 0x55555555 = 0101_0101_0101_0101_0101_0101_0101_0101
     * 0xaaaaaaaa = 1010_1010_1010_1010_1010_1010_1010_1010
     * 用这两个数做与运算，就可以把奇数位和偶数位取出来，
     * 然后位左移奇数位，右移偶数位，
     * 再把 奇数位和偶数位做或运算。
     */
    public int exchangeBits(int num) {
        //奇数
        int value1 = num & 0x55555555;
        //偶数
        int value2 = num & 0xaaaaaaaa;
        value1 = value1 << 1;
        value2 = value2 >> 1;
        return value1 | value2;
    }
}