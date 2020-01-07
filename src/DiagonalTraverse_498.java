import util.StringUtils;

public class DiagonalTraverse_498 {

	public int[] findDiagonalOrder(int[][] matrix) {
		int row = 0;
		int col = 0;
		int dir = 1; // 0- down, 1-up
		int m = matrix.length;
		int n = matrix[0].length;
		int[] res = new int[m * n];
		int index = 0;
		while (row >= 0 && row < m && col >= 0 && col < n) {
			if (dir == 1) { // up
				while (row >= 0 && col < n) {
					res[index++] = matrix[row][col];
					row--;
					col++;
				}

				if (col >= n) {
					col = n - 1;
					row = row + 2;
				} else if (row < 0) {
					row = 0;
				}
				dir = 0;

			} else { // down
				while (row < m && col >= 0) {
					res[index++] = matrix[row][col];
					row++;
					col--;
				}
				if (row >= m) {
					row = m - 1;
					col = col + 2;
				} else if (col < 0) {
					col = 0;
				}
				dir = 1;
			}
		}
		return res;
	}

	public static void main(String[] args) {
		DiagonalTraverse_498 obj = new DiagonalTraverse_498();
		int[][] matrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };
		int[] res = obj.findDiagonalOrder(matrix);
		System.out.println(StringUtils.toString(res));
	}

}
