public class UniquePathII {

	/*
	 * Using recursion. Complexity is O(2^n)
	 */
	public int uniquePathsRecursion(int[][] obstacleGrid) {
		if (obstacleGrid == null || obstacleGrid.length == 0) {
			return 0;
		}
		return uniquePathsRecursion(obstacleGrid, obstacleGrid.length - 1, obstacleGrid[0].length - 1);
	}

	private int uniquePathsRecursion(int[][] grid, int row, int col) {
		if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == 1) {
			return 0;
		}

		if (row == 0 && col == 0) {
			return 1;
		}

		return uniquePathsRecursion(grid, row - 1, col) + uniquePathsRecursion(grid, row, col - 1);
	}

	/*
	 * Using DP. Complexity is O(n)
	 */
	public int uniquePathsDp(int[][] grid) {
		if (grid == null || grid.length == 0) {
			return 0;
		}
		int[][] dp = new int[grid.length + 1][grid[0].length + 1];
		for (int i = 0; i < dp.length; i++) {
			for (int j = 0; j < dp[0].length; j++) {
				if (i == 0 || j == 0 || grid[i - 1][j - 1] == 1) {
					dp[i][j] = 0;
				} else if (i == 1 && j == 1) {
					dp[i][j] = 1;
				} else {
					dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
				}
			}
		}
		return dp[grid.length][grid[0].length];
	}

	public static void main(String[] args) {
		UniquePathII obj = new UniquePathII();
		int[][] grid = { { 0 } };
		System.out.println(obj.uniquePathsRecursion(grid));
		System.out.println(obj.uniquePathsDp(grid));

	}

}
