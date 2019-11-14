import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
	public List<List<Integer>> combinationSum2(int[] candidates, int target) {
		Arrays.sort(candidates);
		List<List<Integer>> result = new ArrayList<>();
		List<Integer> currentList = new ArrayList<>();
		find(candidates, target, 0, currentList, result);
		return result;
	}
	
	private void find(int[] candidates, int target, int start, List<Integer> currentList, List<List<Integer>> result) {
		if (target == 0) {
			List<Integer> list = new ArrayList<>(currentList);
			result.add(list);
		}
		while (start < candidates.length && candidates[start] <= target) {
			int value = target - candidates[start];
			if (value < 0) {
				return;
			}
			currentList.add(candidates[start]);
			find(candidates, value, start + 1, currentList, result);
			currentList.remove(currentList.size() - 1);
			while (start < candidates.length-1 && candidates[start] == candidates[start + 1]) {
				start++;
			}
			start++;
		}
	}
}