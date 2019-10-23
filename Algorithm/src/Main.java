import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int w = sc.nextInt();
        int[] x = new int[n];
        for (int i = 0; i < n; i++) {
            x[i] = sc.nextInt();
        }
        List<Integer> list = new Main().getMaxWindow(x, w);
        for (int i = 0; i < list.size(); i++) {
            System.out.print(list.get(i) + " ");
        }
    }

    public List<Integer> getMaxWindow(int[] array, int size) {
        ArrayList<Integer> result = new ArrayList<>();
        if (size < 1) {
            return result;
        }
        size = Math.min(array.length, size);
        LinkedList<Integer> queue = new LinkedList<>();
        int i = -1;
        int j = 0;
        while (j < array.length) {
            addElement(queue, array[j]);
            if (j - i < size) {
                j++;
                continue;
            }
            result.add(queue.getFirst());
            j++;
            i++;
            if (queue.getFirst() == array[i]) {
                queue.removeFirst();
            }
        }
        return result;
    }

    private void addElement(LinkedList<Integer> queue, int value) {
        while (!queue.isEmpty() && queue.getLast() < value) {
            queue.removeLast();
        }
        queue.addLast(value);
    }
}
