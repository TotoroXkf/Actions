import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    /**
     * 在原有基础上面的进阶，增加了重复的字符。按照过往的经验来看，还是先排序
     * 还是交换首位的逻辑。这里增加一些过滤的条件
     * 如果说当前这个字符和它前面的字符是一样的，就不把它换到首位去。比如 [a,b,b] ,首位a就不应该和最后一个b交换
     * 另外就是，如果当前字符和要交换的字符是一样的，也不应该换 比如 [v,a,j,a] 第一个a和第2个a就不应该交换
     * 其他的和不带重复元素的就一样了
     */
    public String[] permutation(String s) {
        char[] c = s.toCharArray();
        // 先排序
        Arrays.sort(c);
        List<String> list = new ArrayList<>();
        permutation(c, 0, list);
        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }

    private void permutation(char[] c, int index, List<String> result) {
        if (index == c.length) {
            result.add(new String(c));
        }
        for (int i = index; i < c.length; i++) {
            // 两个过滤的条件
            if (i > index && (c[i] == c[i - 1])) {
                continue;
            }
            swap(c, index, i);
            permutation(c, index + 1, result);
            swap(c, index, i);
        }
    }

    private void swap(char[] c, int i, int j) {
        char temp = c[i];
        c[i] = c[j];
        c[j] = temp;
    }
}