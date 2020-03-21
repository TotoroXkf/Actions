class Solution {
    /**
     * 比较典型的二叉树问题
     * 比较当前节点相同，则递归两个树的左右子树去比较
     * 当前节点不同，递归第一颗树的左子树和第二颗树，不匹配，在递归第一课树的右子树和第二颗树
     */
    public boolean checkSubTree(TreeNode t1, TreeNode t2) {
        if (t1 == null && t2 == null) {
            return true;
        }
        if (t1 == null || t2 == null) {
            return false;
        }
        if (t1.val == t2.val) {
            return checkSubTree(t1.left, t2.left) && checkSubTree(t1.right, t2.right);
        }
        return checkSubTree(t1.left, t2) || checkSubTree(t1.right, t2);
    }
}