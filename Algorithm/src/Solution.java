import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<String> generateParenthesis(int n) {
        ArrayList<String> result = new ArrayList<>();
        if (n == 0) {
            return result;
        }
        generate(result, "", n, 0, 0);
        return result;
    }

    private void generate(ArrayList<String> result, String current, int n, int leftNum, int rightNum) {
        if (leftNum == n && rightNum == n) {
            result.add(current);
            return;
        }
        if (leftNum > rightNum) {
            generate(result, current + ")", n, leftNum, rightNum + 1);
        }
        if (leftNum < n) {
            generate(result, current + "(", n, leftNum + 1, rightNum);
        }
    }
}