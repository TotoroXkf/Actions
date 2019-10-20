import java.util.List;

public class Utils {
    public static <T> void println(List<T> list) {
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
}
