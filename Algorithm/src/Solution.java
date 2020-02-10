import java.util.LinkedList;

class Solution {
    public String simplifyPath(String path) {
        LinkedList<String> stack = new LinkedList<>();
        int i = 0;
        int startIndex = 0;
        int endIndex = 0;
        while (i < path.length()) {
            while (i < path.length() && path.charAt(i) == '/') {
                i++;
            }
            startIndex = i;
            while (i < path.length() && path.charAt(i) != '/') {
                i++;
            }
            endIndex = i;
            String desc = path.substring(startIndex, endIndex);
            handle(desc, stack);
        }
        if (stack.isEmpty()) {
            return "/";
        }
        StringBuilder result = new StringBuilder();
        while (!stack.isEmpty()) {
            result.insert(0, stack.pop());
        }
        return result.toString();
    }

    private void handle(String desc, LinkedList<String> stack) {
        if (desc.equals(".") || desc.equals("")) {
            return;
        }
        if (desc.equals("..")) {
            if (!stack.isEmpty()) {
                stack.pop();
            }
        } else {
            stack.push("/" + desc);
        }
    }

    public static void main(String[] args) {
        String str = "/a//b////c/d//././/..";
        System.out.println(new Solution().simplifyPath(str));
    }
}