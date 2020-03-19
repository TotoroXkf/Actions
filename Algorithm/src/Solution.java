class Solution {
    /**
     * 寻找共同的祖先节点。这个题的定义，节点本身也可以算祖先节点。并且所有的节点都是唯一的
     * 那么久考虑递归的形式。
     * 如果当前节点是目标节点
     * 那么就可以直接返回当前节点了。原因就在于。如果另外的目标节点在左子树或者右子树上，最终的结果还是自身。无所谓
     * 若果不在，那么就是在当前节点还要前面的节点了。也是不用递归下面的左右子树的。返回自身，代表了告诉更上级的节点，找到了相应的节点
     * 由于所有值都是唯一的。对于上一层递归来说，子树递归结果返回不为null,说明子树包含了目标节点，或者就是包含了最后的答案
     * <p>
     * 如果当前节点不是目标节点
     * 那么就可以分情况
     * <p>
     * 左边和右边都是null
     * 这种情况说明左右子树都是无效的，那么当前节点也不是目标节点，直接返回null
     * <p>
     * 左边是null，右边不是null
     * 那就说明右边包含了目标节点值或者就是最后的答案，返回右边
     * <p>
     * 右边不是null，左边是null
     * 同上，返回左边
     * <p>
     * 左右都不是null
     * 这就只有一个可能。左边含有目标节点，右边也含有。同时遇到了当前节点。当前节点就是公共祖先。返回自身
     * 这样的话就讨论到了所有的情况。答案必在其中
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }
        if (root.val == p.val || root.val == q.val) {
            return root;
        }
        TreeNode leftResult = lowestCommonAncestor(root.left, p, q);
        TreeNode rightResult = lowestCommonAncestor(root.right, p, q);
        if (leftResult != null && rightResult != null) {
            return root;
        }
        if (leftResult != null) {
            return leftResult;
        }
        return rightResult;
    }
}