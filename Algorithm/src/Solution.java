class Solution {
    public String printBin(double num) {
        if (num >= 1 || num < 0) {
            return "ERROR";
        }
        StringBuilder sb = new StringBuilder("0.");
        for (int i = 1; i < 31 && num > 0; i++) {
            double value = Math.pow(0.5, i);
            if (num >= value) {
                num -= value;
                sb.append('1');
            } else {
                sb.append('0');
            }
        }
        return num == 0 ? sb.toString() : "ERROR";
    }
}