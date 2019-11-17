import java.util.*;

class Solution {
	public List<List<Integer>> permute(int[] nums) {
		HashMap<Integer, Boolean> hashMap = new HashMap<>();
		for (int i = 0; i < nums.length; i++) {
			hashMap.put(i, true);
		}
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> currentList = new ArrayList<>();
		handle(nums, hashMap, currentList, result);
		return result;
	}
	
	private void handle(int[] nums, HashMap<Integer, Boolean> hashMap, List<Integer> currentList, List<List<Integer>> result) {
		if (currentList.size() == nums.length) {
			List<Integer> newResult = new ArrayList<>(currentList);
			result.add(newResult);
		}
		
		for (int i = 0; i < nums.length; i++) {
			if (hashMap.get(i)) {
				currentList.add(nums[i]);
				hashMap.put(i, false);
				handle(nums, hashMap, currentList, result);
				hashMap.put(i, true);
				currentList.remove(currentList.size() - 1);
			}
		}
	}
}