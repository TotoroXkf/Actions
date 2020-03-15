import java.util.ArrayList;
import java.util.LinkedList;

class Solution {
    public ListNode[] listOfDepth(TreeNode root) {
        ArrayList<ListNode> resultList = new ArrayList<>();
        LinkedList<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        ListNode head = null;
        ListNode tail = null;
        while (!queue.isEmpty()) {
            TreeNode treeNode = queue.removeFirst();
            if (treeNode == null) {
                if (!queue.isEmpty()) {
                    queue.add(null);
                    resultList.add(head);
                    head = null;
                    tail = null;
                } else {
                    resultList.add(head);
                }
                continue;
            }
            if (head == null) {
                head = new ListNode(treeNode.val);
                tail = head;
            } else {
                tail.next = new ListNode(treeNode.val);
                tail = tail.next;
            }
            if (treeNode.left != null) {
                queue.add(treeNode.left);
            }
            if (treeNode.right != null) {
                queue.add(treeNode.right);
            }
        }
        ListNode[] resultArray = new ListNode[resultList.size()];
        return resultList.toArray(resultArray);
    }
}