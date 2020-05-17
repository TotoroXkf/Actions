import java.util.ArrayList;

class Solution {
    public boolean patternMatching(String pattern, String value) {
        if (pattern.isEmpty() && value.isEmpty()) {
            return true;
        }
        if (pattern.isEmpty()) {
            return false;
        }
        int numA = 0;
        int numB = 0;
        for (int i = 0; i < pattern.length(); i++) {
            if (pattern.charAt(i) == 'a') {
                numA++;
            } else {
                numB++;
            }
        }
        ArrayList<Integer[]> list = getList(numA, numB, value);
        return patternMatching(pattern, value, list);
    }

    private ArrayList<Integer[]> getList(int numA, int numB, String value) {
        int lenA;
        int lenB = 0;
        ArrayList<Integer[]> result = new ArrayList<>();
        if (numB == 0) {
            if (value.length() % numA == 0) {
                lenA = value.length() / numA;
                result.add(new Integer[]{lenA, lenB});
            }
            return result;
        }
        lenB = value.length() / numB;
        while (lenB > -1) {
            int remaining = value.length() - numB * lenB;
            if ((numA != 0 && remaining != 0 && remaining % numA == 0)) {
                lenA = remaining / numA;
                Integer[] integers = new Integer[]{lenA, lenB};
                result.add(integers);
            } else if (remaining == 0) {
                lenA = 0;
                Integer[] integers = new Integer[]{lenA, lenB};
                result.add(integers);
            }
            lenB--;
        }
        return result;
    }

    private boolean patternMatching(String pattern, String value, ArrayList<Integer[]> list) {
        if (list.isEmpty()) {
            return false;
        }
        for (Integer[] lens : list) {
            int lenA = lens[0];
            int lenB = lens[1];
            int currentIndex = 0;
            String stringA = null;
            String stringB = null;
            for (int i = 0; i < pattern.length(); i++) {
                if (pattern.charAt(i) == 'a') {
                    if (currentIndex + lenA > value.length()) {
                        break;
                    }
                    String subString = value.substring(currentIndex, currentIndex + lenA);
                    if (stringA == null) {
                        stringA = subString;
                    }
                    if (!stringA.equals(subString)) {
                        break;
                    }
                    currentIndex += lenA;
                } else {
                    if (currentIndex + lenB > value.length()) {
                        break;
                    }
                    String subString = value.substring(currentIndex, currentIndex + lenB);
                    if (stringB == null) {
                        stringB = subString;
                    }
                    if (!stringB.equals(subString)) {
                        break;
                    }
                    currentIndex += lenB;
                }
            }
            if (currentIndex == value.length()) {
                if (stringA != null && stringB != null && !stringA.equals(stringB)) {
                    return true;
                }
                if (stringA != null && stringB == null) {
                    return true;
                }
                if (stringA == null && stringB != null) {
                    return true;
                }
            }
        }
        return false;
    }
}