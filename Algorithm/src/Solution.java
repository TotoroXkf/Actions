import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public List<String> printKMoves(int k) {
        HashMap<String, Integer> hashMap = new HashMap<>();
        hashMap.put("0,0", 1);
        int direction = 1;
        int minX = 0, minY = 0, maxX = 0, maxY = 0;
        int currentX = 0, currentY = 0;
        for (int i = 0; i < k; i++) {
            String key = currentX + "," + currentY;
            int value = hashMap.getOrDefault(key, 1);
            hashMap.put(key, -value);
            if (value == 1) {
                direction++;
            } else {
                direction--;
            }
            direction %= 4;
            switch (direction) {
                case 0:
                    currentY--;
                    break;
                case 1:
                    currentX++;
                    break;
                case 2:
                    currentY++;
                    break;
                case 3:
                    currentX--;
                    break;
            }
            minX = Math.min(minX, currentX);
            minY = Math.min(minY, currentY);
            maxX = Math.max(maxX, currentX);
            maxY = Math.max(maxY, currentY);
        }
        boolean[][] map = new boolean[maxY - minY + 1][maxX - minX + 1];
        for (String key : hashMap.keySet()) {
            int index = key.indexOf(',');
            int x = Integer.parseInt(key.substring(0, index)) - minX;
            int y = Integer.parseInt(key.substring(index + 1)) - minY;
            int value = hashMap.get(key);
            if (value == -1) {
                map[y][x] = true;
            }
        }
        currentX -= minX;
        currentY -= minY;
        List<String> result = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int j = 0; j < map[0].length; j++) {
                if (i == currentY && j == currentX) {
                    switch (direction) {
                        case 0:
                            stringBuilder.append("U");
                            break;
                        case 1:
                            stringBuilder.append("R");
                            break;
                        case 2:
                            stringBuilder.append("D");
                            break;
                        case -1:
                        case 3:
                            stringBuilder.append("L");
                            break;
                    }
                    continue;
                }
                if (map[i][j]) {
                    stringBuilder.append("X");
                } else {
                    stringBuilder.append("_");
                }
            }
            result.add(stringBuilder.toString());
        }
        return result;
    }
}