import java.util.HashMap;

class Solution {
    public int[] masterMind(String solution, String guess) {
        int[] result = new int[2];
        HashMap<Character, Integer> solutionHasMap = new HashMap<>();
        HashMap<Character, Integer> guessHashMap = new HashMap<>();
        for (int i = 0; i < solution.length(); i++) {
            if (solution.charAt(i) == guess.charAt(i)) {
                result[0]++;
            } else {
                solutionHasMap.put(solution.charAt(i), solutionHasMap.getOrDefault(solution.charAt(i), 0) + 1);
                guessHashMap.put(guess.charAt(i), guessHashMap.getOrDefault(guess.charAt(i), 0) + 1);
            }
        }
        for (Character character : guessHashMap.keySet()) {
            if (solutionHasMap.containsKey(character)) {
                result[1] += Math.min(solutionHasMap.get(character), guessHashMap.get(character));
            }
        }
        return result;
    }
}