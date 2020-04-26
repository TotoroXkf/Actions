public class Solution {
    public String numberToWords(int num) {
        String numString = "" + num;
        String[] array = new String[]{"", "Thousand", "Million", "Billion"};
        int i = 0;
        int right = numString.length();
        int left = right - 3;
        StringBuilder result = new StringBuilder();
        while (left > -1) {
            String str = getThreeCharString(numString.substring(left, right));
            if (!str.equals("Zero")) {
                str += (" " + array[i]);
                result.insert(0, str + " ");
            }
            left -= 3;
            right -= 3;
            i++;
        }
        if (right > 0) {
            if (right == 1) {
                result.insert(0, getOneCharString(numString.charAt(0)) + " " + array[i] + " ");
            } else if (right == 2) {
                result.insert(0, getTwoCharString(numString.substring(0, right)) + " " + array[i] + " ");
            }
        }
        int j;
        for (j = result.length() - 1; j > -1 && result.charAt(j) == ' '; j--) {
            result.delete(j, j + 1);
        }
        return result.toString().trim();
    }

    public String getThreeCharString(String threeChar) {
        String hundred = "Hundred";
        String subResult = getTwoCharString(threeChar.substring(1, 3));
        if ((threeChar.charAt(0) - '0') == 0) {
            return subResult;
        }
        String result = "";
        result += getOneCharString(threeChar.charAt(0));
        result += " " + hundred;
        if (!subResult.equals("Zero")) {
            result += (" " + subResult);
        }
        return result;
    }

    public String getTwoCharString(String twoChar) {
        String[] array1 = new String[]{"", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
        String[] array2 = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
        int value = (twoChar.charAt(0) - '0') * 10 + twoChar.charAt(1) - '0';
        if (value < 10) {
            return getOneCharString((char) (value + '0'));
        }
        if (value % 10 == 0) {
            return array2[value / 10];
        }
        if (value < 20) {
            return array1[value - 10];
        }
        return array2[value / 10] + " " + getOneCharString(twoChar.charAt(1));
    }

    public String getOneCharString(char c) {
        String[] array = new String[]{"Zero", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
        return array[c - '0'];
    }
}