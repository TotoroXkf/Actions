class Solution {
    public int maxArea(int[] height) {
        int result = 0;
        int start = 0;
        int end = height.length - 1;
        while (start < end) {
            int area = (end - start) * Math.min(height[start], height[end]);
            result = Math.max(result, area);
            if(height[start]<height[end]){
                start++;
            }else {
                end--;
            }
        }
        return result;
    }
}