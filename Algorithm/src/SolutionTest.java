import org.junit.Test;

import struct.TreeNode;

public class SolutionTest {
    private Solution solution = new Solution();
    private Case testCase = new Case();

    @Test
    public void test() {
        int a = -2;
        int b = 2;
        System.out.println(Integer.toBinaryString(a));
        System.out.println(a ^ b);
    }
} 
