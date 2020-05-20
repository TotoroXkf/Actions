import org.junit.Test;

import java.util.List;

public class SolutionTest {
    private Solution solution = new Solution();
    private Case testCase = new Case();

    @Test
    public void test() {
        int k = 2;
        List<String> result = solution.printKMoves(k);
        testCase.matchArray(result.toArray(), new Object[]{"_X", "LX"});

        k = 3;
        result = solution.printKMoves(k);
        testCase.matchArray(result.toArray(), new Object[]{"UX", "XX"});

        k = 5;
        result = solution.printKMoves(k);
        testCase.matchArray(result.toArray(), new Object[]{"_U", "X_", "XX"});

        k = 0;
        result = solution.printKMoves(k);
        testCase.matchArray(result.toArray(), new Object[]{"R"});

        k = 10;
        result = solution.printKMoves(k);
        testCase.matchArray(result.toArray(), new Object[]{"L_X", "XXX", "XX_"});
    }
} 
