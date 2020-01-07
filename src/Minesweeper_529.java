public class Minesweeper_529 {

	int[] rows = { -1, -1, -1, 1, 1, 1, 0, 0 };
	int[] cols = { -1, 0, 1, -1, 0, 1, -1, 1 };

	public char[][] updateBoard(char[][] board, int[] click) {
		int x = click[0];
		int y = click[1];
		updateBoard(board, x, y);
		return board;
	}

	private void updateBoard(char[][] board, int x, int y) {
		if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
			return;
		}

		if (board[x][y] == 'M') {
			board[x][y] = 'X';
			return;
		}

		if (board[x][y] == 'E') {
			int mines = countMines(board, x, y);
			if (mines > 0) {
				board[x][y] = (char) (mines + '0');
				return;
			}

			board[x][y] = 'B';
			for (int i = 0; i < 8; i++) {
				int newX = x + rows[i];
				int newY = y + cols[i];
				updateBoard(board, newX, newY);
			}
		}

	}

	private int countMines(char[][] board, int x, int y) {
		int count = 0;
		for (int i = 0; i < 8; i++) {
			int newX = x + rows[i];
			int newY = y + cols[i];
			if (newX >= 0 && newX < board.length && newY >= 0 && newY < board[0].length && board[newX][newY] == 'M') {
				count++;
			}
		}
		return count;
	}

	public static void main(String[] args) {
		Minesweeper_529 obj = new Minesweeper_529();
		char[][] board = { { 'E', 'E', 'E', 'E', 'E' }, { 'E', 'E', 'M', 'E', 'E' }, { 'E', 'E', 'E', 'E', 'E' },
				{ 'E', 'E', 'E', 'E', 'E' } };

		char[][] updatedBoard = obj.updateBoard(board, new int[] { 3, 0 });

		printBoard(updatedBoard);

	}

	private static void printBoard(char[][] board) {
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[0].length; j++) {
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

}
