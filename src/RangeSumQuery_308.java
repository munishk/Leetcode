public class RangeSumQuery_308 {

	int[][] tree;
	int[][] matrix;
	int m, n;

	public RangeSumQuery_308(int[][] matrix) {
		if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
			return;
		}
		m = matrix.length;
		n = matrix[0].length;
		tree = new int[m + 1][n + 1];
		this.matrix = new int[m][n];

		for (int i = 0; i < m; i++) {
			for (int j = 0; j < n; j++) {
				update(i, j, matrix[i][j]);
			}
		}

	}

	public void update(int row, int col, int val) {
		int delta = val - matrix[row][col];
		matrix[row][col] = delta;
		for (int i = row + 1; i <= m; i += i & (-i)) {
			for (int j = col + 1; j <= n; j += j & (-j)) {
				tree[i][j] += delta;
			}
		}
	}

	private int sumRegion(int row, int col) {
		int sum = 0;
		for (int i = row + 1; i > 0; i -= i & (-i)) {
			for (int j = col + 1; j > 0; j -= j & (-j)) {
				sum += tree[i][j];
			}
		}
		return sum;
	}

	public int sumRegion(int row1, int col1, int row2, int col2) {
		return sumRegion(row2, col2) - sumRegion(row1 - 1, col2) - sumRegion(row2, col1 - 1)
				+ sumRegion(row1 - 1, col1 - 1);
	}

	public static void main(String[] args) {
		int[][] matrix =  {{1, 2, 3, 4},
                {5, 3, 8, 1},
                {4, 6, 7, 5},
                {2, 4, 8, 9}};
		RangeSumQuery_308 obj = new RangeSumQuery_308(matrix);
		System.out.println(obj.sumRegion(1, 1, 2, 2));
	}

}
