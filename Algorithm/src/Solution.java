class Solution {
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        if (s1.isEmpty()) {
            return true;
        }
        int i = 0;
        int j = s2.length() - 1;
        while (j > -1) {
            if (s1.charAt(i) != s2.charAt(j)) {
                j--;
                continue;
            }
            if (compare(s1, s2, i, j, i + (s2.length() - j), s2.length())) {
                if (compare(s1, s2, i + (s2.length() - j), 0, s1.length(), j)) {
                    return true;
                }
            }
            j--;
        }
        return false;
    }

    private boolean compare(String s1, String s2, int start1, int start2, int end1, int end2) {
        int i = start1;
        int j = start2;
        while (i < end1 && j < end2 && s1.charAt(i) == s2.charAt(j)) {
            i++;
            j++;
        }
        return i == end1 && j == end2;
    }

    public static void main(String[] args) {
        String s1 = "aba";
        String s2 = "bab";
        Solution solution = new Solution();
        System.out.println(solution.isFlipedString(s1, s2));
    }
}