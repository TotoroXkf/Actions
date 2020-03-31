class Solution {
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        boolean[][] marked = new boolean[image.length][image[0].length];
        fill(image, marked, sr, sc, image[sr][sc], newColor);
        return image;
    }

    private void fill(int[][] image, boolean[][] marked, int row, int column, int oldColor, int newColor) {
        if (row >= image.length || row < 0 || column >= image[0].length || column < 0 || marked[row][column]) {
            return;
        }
        marked[row][column] = true;
        if (image[row][column] != oldColor) {
            return;
        }
        image[row][column] = newColor;
        fill(image, marked, row + 1, column, oldColor, newColor);
        fill(image, marked, row - 1, column, oldColor, newColor);
        fill(image, marked, row, column + 1, oldColor, newColor);
        fill(image, marked, row, column - 1, oldColor, newColor);
    }
}