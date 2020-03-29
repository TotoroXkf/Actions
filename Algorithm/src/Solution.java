import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * 这个题一看就应该知道是dfs的问题
     * 举例来说。比如 [a,b,c,d,e]
     * 他的最后结果应该是 [a,x,x,x,x]的所有解加上b,c,d,e等站在开头的所有解
     * 往下一位上又可以逐步的递归下去
     * 所以这个题的关键就是，当遍历到一个位置，从这个位置向前循环，遇到一位，就把遇到的字符和当前的字符交换位置，模拟这个字符站在首位的情况
     * 然后递归下一位的子集
     * 等到子集全部递归完毕回来，再把这两个字符换回来即可
     */
    public String[] permutation(String s) {
        List<String> list = new ArrayList<>();
        permutation(s.toCharArray(), list, 0);
        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }

    private void permutation(char[] c, List<String> result, int index) {
        if (index == c.length) {
            result.add(new String(c));
        }
        for (int i = index; i < c.length; i++) {
            // 交换字符的位置，模拟这个字符在首位
            swap(c, index, i);
            // 递归求解子集
            permutation(c, result, index + 1);
            // 把两个字符换回来
            swap(c, index, i);
        }
    }

    private void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
}