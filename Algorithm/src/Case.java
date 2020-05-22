import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import struct.ListNode;
import struct.TreeNode;

public class Case {
    public void matchArray(Object[] array1, Object[] array2) {
        if (array1 == null && array2 == null) {
            return;
        }
        assert array1 != null && array2 != null;
        assert array1.length == array2.length;
        for (int i = 0; i < array1.length; i++) {
            assert array1[i].equals(array2[i]);
        }
    }

    public int[][] createMatrix(String text) {
        List<int[]> list = new ArrayList<>();
        StringBuilder stringBuilder = new StringBuilder(text);
        stringBuilder.deleteCharAt(0);
        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        int start = 0;
        int end = stringBuilder.indexOf("]");
        while (start != -1) {
            String subString = stringBuilder.substring(start + 1, end);
            String[] textArray = subString.split(",");
            int[] intArray = new int[textArray.length];
            for (int i = 0; i < textArray.length; i++) {
                textArray[i] = textArray[i].trim();
                intArray[i] = Integer.parseInt(textArray[i]);
            }
            list.add(intArray);
            stringBuilder.delete(start, end + 1);
            start = stringBuilder.indexOf("[");
            end = stringBuilder.indexOf("]");
        }
        int[][] result = new int[list.size()][];
        list.toArray(result);
        return result;
    }

    public void matchMatrix(int[][] matrix1, int[][] matrix2) {
        if (matrix1 == null && matrix2 == null) {
            return;
        }
        assert matrix1 != null && matrix2 != null;
        assert matrix1.length == matrix2.length;
        assert matrix1[0].length == matrix2[0].length;
        for (int i = 0; i < matrix1.length; i++) {
            for (int j = 0; j < matrix2.length; j++) {
                assert matrix1[i][j] == matrix2[i][j];
            }
        }
    }

    public ListNode createLinkedList(int[] array) {
        ListNode preHead = new ListNode(0);
        ListNode preNode = preHead;
        for (int value : array) {
            preNode.next = new ListNode(value);
            preNode = preNode.next;
        }
        return preHead.next;
    }

    public void matchLinkedList(ListNode root1, ListNode root2) {
        if (root1 == null && root2 == null) {
            return;
        }
        assert root1 != null && root2 != null;
        while (root1 != null && root2 != null) {
            assert root1.val == root2.val;
            root1 = root1.next;
            root2 = root2.next;
        }
    }

//    public TreeNode createTree(String text) {
//        StringBuilder stringBuilder = new StringBuilder(text);
//        stringBuilder.deleteCharAt(0);
//        stringBuilder.deleteCharAt(stringBuilder.length() - 1);
//        String[] textArray = stringBuilder.toString().split(",");
//        LinkedList<Integer> indexQueue = new LinkedList<>();
//        LinkedList<TreeNode> nodeQueue = new LinkedList<>();
//        TreeNode root = new TreeNode(Integer.parseInt(textArray[0]));
//        indexQueue.addLast(0);
//        nodeQueue.addLast(root);
//        while (!nodeQueue.isEmpty()) {
//            TreeNode node = nodeQueue.removeFirst();
//            int index = indexQueue.removeFirst();
//
//            int leftIndex = (index + 1) * 2 - 1;
//            if (leftIndex < textArray.length) {
//                String value = textArray[leftIndex];
//                if (!value.equals("null")) {
//                    TreeNode leftNode = new TreeNode(Integer.parseInt(value));
//                    node.left = leftNode;
//                    nodeQueue.addFirst(leftNode);
//                    indexQueue.addFirst(leftIndex);
//                }
//            }
//
//            int rightIndex = (index + 1) * 2;
//            if (rightIndex < textArray.length) {
//                String value = textArray[rightIndex];
//                if (!value.equals("null")) {
//                    TreeNode rightNode = new TreeNode(Integer.parseInt(value));
//                    node.right = rightNode;
//                    nodeQueue.addFirst(rightNode);
//                    indexQueue.addFirst(rightIndex);
//                }
//            }
//        }
//        return root;
//    }

    public void matchTree(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) {
            return;
        }
        assert root1 != null && root2 != null;
        assert root1.val == root2.val;
        matchTree(root1.left, root2.left);
        matchTree(root1.right, root2.right);
    }
}
