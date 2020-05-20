import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

class Solution {
    public List<String> printKMoves(int k) {
        // 使用set记录黑块的位置
        HashSet<Position> hashSet = new HashSet<>();
        int currentX = 0, currentY = 0, minX = 0, maxX = 0, minY = 0, maxY = 0;
        int[][] updateDirection = new int[][]{new int[]{0, -1}, new int[]{1, 0}, new int[]{0, 1}, new int[]{-1, 0}};
        int direction = 1;
        // 模拟移动
        for (int i = 0; i < k; i++) {
            Position position = new Position(currentX, currentY);
            // 添加成功说明当前是白色的块
            if (hashSet.add(position)) {
                direction++;
                direction %= 4;
            } else {
                // 添加失败说明是黑色的块
                direction--;
                if (direction == -1) {
                    direction = 3;
                }
                hashSet.remove(position);
            }
            // 移动
            currentX += updateDirection[direction][0];
            currentY += updateDirection[direction][1];
            // 确定边界
            minX = Math.min(minX, currentX);
            maxX = Math.max(maxX, currentX);
            minY = Math.min(minY, currentY);
            maxY = Math.max(maxY, currentY);
        }
        char[][] map = new char[maxY - minY + 1][maxX - minX + 1];
        // 填充白色
        for (char[] chars : map) {
            Arrays.fill(chars, '_');
        }
        // 填充黑色
        for (Position position : hashSet) {
            int x = position.x - minX;
            int y = position.y - minY;
            map[y][x] = 'X';
        }
        // 设置蚂蚁的位置
        char[] directionChar = new char[]{'U', 'R', 'D', 'L'};
        currentX -= minX;
        currentY -= minY;
        map[currentY][currentX] = directionChar[direction];
        List<String> result = new ArrayList<>();
        for (char[] chars : map) {
            result.add(new String(chars));
        }
        return result;
    }

    private static class Position {
        int x, y;

        public Position(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(Object obj) {
            if (obj == this) return true;
            if (!(obj instanceof Position)) return false;
            Position o = (Position) obj;
            return x == o.x && y == o.y;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
    }

}