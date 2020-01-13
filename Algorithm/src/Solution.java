class Solution {
    /**
     * 非常经典的问题，解法有很多种
     * 这里记录最简单的一种
     * 遇到一个位置，无外乎就是做两个事情的比较
     * 要么就是从这个位置重新开始新的计算
     * 要么就是连带这个位置继续之前的计算
     * 哪个大，要哪个
     * 贪心算法
     */
    public int maxSubArray(int[] nums) {
        int currentSum = nums[0];
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            currentSum = Math.max(nums[i], nums[i] + currentSum);
            result = Math.max(currentSum, result);
        }
        return result;
    }
}