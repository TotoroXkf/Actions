class Solution {
    /**
     * 判断二叉搜索树是面试经常遇到的问题
     * 其实就是设置左右边界，看当前的节点是不是在这个边界里面即可
     */
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    private boolean isValidBST(TreeNode root, long atLeast, long atMost) {
        if (root == null) {
            return true;
        }
        if (root.val <= atLeast || root.val >= atMost) {
            return false;
        }
        return isValidBST(root.left, atLeast, root.val) && isValidBST(root.right, root.val, atMost);
    }
}