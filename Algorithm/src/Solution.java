import java.util.ArrayList;
import java.util.List;

class Solution {
    public String[] permutation(String s) {
        boolean[] marked = new boolean[s.length()];
        List<String> list = new ArrayList<>();
        permutation(s, marked, list, new StringBuilder());
        String[] result = new String[list.size()];
        list.toArray(result);
        return result;
    }

    public void permutation(String s, boolean[] marked, List<String> result, StringBuilder stringBuilder) {
        if (stringBuilder.length() == s.length()) {
            result.add(stringBuilder.toString());
        }
        for (int i = 0; i < s.length(); i++) {
            if (marked[i]) {
                continue;
            }
            stringBuilder.append(s.charAt(i));
            marked[i] = true;
            permutation(s, marked, result, stringBuilder);
            marked[i] = false;
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }
    }
}