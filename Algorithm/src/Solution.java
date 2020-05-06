public class Solution {
    public int majorityElement(int[] nums) {
        int temp = nums[0];
        int count = 1;
        for (int i = 1; i < nums.length; i++) {
            // 遇到相同的+1，不相同的减去1
            if (temp == nums[i]) {
                count++;
            } else {
                count--;
            }
            // 如果count减少到了0，说明这个元素在i这个位置，值为temp的元素全部被抵消了，重新选择当前元素开始下一轮
            if (count == 0) {
                temp = nums[i];
                count = 1;
            }
        }
        // 最后确定下来的元素所代表的含义就是，数组中每两个不相同的数字相互抵消，所剩下的数字就是temp
        // 这个temp是唯一可能成为主要元素的结果，这里再做一次验证
        count = 0;
        for (int num : nums) {
            if (num == temp) {
                count++;
            }
            if (count > nums.length / 2) {
                return num;
            }
        }
        return -1;
    }
}