import org.junit.Test;

import java.util.List;

import struct.ListNode;

public class SolutionTest {
    private Solution solution = new Solution();
    private Case testCase = new Case();

    @Test
    public void test() {
        String str1 = "A";
        String str2 = "A";
        assert solution.isScramble(str1, str2);

        str1 = "A";
        str2 = "B";
        assert !solution.isScramble(str1, str2);

        str1 = "AB";
        str2 = "BA";
        assert solution.isScramble(str1, str2);

        str1 = "AB";
        str2 = "AC";
        assert !solution.isScramble(str1, str2);

        str1 = "ABCD";
        str2 = "ABDC";
        assert solution.isScramble(str1, str2);

        str1 = "ABCD";
        str2 = "ABDC";
        assert solution.isScramble(str1, str2);

        str1 = "great";
        str2 = "rgeat";
        assert solution.isScramble(str1, str2);

        str1 = "great";
        str2 = "rgtae";
        assert solution.isScramble(str1, str2);

        str1 = "abcde";
        str2 = "caebd";
        assert !solution.isScramble(str1, str2);

        str1 = "abc";
        str2 = "bca";
        assert solution.isScramble(str1, str2);
    }
} 
