import java.util.HashMap;
import java.util.Map;

public class MaxPointsOnLine_149 {

	static class Point {
		int x, y;

		public Point(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}

	public int maxPoints(Point[] points) {
		if (points.length < 2) {
			return points.length;
		}
		Map<Integer, Map<Integer, Integer>> map = new HashMap<>();
		int max = 0;
		for (int i = 0; i < points.length - 1; i++) {
			map.clear();
			int currMax = 0, overlaping = 0, vertical = 0;
			for (int j = i + 1; j < points.length; j++) {
				Point p1 = points[i];
				Point p2 = points[j];
				if (p1.x == p2.x && p1.y == p2.y) {
					overlaping++;
				} else if (p1.x == p2.x) {
					vertical++;
					currMax = Math.max(currMax, vertical);
				} else {
					int yDiff = p2.y - p1.y;
					int xDiff = p2.x - p1.x;
					int gcd = gcd(xDiff, yDiff);
					yDiff /= gcd;
					xDiff /= gcd;
					if (map.get(xDiff) != null) {
						if (map.get(xDiff).get(yDiff) != null) {
							map.get(xDiff).put(yDiff, map.get(xDiff).get(yDiff) + 1);
						} else {
							map.get(xDiff).put(yDiff, 1);
						}
					} else {
						Map<Integer, Integer> innerMap = new HashMap<>();
						innerMap.put(yDiff, 1);
						map.put(xDiff, innerMap);
					}
					int count = map.get(xDiff).get(yDiff);
					currMax = Math.max(currMax, count);
				}
			}
			max = Math.max(max, currMax + overlaping + 1);
		}
		return max;
	}

	private int gcd(int a, int b) {
		if (a == 0)
			return b;
		return gcd(a % b, a);
	}

	public static void main(String[] args) {
		MaxPointsOnLine_149 obj = new MaxPointsOnLine_149();
		int[][] points = { { -4, -4 }, { -8, -582 }, { -3, 3 }, { -9, -651 }, { 9, 591 } };
		System.out.println(obj.maxPoints(toPoints(points)));
	}

	private static Point[] toPoints(int[][] arr) {
		Point[] points = new Point[arr.length];
		for (int i = 0; i < arr.length; i++) {
			points[i] = new Point(arr[i][0], arr[i][1]);
		}
		return points;
	}

}
