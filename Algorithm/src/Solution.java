class Solution {
    public int myAtoi(String str) {
        int i = 0;
        for (; i < str.length() && str.charAt(i) == ' '; i++) ;
        if (i == str.length()) {
            return 0;
        }
        boolean isNav = false;
        if (str.charAt(i) == '-') {
            isNav = true;
        }
        int result = 0;
        if (isNav || str.charAt(i) == '+') {
            i++;
        }
        for (; i < str.length() && str.charAt(i) == '0'; i++) ;
        while (i < str.length() && Character.isDigit(str.charAt(i))) {
            int value = str.charAt(i) - 48;
            if (result > Integer.MAX_VALUE / 10 || (result == Integer.MAX_VALUE / 10 && value > 7)) {
                return Integer.MAX_VALUE;
            }
            if (result < Integer.MIN_VALUE / 10 || (result == Integer.MIN_VALUE / 10 && value == 9)) {
                return Integer.MIN_VALUE;
            }
            if (isNav) {
                value *= -1;
                if (result > 0) {
                    result *= -1;
                }
            }
            result = result * 10 + value;
            i++;
        }
        return result;
    }
}