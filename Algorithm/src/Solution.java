class Solution {
    /**
     * 其实关键点就一个，如何得到每一位上的值是1还是0
     * 用一个1不断的左移并且&，等于0就说明当前位数是0，反之就是1
     */
    public int reverseBits(int num) {
        int mask = 1;
        int preSize = 0;
        int currentSize = 0;
        int maxLen = 0;
        while (mask != 0) {
            // 遇到0，增加之前的长度和当前的长度，再加上翻转的1位
            if ((num & mask) == 0) {
                int len = currentSize + preSize + 1;
                maxLen = Math.max(maxLen, len);
                // 长度重置
                preSize = currentSize;
                currentSize = 0;
            } else {
                currentSize++;
            }
            // 继续左移
            mask = mask << 1;
        }
        if (currentSize > 0) {
            maxLen = Math.max(maxLen, currentSize + preSize);
        }
        return maxLen;
    }
}