import java.util.HashMap;

class Solution {
    /**
     * 算是比较经典的面试题了。思路如下
     * 走到一个节点，将当前节点的值加入从根节点走过来的累加值。将累加值记入到HashMap里面
     * 然后，将当前的累加值减去目标值，得到一个数字。去HashMap里面查有没有这个数
     * 如果有，说明从根节点到当前节点的长路径中，至少存在一条子路径的和，将它拿掉之后剩下的路径和等于Target
     * 具体的画个图试一下就明白了
     * 然后递归左右子树继续求和
     * 当从当前节点返回的时候，将HashMap的值减少1即可
     */
    public int pathSum(TreeNode root, int sum) {
        return pathSum(root, sum, 0, new HashMap<>());
    }

    public int pathSum(TreeNode root, int targetSum, int currentSum, HashMap<Integer, Integer> pathCount) {
        if (root == null) {
            return 0;
        }
        int result = 0;
        // 累加当前节点的和
        currentSum += root.val;
        // 计算出需要砍掉的值
        int prePathValue = currentSum - targetSum;
        // 如果前面的路径和存在，就是有解的
        result += pathCount.getOrDefault(prePathValue, 0);
        // 如果等于0，说明累加到当前刚好合适，结果+1
        if (prePathValue == 0) {
            result++;
        }
        // 递归左右
        pathCount.put(currentSum, pathCount.getOrDefault(currentSum, 0) + 1);
        result += pathSum(root.left, targetSum, currentSum, pathCount);
        result += pathSum(root.right, targetSum, currentSum, pathCount);
        pathCount.put(currentSum, pathCount.get(currentSum) - 1);
        return result;
    }
}