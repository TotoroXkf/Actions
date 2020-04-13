class StreamRank {
    int[] arrays = new int[5001];

    public StreamRank() {

    }

    public void track(int x) {
        arrays[x] += 1;
    }

    public int getRankOfNumber(int x) {
        int result = 0;
        for (int i = 0; i <= x; i++) {
            result += arrays[i];
        }
        return result;
    }
}