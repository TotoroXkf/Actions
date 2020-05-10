class Solution {
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        if (nums.length == 1) {
            return nums[0];
        }
        int preA = nums[0];
        int preB = Math.max(nums[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int value = Math.max(nums[i] + preA, preB);
            preA = preB;
            preB = value;
        }
        return preB;
    }
}