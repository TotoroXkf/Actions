import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
//        int length = scanner.nextInt();
//        int[] array = new int[length];
//        for (int i = 0; i < array.length; i++) {
//            array[i] = scanner.nextInt();
//        }
//        int[][] result = findTwoSideMinValuePosition(array);
//        for (int[] integers : result) {
//            System.out.println(integers[0] + " " + integers[1]);
//        }
//        int[][] array = new int[][]{
//                new int[]{0, 0, 1, 1},
//                new int[]{1, 1, 0, 0},
//                new int[]{1, 0, 1, 0},
//                new int[]{1, 1, 1, 0},
//        };
//        Utils.println(compression(array));
    }

    public static int[][] findTwoSideMinValuePosition(int[] array) {
        int[][] result = new int[array.length][2];
        for (int i = 0; i < array.length; i++) {
            result[i][0] = result[i][1] = -1;
        }
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            while (!stack.isEmpty() && array[stack.peek()] > array[i]) {
                int index = stack.pop();
                result[index][1] = i;
            }
            if (!stack.isEmpty() && array[stack.peek()] == array[i]) {
                result[i][0] = result[stack.peek()][0];
            } else if (!stack.isEmpty()) {
                result[i][0] = stack.peek();
            }
            stack.push(i);
        }
        return result;
    }

    private static int[] compression(int[][] array) {
        if (array.length == 0 || array[0].length == 0) {
            return new int[]{};
        }
        int[] result = new int[array[0].length];
        int lastRow = array.length - 1;
        for (int i = 0; i < array[0].length; i++) {
            if (array[lastRow][i] == 0) {
                result[i] = 0;
                continue;
            }
            int j = lastRow;
            int count = 0;
            while (j > 0 && array[j][i] != 0) {
                j--;
                count++;
            }
            result[i] = count;
        }
        return result;
    }
}
