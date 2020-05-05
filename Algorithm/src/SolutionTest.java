import org.junit.Test;


public class SolutionTest {
    private Solution solution = new Solution();

    @Test
    public void test() {
        int[] square1 = new int[]{-1, -1, 2};
        int[] square2 = new int[]{-1, 10, 2};
        double[] result = solution.cutSquares(square1, square2);
        assert result[0] == 0;
        assert result[1] == -1;
        assert result[2] == 0;
        assert result[3] == 12;

        square1 = new int[]{-1, -1, 2};
        square2 = new int[]{0, -1, 2};
        result = solution.cutSquares(square1, square2);
        assert result[0] == -1;
        assert result[1] == 0;
        assert result[2] == 2;
        assert result[3] == 0;

        square1 = new int[]{-1, -1, 3};
        square2 = new int[]{-1, 10, 2};
        result = solution.cutSquares(square1, square2);
        assert result[0] == -0.04762;
        assert result[1] == 12.00000;
        assert result[2] == 0.57143;
        assert result[3] == -1.00000;
    }
} 
