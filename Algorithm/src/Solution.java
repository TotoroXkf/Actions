import java.util.ArrayList;
import java.util.Arrays;

class Solution {
    public String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        ArrayList<Integer[]> numberMap = new ArrayList<>();
        int i = 2;
        StringBuilder stringBuilder = new StringBuilder("1");
        while (i <= n) {
            numberMap.clear();
            int target = -1;
            int count = 0;
            char[] numbers = stringBuilder.toString().toCharArray();
            for (char number : numbers) {
                if ((number - '0') != target) {
                    if (target != -1) {
                        numberMap.add(new Integer[]{target, count});
                    }
                    target = number - '0';
                    count = 1;
                } else {
                    count++;
                }
            }
            numberMap.add(new Integer[]{target, count});
            stringBuilder.delete(0, stringBuilder.length());
            for (Integer[] values : numberMap) {
                stringBuilder.append(values[1]);
                stringBuilder.append(values[0]);
            }
            i++;
        }
        return stringBuilder.toString();
    }
}