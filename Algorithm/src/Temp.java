import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Temp {
    public List<List<Integer>> threeSum(int[] nums, int target, int start) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        Arrays.sort(nums);
        int i = start;
        while (i < nums.length) {
            int subTarget = target - nums[i];
            List<List<Integer>> twoValue = twoSum(nums, subTarget, i + 1);
            if (!twoValue.isEmpty()) {
                for (List<Integer> list : twoValue) {
                    list.add(nums[i]);
                    result.add(list);
                }
            }
            i++;
            while (i < nums.length && nums[i] == nums[i - 1]) i++;
        }
        return result;
    }

    public List<List<Integer>> twoSum(int[] nums, int target, int start) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        int i = start;
        int j = nums.length - 1;
        while (i < j) {
            int value = nums[i] + nums[j];
            if (value == target) {
                ArrayList<Integer> answer = new ArrayList<>();
                answer.add(nums[i]);
                answer.add(nums[j]);
                result.add(answer);
            }
            if (value <= target) {
                while (i < j && nums[i] == nums[i + 1]) i++;
                i++;
            }
            if (value >= target) {
                while (i < j && nums[j] == nums[j - 1]) j--;
                j--;
            }
        }
        return result;
    }
}
