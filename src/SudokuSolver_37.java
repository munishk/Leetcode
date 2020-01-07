public class SudokuSolver_37 {

	public void solveSudoku(char[][] board) {
		solveSudoku(board, 0, 0);
	}

	private boolean solveSudoku(char[][] board, int row, int col) {
		if (row < 0 || row >= board.length || col < 0 || col >= board[0].length) {
			return false;
		}
		if (board[row][col] != '.') {
			int rowX = row;
			int colY = col + 1;
			if (col == board[0].length - 1) {
				rowX = rowX + 1;
				colY = 0;
			}
			if (row == board.length - 1 && col == board[0].length - 1) {
				return true;
			}
			System.out.format("Found digit at (%s,%s), procedding to (%s,%s)\n", row, col, rowX, colY);
			return solveSudoku(board, rowX, colY);
		}

		for (char i = '1'; i <= '9'; i++) {
			int rowX = row;
			int colY = col + 1;
			if (col == board[0].length - 1) {
				rowX = rowX + 1;
				colY = 0;
			}
			if (!existsInRow(board, row, i) && !existsInCol(board, col, i) && !existsInSubMatrix(board, row, col, i)) {
				System.out.format("Trying with %s at (%s,%s), procedding to (%s,%s)\n", i, row, col, rowX, colY);
				board[row][col] = i;
				if (row == board.length - 1 && col == board[0].length - 1) {
					return true;
				}
				if (solveSudoku(board, rowX, colY)) {
					return true;
				}
				board[row][col] = '.'; // reverse back to dot
				System.out.format("Reversing %s at (%s,%s)\n", i, row, col);
			}
		}
		return false;
	}

	private boolean existsInRow(char[][] board, int row, char value) {
		for (int i = 0; i < board[0].length; i++) {
			if (board[row][i] == value) {
				return true;
			}
		}
		return false;
	}

	private boolean existsInCol(char[][] board, int col, char value) {
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == value) {
				return true;
			}
		}
		return false;
	}

	private boolean existsInSubMatrix(char[][] board, int row, int col, char value) {
		int startX = 3 * (row / 3);
		int startY = 3 * (col / 3);
		for (int i = startX; i < startX + 3; i++) {
			for (int j = startY; j < startY + 3; j++) {
				if (board[i][j] == value) {
					return true;
				}
			}
		}
		return false;
	}

	public void printBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public static void main(String[] args) {
		SudokuSolver_37 obj = new SudokuSolver_37();
		// String[] strArray =
		// {"..9748...","7........",".2.1.9...","..7...24.",".64.1.59.",".98...3..","...8.3.2.","........6","...2759.."};
		String[] strArray = { "53..7....", "6..195...", ".98....6.", "8...6...3", "4..8.3..1", "7...2...6", ".6....28.",
				"...419..5", "....8..79" };
		char[][] board = new char[9][9];
		for (int i = 0; i < strArray.length; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = strArray[i].charAt(j);
			}
		}
		obj.printBoard(board);
		obj.solveSudoku(board);
		obj.printBoard(board);
	}

}
