public class MatrixReshape_566 {

	public int[][] matrixReshape(int[][] nums, int r, int c) {
		if (nums == null || nums.length == 0) {
			return nums;
		}

		int m = nums.length;
		int n = nums[0].length;
		if (m * n != r * c) {
			return nums;
		}

		return reshape(nums, r, c);
	}

	private int[][] reshape(int[][] nums, int r, int c) {
		int[][] res = new int[r][c];
		int row = 0;
		int col = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = 0; j < nums[0].length; j++) {
				res[row][col++] = nums[i][j];
				if (col == c) {
					row++;
					col = 0;
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		MatrixReshape_566 obj = new MatrixReshape_566();
		int[][] matrix = { { 1, 2, 3, 4 } };
		int[][] res = obj.matrixReshape(matrix, 2, 2);
		System.out.println(res);
	}

}
