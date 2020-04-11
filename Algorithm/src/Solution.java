class Solution {
    public int findString(String[] words, String s) {
        return search(words, 0, words.length, s);
    }

    private int search(String[] words, int left, int right, String target) {
        if (left >= right) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (words[mid].equals(target)) {
            return mid;
        }
        if (words[mid].isEmpty()) {
            int leftResult = search(words, left, mid, target);
            if (leftResult != -1) {
                return leftResult;
            }
            return search(words, mid + 1, right, target);
        }
        int compareResult = compare(target, words[mid]);
        if (compareResult > 0) {
            return search(words, mid + 1, right, target);
        } else {
            return search(words, left, mid, target);
        }
    }

    private int compare(String s1, String s2) {
        int i = 0;
        while (i < s1.length() && i < s2.length()) {
            char a = s1.charAt(i);
            char b = s2.charAt(i);
            if (a != b) {
                return a - b;
            }
            i++;
        }
        if (i == s1.length() && i != s2.length()) {
            return -1;
        }
        if (i != s1.length() && i == s2.length()) {
            return 1;
        }
        return 0;
    }
}