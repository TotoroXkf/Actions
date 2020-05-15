import org.junit.Test;

import struct.TreeNode;

public class SolutionTest {
    private Solution solution = new Solution();
    private Case testCase = new Case();

    @Test
    public void test() {
        String pattern = "abba";
        String value = "dogcatcatdog";
        boolean result = solution.patternMatching(pattern, value);
        assert result;

        pattern = "abba";
        value = "dogcatcatfish";
        result = solution.patternMatching(pattern, value);
        assert !result;

        pattern = "aaaa";
        value = "dogcatcatdog";
        result = solution.patternMatching(pattern, value);
        assert !result;

        pattern = "abba";
        value = "dogdogdogdog";
        result = solution.patternMatching(pattern, value);
        assert result;
    }
} 
