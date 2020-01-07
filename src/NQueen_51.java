import java.util.ArrayList;
import java.util.List;

public class NQueen_51 {

	public List<List<String>> solveNQueens(int n) {
		int[][] board = new int[n][n];
		List<List<String>> res = new ArrayList<>();
		solve(board, 0, res);
		return res;
	}

	private void solve(int[][] board, int col, List<List<String>> res) {
		if (col == board.length) {
			res.add(toList(board));
		}

		for (int i = 0; i < board.length; i++) {
			if (isValid(board, i, col)) {
				board[i][col] = 1;
				solve(board, col + 1, res);
				board[i][col] = 0;
			}
		}
	}

	private boolean isValid(int[][] board, int row, int col) {
		// check if queen is on same row
		for (int j = 0; j < col; j++) {
			if (board[row][j] == 1) {
				return false;
			}
		}

		// check upper diagonal
		for (int i = row - 1, j = col-1;  i >= 0 && j >=0; i--, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}

		//check lower diagonal
		for (int i = row + 1, j = col - 1; j >= 0 && i < board.length; i++, j--) {
			if (board[i][j] == 1) {
				return false;
			}
		}
		return true;
	}

	private List<String> toList(int[][] board) {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < board.length; i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < board[0].length; j++) {
				sb.append(board[i][j] == 1 ? 'Q' : '.');
			}
			list.add(sb.toString());
		}
		return list;
	}

	public static void main(String[] args) {
		NQueen_51 obj = new NQueen_51();
		System.out.println(obj.solveNQueens(4));

	}

}
