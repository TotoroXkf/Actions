class Solution {
    /**
     * 二叉树的经典问题了
     * 一般二叉问题都可以使用递归比较好的解决
     * 由于数组排序了。所以中间的节点就是根节点。
     * 然后将左右两边放下去递归即可
     */
    public TreeNode sortedArrayToBST(int[] nums) {
        return sortedArrayToBST(nums, 0, nums.length);
    }

    public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left >= right) {
            return null;
        }
        int mid = (left + right) / 2;
        TreeNode treeNode = new TreeNode(nums[mid]);
        treeNode.left = sortedArrayToBST(nums, left, mid);
        treeNode.right = sortedArrayToBST(nums, mid + 1, right);
        return treeNode;
    }
}