import java.util.LinkedList;
import java.util.List;

class Utils {
    static <T> void println(List<T> list) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < list.size(); i++) {
            if (i == list.size() - 1) {
                stringBuilder.append(list.get(i).toString());
            } else {
                stringBuilder.append(list.get(i).toString()).append(", ");
            }
        }
        stringBuilder.append("]");
        System.out.println(stringBuilder.toString());
    }

    static LinkedList<Integer> newRandomStack(int length) {
        return newRandomStack(length, 0, 100);
    }

    private static LinkedList<Integer> newRandomStack(int length, int minValue, int maxValue) {
        int i = 0;
        LinkedList<Integer> stack = new LinkedList<>();
        while (i < length) {
            double randomValue = Math.random();
            int value = (int) (randomValue * (maxValue - minValue) + minValue);
            stack.push(value);
            i++;
        }
        return stack;
    }
}
