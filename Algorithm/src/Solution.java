import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    /**
     * 主要思路是滑动窗口法
     * 提前使用hashmap记录好每个单词的出现的次数
     * 维护一个长度变化的窗口，窗口的内容每一次计算一个单词的长度
     * 控制窗口的左右边界并且比对窗口内的内容得。当窗口与单词总长度一致的时候，窗口的左边界就是答案
     * 窗口的选择有一个小技巧。因为窗口内的内容都是按照单词的长度一个一个取的
     * 所以，假设单词长度为n，可能的窗口只可能是0作为开始，或者1作为开始，或者直到n-1作为开始。
     * 一旦到作为开始其实就和0作为开始重合了。或者说0作为开始包含了n作为开始的情况
     */
    public List<Integer> findSubstring(String s, String[] words) {
        if (words.length == 0 || words[0].isEmpty()) {
            return new ArrayList<>();
        }
        HashMap<String, Integer> hashMap = new HashMap<>();
        // 记录单词出现的次数
        for (String str : words) {
            hashMap.put(str, hashMap.getOrDefault(str, 0) + 1);
        }
        ArrayList<Integer> result = new ArrayList<>();
        int wordLen = words[0].length();
        int totalLen = wordLen * words.length;
        // 窗口的选择策略
        for (int i = 0; i < wordLen; i++) {
            int left = i;
            int right = i;
            HashMap<String, Integer> temp = new HashMap<>();
            // 扩展窗口
            // 策略就是每一次一定移动右窗口一个单词的位置，然后比较看左窗口的变化情况
            while (right + wordLen <= s.length()) {
                // 移动右窗口一个单位
                String currentWord = s.substring(right, right + wordLen);
                right += wordLen;
                // 记录这个单词的出现次数，用于和实际的做对比
                temp.put(currentWord, temp.getOrDefault(currentWord, 0) + 1);

                // 如果发现这个单词根本就不在单词数组里面，直接抛弃当前的窗口，把右边节直接当成左边界开启新的窗口
                if (!hashMap.containsKey(currentWord)) {
                    left = right;
                    temp.clear();
                    continue;
                }

                // 添加完右边新遇到的单词，改变左窗口适配单词数组
                while (temp.get(currentWord) > hashMap.getOrDefault(currentWord, 0)) {
                    // 逐步丢弃匹配失败位置左边的单词，直到抛弃到可以继续向右扩展窗口的位置
                    String removeWord = s.substring(left, left + wordLen);
                    temp.put(removeWord, temp.get(removeWord) - 1);
                    left += wordLen;
                }
                if (right-left == totalLen) {
                    result.add(left);
                }
            }
        }
        return result;
    }
}