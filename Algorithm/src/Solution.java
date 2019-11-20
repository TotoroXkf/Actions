import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

class Solution {
	public List<List<Integer>> permuteUnique(int[] nums) {
		Arrays.sort(nums);
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> currentList = new ArrayList<>();
		HashMap<Integer, Boolean> hashMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			hashMap.put(i, true);
		}
		handle(nums, currentList, hashMap, result);
		return result;
	}
	
	private void handle(int[] nums, List<Integer> currentList, HashMap<Integer, Boolean> hashMap, List<List<Integer>> result) {
		if (currentList.size() == nums.length) {
			List<Integer> newResult = new ArrayList<>(currentList);
			result.add(newResult);
			return;
		}
		int sameValue = Integer.MAX_VALUE;
		for (int i = 0; i < nums.length; i++) {
			if (hashMap.get(i) && nums[i] != sameValue) {
				currentList.add(nums[i]);
				sameValue = nums[i];
				hashMap.put(i, false);
				handle(nums, currentList, hashMap, result);
				hashMap.put(i, true);
				currentList.remove(currentList.size() - 1);
			}
		}
	}
	
	public static void main(String[] args) {
		new Solution().permuteUnique(new int[]{3, 3, 0, 3});
	}
}