class Solution {
    public int strStr(String s, String p) {
        if (p.isEmpty()) {
            return 0;
        }
        return 0;
    }

    private int[] getNext(String p) {
        int[] result = new int[p.length()];
        char[] chars = p.toCharArray();
        result[0] = -1;
        for (int i = 1; i < chars.length - 1; i++) {
        }
        return result;
    }
}