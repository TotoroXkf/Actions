class Solution {
    public boolean isScramble(String s1, String s2) {
        char[] str1 = s1.toCharArray();
        char[] str2 = s2.toCharArray();
        return check(str1, 0, str1.length, str2, 0, str2.length);
    }

    private boolean check(char[] str1, int start1, int end1, char[] str2, int start2, int end2) {
        if (end1 - start1 == 1) {
            return str1[start1] == str2[start2];
        }
        if (end2 - start2 == 2) {
            return (str1[start1] == str2[start2] && str1[start1 + 1] == str2[start2 + 1]) ||
                (str1[start1] == str2[start2 + 1] && str1[start1 + 1] == str2[start2]);
        }
        for (int i = start1; i < end1 - 1; i++) {
            int leftLen = i - start1 + 1;
            int rightLen = end1 - leftLen;
//            boolean checkResult = check(str1, start1, start1 + leftLen, str2, start2, start2 + leftLen);
//            if (checkResult) {
//                checkResult = check(str1, start1 + leftLen, end1, str2, start2 + leftLen, end2);
//                if (checkResult) {
//                    return true;
//                }
//            }
//            checkResult = check(str1, start1, start1 + leftLen, str2, end2 - rightLen, end2);
//            if (checkResult) {
//                checkResult = check(str1, start1 + leftLen, end1, str2, start2, start2 + leftLen);
//                if (checkResult) {
//                    return true;
//                }
//            }
        }
        return false;
    }
}