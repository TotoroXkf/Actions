import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public boolean findWhetherExistsPath(int n, int[][] graph, int start, int target) {
        HashMap<Integer, List<Integer>> graphMap = new HashMap<>();
        HashMap<Integer, Boolean> puts = new HashMap<>();
        for (int i = 0; i < n; i++) {
            puts.put(i, false);
            List<Integer> list = new ArrayList<>();
            graphMap.put(i, list);
        }
        for (int[] ints : graph) {
            graphMap.get(ints[0]).add(ints[1]);
        }
        return search(graphMap, puts, start, target);
    }

    public boolean search(HashMap<Integer, List<Integer>> graphMap, HashMap<Integer, Boolean> puts, int start, int target) {
        if (start == target) {
            return true;
        }
        if (puts.get(start)) {
            return false;
        }
        puts.put(start, true);
        List<Integer> list = graphMap.get(start);
        for (Integer value : list) {
            if (puts.get(value)) {
                continue;
            }
            boolean result = search(graphMap, puts, value, target);
            if (result) {
                return true;
            }
        }
        puts.put(start, false);
        return false;
    }
}