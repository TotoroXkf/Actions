public class Runner {
    public static void main(String[] args) {
        Solution solution = new Solution();
        String s = "0&0&0&1^1|0";
        int target = 1;
        System.out.println(solution.countEval(s, target));
    }
}
