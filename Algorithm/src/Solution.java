class Solution {
    public boolean validPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        boolean hasChange = false;
        while (i < j) {
            if (s.charAt(i) == s.charAt(j)) {
                i++;
                j--;
                continue;
            }
            if (hasChange) {
                return false;
            }
            if (j - i == 1) {
                return true;
            }
            if (s.charAt(i) == s.charAt(j - 1)) {
                j--;
                hasChange = true;
            } else if (s.charAt(j) == s.charAt(i + 1)) {
                i++;
                hasChange = true;
            } else {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String str = "ebcbbececabbacecbbcbe";
        System.out.println(solution.validPalindrome(str));
    }
}