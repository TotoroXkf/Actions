class Solution {
    /**
     * 基础的思路是找到word1的所有位置和word2的所有位置
     * 两个数组按照丛小到大排序。然后双指针遍历两个数组
     * 小的指针往前移动，每次移动就比较记录新的结果
     * 这个题可以再这个基础上再优化一步。不用记录这个位置
     * 从头遍历word数组，然后遇到word1或者word2的时候更新index的值，然后立刻比较当前的距离
     * 这样其实就是模拟了上面的基础思路，并且不用存储
     */
    public int findClosest(String[] words, String word1, String word2) {
        int result = Integer.MAX_VALUE;
        int index1 = -1, index2 = -1;
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            if (word.equals(word1)) {
                index1 = i;
                if (index2 != -1) {
                    result = Math.min(Math.abs(index2 - index1), result);
                }
                if (result == 1) {
                    break;
                }
            } else if (word.equals(word2)) {
                index2 = i;
                if (index1 != -1) {
                    result = Math.min(Math.abs(index2 - index1), result);
                }
                if (result == 1) {
                    break;
                }
            }
        }
        return result;
    }
}