class Solution {
    public int[] subSort(int[] array) {
        int[] result = new int[]{-1, -1};
        int left = 0;
        int right = array.length - 1;
        int i = 1;
        // 确立左区域和右区域
        while (i < array.length && array[i] >= array[left]) {
            left++;
            i++;
        }
        i = array.length - 2;
        while (i > -1 && array[i] <= array[right]) {
            right--;
            i--;
        }
        if (left >= right) {
            return result;
        }
        i = left + 1;
        // 求出中间区域的最大值
        int max = array[i];
        int min = array[i];
        while (i < right) {
            max = Math.max(max, array[i]);
            min = Math.min(min, array[i]);
            i++;
        }
        // 扩展中间区域
        while (left > -1 || right < array.length) {
            // 边界退出情况处理
            // 左边区域完全没了，右边区域的最小值大于中间区域的最大值
            // 右边区域完全没了，左边区域的最大值小于中间区域的最小值
            // 两边区域存在，右边区域的最小值大于中间区域的最大值，左边区域的最大值小于中间区域的最小值
            if ((left < 0 && array[right] >= max) || (right >= array.length && array[left] <= min) || (array[right] >= max && array[left] <= min)) {
                break;
            }
            // 扩展左边界
            if (left > -1 && array[left] > min) {
                max = Math.max(max, array[left]);
                left--;
            }
            // 扩展右边界
            if (array[right] < max) {
                min = Math.min(min, array[right]);
                right++;
            }
        }
        result[0] = left + 1;
        result[1] = right - 1;
        return result;
    }
}