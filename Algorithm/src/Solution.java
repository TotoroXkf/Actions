import java.util.LinkedList;

class Solution {
    public boolean isValid(String s) {
        LinkedList<Character> stack = new LinkedList<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case '{':
                case '[':
                case '(':
                    stack.add(c);
                    break;
                case '}':
                    if (stack.isEmpty() || stack.removeLast() != '{') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.removeLast() != '[') return false;
                    break;
                case ')':
                    if (stack.isEmpty() || stack.removeLast() != '(') return false;
                    break;
            }
        }
        return stack.isEmpty();
    }
}