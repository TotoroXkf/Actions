import org.junit.Test;


public class SolutionTest {
    private Solution solution = new Solution();

    @Test
    public void test() {
        int[] array = new int[]{1, 2, 5, 9, 5, 9, 5, 5, 5};
        int result = solution.majorityElement(array);
        assert result == 5;

        array = new int[]{3, 2};
        result = solution.majorityElement(array);
        assert result == -1;

        array = new int[]{2, 2, 1, 1, 1, 2, 2};
        result = solution.majorityElement(array);
        assert result == 2;

        array = new int[]{3, 2, 3};
        result = solution.majorityElement(array);
        assert result == 3;
    }
} 
