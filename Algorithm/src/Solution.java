class Solution {
    /**
     * 寻找儿叉搜索树的下一个节点，也是面试经常会问到的问题
     * 一般来说，二叉树无外乎就是递归左子树和右子树
     * 假设当前的节点比目标节点值大
     * 说明目标节点值在当前节点的左子树上。那么最终的结果就一定出在左子树上，或者是当前节点
     * 那就可以递归左子树，若果有结果，那么一定是比当前节点更加靠近目标节点值的。返回即可。如果无结果，当前节点顺理成章的成为了后继节点
     * 若果是当前节点比目标节点小，那就说明目标节点在右子树上
     * 无论如何，后继节点都喝当前节点没有关系，直接递归右子树即可
     */
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (root == null || p == null) {
            return null;
        }
        if (root.val > p.val) {
            TreeNode leftResult = inorderSuccessor(root.left, p);
            return leftResult != null ? leftResult : root;
        }
        return inorderSuccessor(root.right, p);
    }
}