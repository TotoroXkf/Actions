public class Main {
//	public int getMaxSubMatrixValue(int[][] matrix) {
//
//	}
	
	private int[] zip(int[][] matrix) {
		if (matrix.length <= 0 || matrix[matrix.length - 1].length<=0) {
			return new int[0];
		}
		int[] result = new int[matrix[matrix.length - 1].length];
		for (int i = 0; i < matrix[matrix.length - 1].length; i++) {
			int count = 0;
			for (int j = matrix.length-1; j >-1 ; j--) {
				if(matrix[j][i]==0){
					break;
				}
				count++;
			}
			result[i] = count;
		}
		return result;
	}
	
	public static void main(String[] args) {
		Main main = new Main();
		int[][] matrix = new int[][]{
			new int[]{1,0,1,1},
            new int[]{1,1,1,1},
            new int[]{1,1,1,0},
		};
		Utils.println(main.zip(matrix));
	}
}
