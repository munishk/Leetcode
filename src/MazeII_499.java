public class MazeII_499 {

	private class PathAndDist {
		int dist;
		String path;

		public PathAndDist(int dist, String path) {
			this.dist = dist;
			this.path = path;
		}
	}

	public String findShortestWay(int[][] maze, int[] ball, int[] hole) {
		PathAndDist res = findShortestWay(maze, ball[0], ball[1], hole, maze.length, maze[0].length);
		return res == null ? "Impossible" : res.path;
	}

	private PathAndDist findShortestWay(int[][] maze, int x, int y, int[] hole, int m, int n) {
		System.out.printf("(%s,%s)\n", x, y);
		if (x < 0 || y < 0 || x >= m || y >= n || maze[x][y] == 1) {
			return null;
		}

		if (x == hole[0] && y == hole[1]) {
			return new PathAndDist(0, "");
		}

		// roll down
		PathAndDist down = null, up = null, left = null, right = null;
		int[] newpos = null;
		newpos = roll(maze, x, y, 'd', hole);
		if (newpos[3] == 0) {
			down = findShortestWay(maze, newpos[0], newpos[1], hole, m, n);
			down = down == null ? null : new PathAndDist(down.dist + newpos[2], down.path + 'd');
		}

		// roll up
		newpos = roll(maze, x, y, 'u', hole);
		if (newpos[3] == 0) {
			up = findShortestWay(maze, newpos[0], newpos[1], hole, m, n);
			up = up == null ? null : new PathAndDist(up.dist + newpos[2], up.path + 'u');
		}

		// roll left
		newpos = roll(maze, x, y, 'l', hole);
		if (newpos[3] == 0) {
			left = findShortestWay(maze, newpos[0], newpos[1], hole, m, n);
			left = left == null ? null : new PathAndDist(left.dist + newpos[2], left.path + 'l');
		}

		// roll right

		newpos = roll(maze, x, y, 'r', hole);
		if (newpos[3] == 0) {
			right = findShortestWay(maze, newpos[0], newpos[1], hole, m, n);
			right = right == null ? null : new PathAndDist(right.dist + newpos[2], right.path + 'r');
		}

		return min(min(up, down), min(left, right));
	}

	private PathAndDist min(PathAndDist first, PathAndDist second) {
		if (first == null && second == null) {
			return null;
		}

		if (first == null) {
			return second;
		}

		if (second == null) {
			return first;
		}

		return first.dist == second.dist ? first.path.compareTo(second.path) < 0 ? first : second
				: first.dist < second.dist ? first : second;
	}

	private int[] roll(int[][] maze, int x, int y, char direction, int[] hole) {
		int dist = 0;
		int destX;
		int destY;
		int skip = 0;
		destX = x;
		destY = y;
		switch (direction) {
		case 'u':
			if (x == 0) {
				skip = 0;
			} else {
				for (destX = x - 1; destX >= 0; destX--) {
					if (maze[destX][y] == 1) {
						skip = 1;
						break;
					}

					if (hole[0] == destX && hole[1] == y) {
						break;
					}
					dist++;
				}
			}
			break;

		case 'd':
			if (x == maze.length - 1) {
				skip = 1;
			} else {
				for (int i = x + 1; i < maze.length; i++) {
					if (maze[i][y] == 1) {
						skip = 1;
						break;
					}

					if (hole[0] == i && hole[1] == y) {
						break;
					}

					dist++;
					destX = i;
				}
			}
			break;

		case 'l':
			if (y == 0) {
				skip = 1;
			} else {
				for (int i = y - 1; i >= 0; i--) {
					if (maze[x][i] == 1) {
						skip = 1;
						break;
					}
					if ((hole[0] == x && hole[i] == i)) {
						break;
					}
					dist++;
					destY = i;
				}
			}
			break;

		case 'r':
			if (y == maze[0].length) {
				skip = 1;
			} else {
				for (int i = y + 1; i < maze[0].length; i++) {
					if (maze[x][i] == 1) {
						skip = 1;
						break;
					}

					if (hole[0] == x && hole[i] == i) {
						break;
					}
					dist++;
					destY = i;
				}
			}
			break;
		default:
			throw new IllegalArgumentException("illegal argument");
		}
		return new int[] { destX, destY, dist, skip };
	}

	public static void main(String[] args) {
		MazeII_499 obj = new MazeII_499();
		int[][] maze = { { 0, 0, 0, 0, 0 }, { 1, 1, 0, 0, 1 }, { 0, 0, 0, 0, 0 }, { 0, 1, 0, 0, 1 },
				{ 0, 1, 0, 0, 0 } };
		int[] ball = { 4, 3 };
		int[] hole = { 0, 1 };
		System.out.println(obj.findShortestWay(maze, ball, hole));

	}

}
