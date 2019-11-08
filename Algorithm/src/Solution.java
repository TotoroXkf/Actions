class Solution {
	public int divide(int dividend, int divisor) {
		boolean sign = true;
		if ((dividend > 0 && divisor < 0) || (dividend < 0 && divisor > 0)) {
			sign = false;
		}
		if (dividend > 0) dividend = -dividend;
		if (divisor > 0) divisor = -divisor;
		int result = nextDivide(dividend, divisor);
		if (!sign) {
			result = -result;
		} else if (result == Integer.MIN_VALUE) {
			result = Integer.MAX_VALUE;
		}
		return result;
	}

	private int nextDivide(int dividend, int divisor) {
		if (dividend > divisor) {
			return 0;
		}
		int i = 0;
		while (dividend <= (divisor << i)) {
			if ((Integer.MIN_VALUE >> 1) > (divisor << i)) {
				i++;
				break;
			}
			i++;
		}
		i = i - 1;
		int sum = 1 << i;
		return sum + nextDivide(dividend - (divisor << i), divisor);
	}
}