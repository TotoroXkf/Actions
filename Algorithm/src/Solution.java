import java.util.ArrayList;
import java.util.List;

class Solution {
    public int findMagicIndex(int[] nums) {
        return findMagicIndex(nums, 0, nums.length);
    }

    public int findMagicIndex(int[] nums, int left, int right) {
        if (left >= right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (mid == nums[mid]) {
            return mid;
        }
        int leftResult = findMagicIndex(nums, left, mid);
        if (leftResult != -1) {
            return leftResult;
        }
        return findMagicIndex(nums, mid + 1, right);
    }
}