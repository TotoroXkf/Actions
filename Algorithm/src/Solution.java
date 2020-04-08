import java.util.Arrays;

class Solution {
    public int pileBox(int[][] box) {
        // 排序
        Arrays.sort(box, (a, b) -> {
            // 宽度从小到大
            if (a[0] != b[0]) {
                return Integer.compare(a[0], b[0]);
            }
            // 宽度一样排序长度
            if (a[1] != b[1]) {
                return Integer.compare(b[1], a[1]);
            }
            // 长度一样排序高度
            if (a[2] != b[2]) {
                return Integer.compare(b[2], a[2]);
            }
            // 全都一样就是相等的
            return 0;
        });
        int[] dp = new int[box.length];
        // 初始值，就是第一个的高度
        dp[0] = box[0][2];
        int result = dp[0];
        for (int i = 1; i < box.length; i++) {
            // 记录当前位置的高度和长度
            int max = 0;
            int currentLength = box[i][1];
            int currentHeight = box[i][2];
            // 循环比较出当前位置之前的，能被叠加上来的最大的结果值
            for (int j = 0; j < i; j++) {
                // 这里宽度已经比较过了
                // 如果说当前位置可以装下，就看一看这个位置的dp值
                if (currentLength > box[j][1] && currentHeight > box[j][2]) {
                    max = Math.max(max, dp[j]);
                }
            }
            // 计算当前位置可以得到的最大值
            dp[i] = max + currentHeight;
            // 动态比较全局的最大值。因为最优解不一定包含了最后一个箱子
            result = Math.max(result, max + currentHeight);
        }
        return result;
    }
}
