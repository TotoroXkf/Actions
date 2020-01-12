class Solution {
    /**
     * 先说常规的思路吧
     * 当遍历到i时，如果能拿到i左边的最大值和i右边的最大值
     * 就可以知道i位置接水量了
     * value = min(leftMax,rightMax) - i
     * 可以用两次遍历，从左向右和从右向左，得到两个数组，分别记录了左右的最大值，然后在遍历一次得到答案
     * 一共遍历3次数组得到答案
     * 这里有一个更加巧妙的算法
     * 使用双指针。
     * 动态维护左右的最大值
     * 左边大的时候移动右边的指针，右边大的时候移动左边的指针
     * 当leftValue<rightValue的时候移动左指针，
     * 判断，如果当前左指针小于左边最大值，根据指针的移动规律，右边的当前指针一定是大于当前左边的所有值的
     * 所以其实就得到右边最大值，就是右指针所在的位置，而且右边最大值大于左边最大值，直接更新i位置的值即可
     * 如果当前左指针大于左边最大值，吧左边最大值更新为当前值即可
     * 右边的处理一样
     * 这样只用遍历一次数组，而且不用开空间。非常的巧妙
     */
    public int trap(int[] height) {
        if (height.length == 0) {
            return 0;
        }
        int result = 0;
        int leftMax = -1;
        int rightMax = -1;
        int left = 0;
        int right = height.length - 1;
        while (left < right) {
            // 左指针大，移动右指针
            if (height[left] >= height[right]) {
                // 右指针小于右边最大值
                if (rightMax >= height[right]) {
                    // 计算结果
                    result += (rightMax - height[right]);
                } else {
                    // 右指针大于右边最大值
                    // 更新右边最大值
                    rightMax = height[right];
                }
                right--;
            } else {
                // 右指针大，移动左指针
                // 左指针小于左边最大值
                if (leftMax >= height[left]) {
                    // 计算结果
                    result += (leftMax - height[left]);
                } else {
                    // 左指针大于左边最大值
                    // 更新左边最大值
                    leftMax = height[left];
                }
                left++;
            }
        }
        return result;
    }
}