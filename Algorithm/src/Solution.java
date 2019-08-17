import java.util.HashMap;

class Solution {
    public int romanToInt(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        HashMap<Character, Integer> hashMap = new HashMap<>();
        hashMap.put('I', 1);
        hashMap.put('V', 5);
        hashMap.put('X', 10);
        hashMap.put('L', 50);
        hashMap.put('C', 100);
        hashMap.put('D', 500);
        hashMap.put('M', 1000);
        int result = 0;
        int pre = Integer.MAX_VALUE;
        for (char aChar : s.toCharArray()) {
            int value = hashMap.get(aChar);
            result += value;
            if (value > pre) {
                result -= (2 * pre);
            }
            pre = value;
        }
        return result;
    }
}