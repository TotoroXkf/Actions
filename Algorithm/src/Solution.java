import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int[] bestLine(int[][] points) {
        HashMap<Double, ArrayList<Integer>> hashMap = new HashMap<>();
        int maxLen = 0;
        int[] result = new int[2];
        for (int i = 0; i < points.length; i++) {
            int x1 = points[i][0];
            int y1 = points[i][1];
            for (int j = i + 1; j < points.length; j++) {
                int x2 = points[j][0];
                int y2 = points[j][1];
                // 确定通过起始点的直线，斜率相同则一定在同一条直线上面
                double k = getK(x1, y1, x2, y2);
                ArrayList<Integer> arrayList = hashMap.getOrDefault(k, new ArrayList<>());
                // 记录在同一条直线上面的点
                if (arrayList.isEmpty()) {
                    arrayList.add(i);
                    hashMap.put(k, arrayList);
                }
                arrayList.add(j);
                // 比较最大值
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
        // 在java中 0d和-0d在 hashmap 中的 比较会不一样，所以统一返回0d
        if (y1 - y2 == 0) {
            return 0d;
        }
        // 在java中 无穷分为正无穷和负无穷， hashmap 中的 比较会不一样，所以统一返回正无穷
        if (x1 == x2) {
            return Double.POSITIVE_INFINITY;
        }
        return (double) (y1 - y2) / (double) (x1 - x2);
    }
}