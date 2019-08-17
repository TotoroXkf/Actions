class Solution {
    public String longestCommonPrefix(String[] strings) {
        if (strings == null || strings.length == 0) {
            return "";
        }
        int count = 0;
        boolean label = true;
        while (count < strings[0].length() && label) {
            char c = strings[0].charAt(count);
            for (String s : strings) {
                if (count >= s.length() || s.charAt(count) != c) {
                    label = false;
                    count--;
                    break;
                }
            }
            count++;
        }
        return strings[0].substring(0, count);
    }
}