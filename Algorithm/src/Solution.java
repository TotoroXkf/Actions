class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0) {
            return false;
        }
        StringBuilder stringBuilder = new StringBuilder(""+x);
        return stringBuilder.reverse().toString().equals(""+x);
    }
}

