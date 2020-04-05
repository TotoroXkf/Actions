import java.util.Arrays;

class Solution {
    public int pileBox(int[][] box) {
        Arrays.sort(box, (array1, array2) -> Integer.compare(array2[0], array1[0]));
        int[] remember = new int[box.length];
        Arrays.fill(remember, -1);
        int max = 0;
        for (int i = 0; i < box.length; i++) {
            int value = pileBox(box, i, remember);
            max = Math.max(value, max);
        }
        return max;
    }

    private int pileBox(int[][] box, int index, int[] remember) {
        if (index >= box.length) {
            return 0;
        }
        int max = box[index][2];
        for (int i = index + 1; i < box.length; i++) {
            if (compare(box[index], box[i])) {
                if (remember[i] == -1) {
                    int value = pileBox(box, i, remember);
                    max = Math.max(value + box[index][2], max);
                } else {
                    max = Math.max(remember[i] + box[index][2], max);
                }
            }
        }
        remember[index] = max;
        return max;
    }

    private boolean compare(int[] array1, int[] array2) {
        return array1[0] > array2[0] && array1[1] > array2[1] && array1[2] > array2[2];
    }

    public static void main(String[] args) {
        int[][] array = Utils.createMatrix("[[9, 9, 10], [8, 10, 9], [9, 8, 10], [9, 8, 10], [10, 8, 8], [9, 8, 9], [9, 8, 8], [8, 9, 10], [10, 9, 10], [8, 8, 10], [10, 9, 10], [10, 9, 8], [8, 9, 9], [9, 10, 8], [8, 9, 9], [10, 10, 9], [8, 9, 10], [8, 10, 10], [8, 9, 10], [10, 10, 8], [10, 10, 9], [9, 10, 10], [10, 8, 9], [10, 10, 9], [8, 9, 10], [8, 8, 9], [8, 10, 10], [9, 9, 10], [10, 8, 8], [10, 10, 8], [8, 9, 9], [8, 9, 8], [10, 10, 8], [8, 10, 8], [10, 9, 10], [9, 9, 10], [9, 9, 9], [8, 9, 8], [9, 8, 8], [8, 9, 10], [10, 10, 8], [9, 9, 9], [10, 10, 10], [10, 9, 8], [9, 8, 9], [8, 8, 10], [8, 8, 8], [8, 8, 8], [8, 9, 10], [10, 9, 8], [8, 10, 8], [8, 10, 10], [9, 10, 10], [8, 8, 9], [9, 9, 9], [10, 8, 8], [8, 10, 10], [9, 10, 9], [9, 9, 8], [8, 10, 9], [9, 8, 8], [9, 9, 10], [9, 10, 10], [8, 8, 10]]");
        Solution solution = new Solution();
        System.out.println(solution.pileBox(array));
    }
}