import java.util.HashMap;

class Solution {
    public String intToRoman(int num) {
        HashMap<Integer, String[]> hashMap = new HashMap<>();
        hashMap.put(1, new String[]{"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"});
        hashMap.put(2, new String[]{"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"});
        hashMap.put(3, new String[]{"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"});
        hashMap.put(4, new String[]{"", "M", "MM", "MMM"});
        int index = 1;
        StringBuilder result = new StringBuilder();
        while (num > 0) {
            int value = num % 10;
            result.insert(0, hashMap.get(index)[value]);
            index++;
            num /= 10;
        }
        return result.toString();
    }
}