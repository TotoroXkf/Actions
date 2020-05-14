import org.junit.Test;

import struct.TreeNode;

public class SolutionTest {
    private Solution solution = new Solution();
    private Case testCase = new Case();

    @Test
    public void test() {
        int[] array = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] result = solution.subSort(array);
        testCase.matchArray(result, new int[]{-1, -1});

        array = new int[]{1, 2, 4, 7, 10, 11, 7, 12, 6, 7, 16, 18, 19};
        result = solution.subSort(array);
        testCase.matchArray(result, new int[]{3, 9});

        array = new int[]{9, 8, 7, 5, 3, 1};
        result = solution.subSort(array);
        testCase.matchArray(result, new int[]{0, array.length - 1});
    }
} 
