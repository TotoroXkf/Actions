import java.util.ArrayList;
import java.util.LinkedHashMap;

class Solution {
    public int[] bestLine(int[][] points) {
        LinkedHashMap<Double, ArrayList<Integer>> hashMap = new LinkedHashMap<>();
        int maxLen = 0;
        int[] result = new int[2];
        for (int i = 0; i < points.length; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < points.length; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                double k = getK(x1, y1, x2, y2);
                ArrayList<Integer> arrayList = hashMap.getOrDefault(k, new ArrayList<>());
                if (arrayList.isEmpty()) {
                    arrayList.add(i);
                    hashMap.put(k, arrayList);
                }
                arrayList.add(j);
                if (arrayList.size() > maxLen) {
                    maxLen = arrayList.size();
                    result[0] = arrayList.get(0);
                    result[1] = arrayList.get(1);
                }
            }
            hashMap.clear();
        }
        return result;
    }

    private double getK(int x1, int y1, int x2, int y2) {
        if (y1 - y2 == 0) {
            return 0d;
        }
        if (x1 == x2) {
            return Double.POSITIVE_INFINITY;
        }
        return (double) (y1 - y2) / (double) (x1 - x2);
    }
}