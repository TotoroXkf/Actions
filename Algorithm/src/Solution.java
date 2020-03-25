class Solution {
    public int[] findClosedNumbers(int num) {
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        int mask = 2;
        int firstValue = (num & 1) == 0 ? 0 : 1;
        int firstIndex = 1;
        int secondIndex = -1;
        int thirdIndex = -1;
        int i = 2;
        while (mask != 0) {
            int value = num & mask;
            if (((value == 0 && firstValue == 1) || (value != 0 && firstValue == 0)) && secondIndex == -1) {
                secondIndex = mask;
            }
            if (((value != 0 && firstValue == 1) || (firstValue == value)) && secondIndex != -1) {
                thirdIndex = mask;
                break;
            }
            mask <<= 1;
        }
        if (secondIndex != -1) {
            int value = num ^ secondIndex;
            value = value ^ firstIndex;
            setValue(value, num, result);
        }
        if (thirdIndex != -1) {
            int value = num ^ secondIndex;
            value = value ^ thirdIndex;
            setValue(value, num, result);
        }
        return result;
    }

    private void setValue(int value, int num, int[] result) {
        if (value > num) {
            result[0] = value;
        } else {
            result[1] = value;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.findClosedNumbers(2);

        System.out.println(Integer.toBinaryString(1837591841));
        System.out.println(Integer.toBinaryString(1837591832));
    }
}