import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class SnakeGame_353 {

	class Position {
		int x, y;

		Position(int x, int y) {
			this.x = x;
			this.y = y;
		}

		public boolean equals(Object obj) {
			if(obj == null || !(obj instanceof Position)) {
				return false;
			}
			
			Position pos = (Position)obj;
			return obj != null && pos.x == x && pos.y == y;
		}
		public int hashCode() {
			int hash = 31;
			hash = 31 * x + hash;
			hash = 31 * y + hash;
			return hash;
		}
		
		public String toString() {
			return String.format("(%s,%s)", x,y);
		}
	}

	private LinkedList<Position> snake;
	private Set<Position> set;
	private int width, height;
	private int[][] food;
	private int length = 0;

	/**
	 * Initialize your data structure here.
	 * 
	 * @param width
	 *            - screen width
	 * @param height
	 *            - screen height
	 * @param food
	 *            - A list of food positions E.g food = [[1,1], [1,0]] means the
	 *            first food is positioned at [1,1], the second is at [1,0].
	 */
	public SnakeGame_353(int width, int height, int[][] food) {
		this.width = width;
		this.height = height;
		this.food = food;
		this.snake = new LinkedList<>();
		snake.addFirst(new Position(0, 0));

		this.set = new HashSet<>();
		set.add(new Position(0, 0));
	}

	/**
	 * Moves the snake.
	 * 
	 * @param direction
	 *            - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
	 * @return The game's score after the move. Return -1 if game over. Game
	 *         over when snake crosses the screen boundary or bites its body.
	 */
	public int move(String direction) {
		// If game is already over
		if (length == -1) {
			return -1;
		}

		// Get snake's head
		Position head = snake.get(0);
		Position newPos = calculateNewPosition(head, direction);
		if (!withinBoundary(newPos) || set.contains(newPos)) {
			length = -1;
			return length;
		}

		snake.addFirst(newPos);
		set.add(newPos);
		if (length < food.length && food[length][0] == newPos.x && food[length][1] == newPos.y) {
			length++;
		} else {
			Position pos = snake.removeLast();
			set.remove(pos);
		}

		return length;
	}

	private boolean withinBoundary(Position pos) {
		return pos.x >= 0 && pos.x < height && pos.y >= 0 && pos.y < width;
	}

	private Position calculateNewPosition(Position curr, String direction) {
		switch (direction) {
		case "U":
			return new Position(curr.x - 1, curr.y);
		case "D":
			return new Position(curr.x + 1, curr.y);
		case "L":
			return new Position(curr.x, curr.y - 1);
		case "R":
			return new Position(curr.x, curr.y + 1);
		default:
			throw new IllegalArgumentException();
		}
	}

	public static void main(String[] args) {
		int[][] food = { { 2, 0 }, { 0, 0 }, { 0, 2 }, { 0, 1 }, { 2, 2 }, { 0, 1 } };
		SnakeGame_353 obj = new SnakeGame_353(3, 3, food);
		String moves = "DDRUULDRRULLDRU";
		StringBuilder res = new StringBuilder();
		for (int i = 0; i < moves.length(); i++) {
			res.append(obj.move(moves.substring(i, i + 1))).append(' ');
		}
		System.out.println(res);
	}

}
