class Solution {
	public int search(int[] nums, int target) {
		return search(nums, target, 0, nums.length - 1);
	}

	private int search(int[] nums, int target, int left, int right) {
		if (left > right) {
			return -1;
		}
		int mid = (left + right) / 2;
		if (target == nums[mid]) {
			return mid;
		}
		if (target > nums[mid] && target <= nums[right] && nums[right] >= nums[mid]) {
			// 左边有序且落在左边，二分搜索左边
			return binarySearch(nums, target, mid + 1, right);
		} else if (nums[right] >= nums[mid]) {
			// 左边有序但是不落在左边，递归右边
			return search(nums, target, left, mid - 1);
		}
		if (target < nums[mid] && target >= nums[left] && nums[mid] >= nums[left]) {
			// 右边有序且落在右边，二分搜索右边
			return binarySearch(nums, target, left, mid - 1);
		} else if (nums[mid] >= nums[left]) {
			// 右边有序但是不落在右边，二分搜索左边
			return search(nums, target, mid + 1, right);
		}
		return -1;
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