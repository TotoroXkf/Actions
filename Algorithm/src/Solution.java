class Solution {
	public int[] searchRange(int[] nums, int target) {
		int[] result = new int[]{-1, -1};
		searchRange(nums, target, 0, nums.length - 1, result);
		return result;
	}
	
	private void searchRange(int[] nums, int target, int left, int right, int[] result) {
		if (left > right) {
			return;
		}
		int findResult = binarySearch(nums, target, left, right);
		if (findResult == -1) {
			return;
		}
		if (findResult == 0 || (findResult > 0 && nums[findResult - 1] != target)) {
			result[0] = findResult;
		} else {
			searchRange(nums, target, left, findResult - 1, result);
		}
		if (findResult == nums.length - 1 || (findResult < nums.length - 1 && nums[findResult + 1] != target)) {
			result[1] = findResult;
		} else {
			searchRange(nums, target, findResult + 1, right, result);
		}
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