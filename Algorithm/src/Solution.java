class Solution {
    public String replaceSpaces(String s, int length) {
        StringBuilder stringBuilder = new StringBuilder();
        String space = "%20";
        int i = 0;
        int len = 0;
        while (i < s.length() && len < length) {
            if (s.charAt(i) == ' ') {
                stringBuilder.append(space);
            } else {
                stringBuilder.append(s.charAt(i));
            }
            len++;
            i++;
        }
        return stringBuilder.toString();
    }
}