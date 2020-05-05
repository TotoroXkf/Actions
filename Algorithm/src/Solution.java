class Solution {
    public double[] cutSquares(int[] square1, int[] square2) {
        double mid1X = square1[0] + square1[2] / 2d;
        double mid1Y = square1[1] + square1[2] / 2d;
        double mid2X = square2[0] + square2[2] / 2d;
        double mid2Y = square2[1] + square2[2] / 2d;
        if (mid1X == mid2X) {
            return new double[]{mid1X, Math.min(square1[1], square2[1]), mid1X, Math.max(square1[1] + square1[2], square2[1] + square2[2])};
        }
        if (mid1Y == mid2Y) {
            return new double[]{Math.min(square1[0], square2[0]), mid1Y, Math.max(square1[0] + square1[2], square2[0] + square2[2]), mid2Y};
        }
        double k = (mid1Y - mid2Y) / (mid1X - mid2X);
        double b = (mid1Y + mid2Y - k * (mid1X + mid2X)) / 2d;
        double[] points1 = new double[4];
        getPoint(square1, k, b, points1);
        double[] points2 = new double[4];
        getPoint(square2, k, b, points2);
        double[] result = new double[4];
        double minX = Math.min(Math.min(points1[0], points1[2]), Math.min(points2[0], points2[2]));
        double y = k * minX + b;
        result[0] = minX;
        result[1] = y;
        double maxX = Math.max(Math.max(points1[0], points1[2]), Math.max(points2[0], points2[2]));
        y = k * maxX + b;
        result[2] = maxX;
        result[3] = y;
        return result;
    }

    private void getPoint(int[] square, double k, double b, double[] result) {
        double y = k * square[0] + b;
        double x1, x2, y1, y2;
        if (y <= square[1] + square[2] && y >= square[1]) {
            x1 = square[0];
            y1 = k * x1 + b;
            x2 = square[0] + square[2];
            y2 = k * x2 + b;
        } else {
            y1 = square[1] + square[2];
            x1 = (y1 - b) / k;
            y2 = square[1];
            x2 = (y2 - b) / k;
        }
        result[0] = x1;
        result[1] = y1;
        result[2] = x2;
        result[3] = y2;
    }
}