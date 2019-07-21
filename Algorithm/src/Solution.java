import java.util.HashMap;

class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s.isEmpty()) {
            return 0;
        }
        HashMap<Character, Integer> hashMap = new HashMap<>();
        int startIndex = 0;
        int result = 1;
        hashMap.put(s.charAt(0), 0);
        for (int i = 1; i < s.length(); i++) {
            if (!hashMap.containsKey(s.charAt(i))) {
                result = Math.max(result, i - startIndex + 1);
                hashMap.put(s.charAt(i),i);
                continue;
            }
            int targetIndex = hashMap.get(s.charAt(i));
            for (int j = startIndex; j < targetIndex; j++) {
                hashMap.remove(s.charAt(j));
            }
            hashMap.put(s.charAt(i),i);
            startIndex = targetIndex+1;
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "pwwkew";
        System.out.println(new Solution().lengthOfLongestSubstring(s));
    }
}