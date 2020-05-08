import org.junit.Test;

import struct.TreeNode;

public class SolutionTest {
    private Solution solution = new Solution();
    private Case testCase = new Case();

    @Test
    public void test() {
        String text = "[4,2,5,1,3,null,6,0]";
        TreeNode treeNode1 = testCase.createTree(text);
        TreeNode treeNode2 = solution.convertBiNode(treeNode1);
        treeNode1 = testCase.createTree("[0,null,1,null,2,null,3,null,4,null,5,null,6]");
        testCase.matchTree(treeNode1, treeNode2);
    }
} 
