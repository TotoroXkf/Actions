class Solution {
    public int search(int[] nums, int target) {
        return search(nums, target, 0, nums.length);
    }

    private int search(int[] nums, int target, int left, int right) {
        if (left >= right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (target == nums[mid]) {
            int leftResult = search(nums, target, left, mid);
            if (leftResult != -1) {
                return leftResult;
            }
            return mid;
        }
        if (target > nums[mid] && nums[right - 1] > nums[mid] && target < nums[right - 1]) {
            return search(nums, target, mid + 1, right);
        }
        if (target < nums[mid] && nums[mid] > nums[left] && target > nums[left]) {
            return search(nums, target, left, mid);
        }
        int leftResult = search(nums, target, left, mid);
        if (leftResult != -1) {
            return leftResult;
        }
        return search(nums, target, mid + 1, right);
    }

    private int binarySearch(int[] nums, int target, int left, int right) {
        while (left <= right) {
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] > target) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}