import struct.TreeNode;

class Solution {
    public TreeNode convertBiNode(TreeNode node) {
        return convert(node)[0];
    }

    private TreeNode[] convert(TreeNode node) {
        TreeNode[] result = new TreeNode[]{node, node};
        if (node.right != null) {
            TreeNode[] nodes = convert(node.right);
            node.right = nodes[0];
            result[1] = nodes[1];
        }
        if (node.left != null) {
            TreeNode[] nodes = convert(node.left);
            nodes[1].right = node;
            result[0] = nodes[0];
        }
        return result;
    }
}