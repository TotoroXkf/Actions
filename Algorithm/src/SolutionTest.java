import org.junit.Test;

import java.util.List;

import struct.ListNode;

public class SolutionTest {
    private Solution solution = new Solution();
    private Case testCase = new Case();

    @Test
    public void test() {
        ListNode head = testCase.createLinkedList(new int[]{1, 4, 3, 2, 5, 2});
        solution.partition(head, 3);
        testCase.matchLinkedList(head, testCase.createLinkedList(new int[]{1, 2, 2, 4, 3, 5}));
    }
} 
