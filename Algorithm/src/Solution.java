import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> BSTSequences(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            result.add(new ArrayList<>());
            return result;
        }
        List<List<Integer>> leftResult = BSTSequences(root.left);
        List<List<Integer>> rightResult = BSTSequences(root.right);
        for (List<Integer> list1 : leftResult) {
            for (List<Integer> list2 : rightResult) {
                result.addAll(generate(list1, list2));
            }
        }
        for (List<Integer> list : result) {
            list.add(0, root.val);
        }
        if (result.isEmpty()) {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            result.add(list);
        }
        return result;
    }

    public List<List<Integer>> generate(List<Integer> list1, List<Integer> list2) {
        List<List<Integer>> result = new ArrayList<>();
        if (list1.isEmpty() || list2.isEmpty()) {
            if (!list2.isEmpty()) {
                result.add(new ArrayList<>(list2));
            }
            if (!list1.isEmpty()) {
                result.add(new ArrayList<>(list1));
            }
            return result;
        }
        List<List<Integer>> answer = generate(list1.subList(1, list1.size()), list2);
        for (List<Integer> list : answer) {
            list.add(0, list1.get(0));
        }
        result.addAll(answer);
        answer = generate(list1, list2.subList(1, list2.size()));
        for (List<Integer> list : answer) {
            list.add(0, list2.get(0));
        }
        result.addAll(answer);
        return result;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(1);
        root.right = new TreeNode(3);
        Solution solution = new Solution();
        List<List<Integer>> result = solution.BSTSequences(root);
        System.out.println(result);
    }
}