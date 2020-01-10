class Solution {
    public int strStr(String s, String p) {
        if (p.isEmpty()) {
            return 0;
        }
        int[] next = getNext(p);
        int i = 0;
        int j = 0;
        int result = -1;
        while (i < s.length() && j < p.length()) {
            if (s.charAt(i) == p.charAt(j)) {
                i++;
                j++;
                continue;
            }
            j = next[j];
            if (j == -1) {
                i++;
                j = 0;
            }
        }
        if (j == p.length()) {
            result = i - p.length();
        }
        return result;
    }

    private int[] getNext(String p) {
        int[] result = new int[p.length()];
        char[] chars = p.toCharArray();
        result[0] = -1;
        for (int i = 0; i < chars.length - 1; i++) {
            int j = i;
            int nextIndex = result[j];
            while (nextIndex != -1) {
                if (chars[nextIndex] == chars[i]) {
                    result[i + 1] = result[j] + 1;
                    break;
                }
                j = nextIndex;
                nextIndex = result[j];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String s = "aabcaabaaa";
        String p = "aaba";
        System.out.println(s.indexOf(p));
        System.out.println(new Solution().strStr(s, p));
    }
}