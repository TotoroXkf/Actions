public class Solution {
    public double[] intersection(int[] start1, int[] end1, int[] start2, int[] end2) {
        int x1 = start1[0], x2 = end1[0], x3 = start2[0], x4 = end2[0];
        int y1 = start1[1], y2 = end1[1], y3 = start2[1], y4 = end2[1];
        if (x1 == x2 && x3 == x4) {
            if (x1 != x3) {
                return new double[0];
            }
            if (Math.min(y3, y4) <= Math.max(y1, y2) && Math.max(y1, y2) <= Math.max(y3, y4)) {
                if (Math.min(y1, y2) >= Math.min(y3, y4)) {
                    return new double[]{x1, Math.min(y1, y2)};
                } else {
                    return new double[]{x1, Math.min(y3, y4)};
                }
            } else if (Math.min(y1, y2) <= Math.max(y3, y4) && Math.max(y3, y4) <= Math.max(y1, y2)) {
                if (Math.min(y3, y4) >= Math.min(y1, y2)) {
                    return new double[]{x1, Math.min(y3, y4)};
                } else {
                    return new double[]{x1, Math.min(y1, y2)};
                }
            } else {
                return new double[0];
            }
        } else if (x1 == x2) {
            return getDoubleValue(x3, y3, x4, y4, x1, y1, y2);
        } else if (x3 == x4) {
            return getDoubleValue(x1, y1, x2, y2, x3, y3, y4);
        }
        double k1 = (double) (y1 - y2) / (double) (x1 - x2);
        double b1 = ((double) (y1 + y2) - k1 * (x1 + x2)) / 2d;
        double k2 = (double) (y3 - y4) / (double) (x3 - x4);
        double b2 = ((double) (y3 + y4) - k2 * (x3 + x4)) / 2d;
        if (k1 != k2) {
            double x = (b2 - b1) / (k1 - k2);
            double y = k1 * x + b1;
            if (x <= Math.max(x1, x2) &&
                x >= Math.min(x1, x2) &&
                x <= Math.max(x3, x4) &&
                x >= Math.min(x3, x4) &&
                y <= Math.max(y1, y2) &&
                y >= Math.min(y1, y2) &&
                y <= Math.max(y3, y4) &&
                y >= Math.min(y3, y4)
            ) {
                return new double[]{x, y};
            }
        }
        if (b1 != b2) {
            return new double[0];
        }
        if (Math.min(x3, x4) <= Math.max(x1, x2) && Math.max(x1, x2) <= Math.max(x3, x4)) {
            if (Math.min(x3, x4) >= Math.min(x1, x2)) {
                return new double[]{x3, y3};
            } else {
                return new double[]{x1, y1};
            }
        }
        if (Math.min(x1, x2) <= Math.max(x3, x4) && Math.max(x3, x4) <= Math.max(x1, x2)) {
            if (Math.min(x1, x2) >= Math.min(x3, x4)) {
                return new double[]{x1, y1};
            } else {
                return new double[]{x3, y3};
            }
        }
        return new double[0];
    }

    private double[] getDoubleValue(int x1, int y1, int x2, int y2, int x, int y3, int y4) {
        double k = (double) (y1 - y2) / (double) (x1 - x2);
        double b = ((double) (y1 + y2) - k * (x1 + x2)) / 2d;
        double y = k * x + b;
        if (y >= Math.min(y3, y4) && y <= Math.max(y3, y4)) {
            return new double[]{x, y};
        }
        return new double[0];
    }
}