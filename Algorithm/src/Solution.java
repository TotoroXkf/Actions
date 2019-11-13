import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
	public List<List<Integer>> combinationSum(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> currentList = new ArrayList<>();
		find(candidates, target, 0, 0, currentList, result);
		return result;
	}
	
	private void find(int[] candidates, int target, int start, int currentValue, List<Integer> currentList, List<List<Integer>> result) {
		if (currentValue > target) {
			return;
		}
		if (currentValue == target) {
			List<Integer> list = new ArrayList<>(currentList);
			result.add(list);
		}
		while (start < candidates.length && candidates[start] <= target) {
			int value = currentValue + candidates[start];
			currentList.add(candidates[start]);
			find(candidates, target, start, value, currentList, result);
			currentList.remove(currentList.size() - 1);
			start++;
		}
	}
}