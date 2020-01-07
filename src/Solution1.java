public class Solution1 {

	private boolean insideBoundary(int x, int y, int[][] rooms) {
		return x >= 0 && x < rooms.length && y >= 0 && y < rooms[0].length;
	}

	static class TreeNode {
		int val;
		TreeNode left, right;

		TreeNode(int val) {
			this.val = val;
		}

	}

	public TreeNode upsideDownBinaryTree(TreeNode root) {
		return upsideDownBinaryTreeUtil(root, null, null);
	}

	private TreeNode upsideDownBinaryTreeUtil(TreeNode root, TreeNode parent, TreeNode right) {
		if (root == null && parent != null) {
			return parent;
		}
		if (root == null) {
			return root;
		}

		TreeNode newRoot = upsideDownBinaryTreeUtil(root.left, root, root.right);
		root.left = right;
		root.right = parent;
		return newRoot;
	}

	static class CounterHolder {
		int counter = 0;

		void incrementCounter() {
			counter++;
		}

		int getCounter() {
			return counter;
		}
	}

	public int kthSmallest(TreeNode root, int k) {
		return kthSmallest(root, k, new CounterHolder());
	}

	private int kthSmallest(TreeNode root, int k, CounterHolder holder) {
		if (root == null) {
			return -1;
		}

		int res = kthSmallest(root.left, k, holder);
		if (res > -1) {
			return res;
		}

		holder.incrementCounter();
		if (holder.getCounter() == k) {
			return root.val;
		}

		return kthSmallest(root.right, k, holder);
	}

	public int combinationSum4(int[] nums, int target) {
		if (target == 0) {
			return 1;
		}

		if (target < 0) {
			return 0;
		}
		int count = 0;
		for (int i = 0; i < nums.length; i++) {
			count += combinationSum4(nums, target - nums[i]);
		}
		return count;
	}

	public int combinationSum41(int[] nums, int target) {
		int[] table = new int[target + 1];
		table[0] = 1;
		for (int i = 1; i <= target; i++) {
			for (int j = 0; j < nums.length; j++) {
				if (i >= nums[j]) {
					table[i] += table[i - nums[j]];
				}
			}
		}
		return table[target];
	}

	public int rob(TreeNode root) {
		if (root == null) {
			return 0;
		}

		int incl = root.val;
		if (root.left != null) {
			incl += rob(root.left.left);
			incl += rob(root.left.right);
		}
		if (root.right != null) {
			incl += rob(root.right.left);
			incl += rob(root.right.right);
		}

		int excl = rob(root.left) + rob(root.right);
		return Math.max(incl, excl);
	}

	static int INF = Integer.MAX_VALUE;

	public void wallsAndGates(int[][] rooms) {
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[0].length; j++) {
				if (rooms[i][j] == INF) {
					boolean[][] visited = new boolean[rooms.length][rooms[0].length];
					wallAndGateUtil(i, j, rooms, visited);
				}
			}
		}
	}

	private int wallAndGateUtil(int x, int y, int[][] rooms, boolean[][] visited) {
		visited[x][y] = true;
		if (rooms[x][y] < INF) {
			return rooms[x][y];
		}

		int down = INF;
		if (insideBoundary(x + 1, y, rooms) && rooms[x + 1][y] != -1 && !visited[x + 1][y]) {
			down = wallAndGateUtil(x + 1, y, rooms, visited);
		}

		int up = INF;
		if (insideBoundary(x - 1, y, rooms) && rooms[x - 1][y] != -1 && !visited[x - 1][y]) {
			up = wallAndGateUtil(x - 1, y, rooms, visited);
		}

		int right = INF;
		if (insideBoundary(x, y + 1, rooms) && rooms[x][y + 1] != -1 && !visited[x][y + 1]) {
			right = wallAndGateUtil(x, y + 1, rooms, visited);
		}

		int left = INF;
		if (insideBoundary(x, y - 1, rooms) && rooms[x][y - 1] != -1 && !visited[x][y - 1]) {
			left = wallAndGateUtil(x, y - 1, rooms, visited);
		}

		int minDist = min(up, down, right, left);
		if (minDist < INF) {
			minDist++;
			rooms[x][y] = minDist;
		}
		return minDist;
	}

	int min(int top, int down, int right, int left) {
		return Math.min(Math.min(top, down), Math.min(right, left));

	}

	
	public int  digitToLetterComb(String digits) {
		if(digits.length() == 1) {
			return 1;
		}
		
		int count  = digitToLetterComb(digits.substring(1));
		if(Integer.parseInt(digits.substring(0, 2)) <= 26) {
			count+=digitToLetterComb(digits.substring(2));
		}
		return count;
	}
	public static void main(String[] args) {
		Solution1 sol = new Solution1();
		int[] nums = { 1, 2, 3 };
		System.out.println(sol.combinationSum41(nums, 4));

		//
		//
		// TreeNode root = new TreeNode(1);
		//
		// TreeNode two = new TreeNode(2);
		// TreeNode three = new TreeNode(3);
		// TreeNode four = new TreeNode(4);
		// TreeNode five = new TreeNode(5);
		//
		// root.right = three;
		// root.left = two;
		// two.left = four;
		// two.right = five;
		//
		// TreeNode res = sol.upsideDownBinaryTree(root);
		// System.out.println(res);

		// TreeNode root = new TreeNode(50);
		// TreeNode thirty = new TreeNode(30);
		// TreeNode seventy = new TreeNode(70);
		// TreeNode twenty = new TreeNode(20);
		// TreeNode forty = new TreeNode(40);
		// TreeNode sixty = new TreeNode(60);
		// TreeNode eighty = new TreeNode(80);
		//
		// root.left = thirty;
		// root.right = seventy;
		// thirty.left = twenty;
		// thirty.right = forty;
		//
		// seventy.left = sixty;
		// seventy.right = eighty;
		//
		//// for(int i=1; i<=7; i++) {
		//// System.out.println(i + ":" + sol.kthSmallest(root, 5));
		//// }
		//
		// System.out.println(sol.rob(root));
//		int[][] rooms = { { INF, -1, 0, INF }, { INF, INF, INF, -1 }, { INF, -1, INF, -1 }, { 0, -1, INF, INF, } };
//		sol.wallsAndGates(rooms);
//		print(rooms);

		int[][] input = {
				{0, INF, -1, INF, INF, -1, -1, 0, 0, -1, INF, INF, 0, -1, INF, INF, INF, INF, 0, INF, 0, -1, -1, -1,
						-1, INF, -1, -1, INF, INF, -1, -1, 0, 0, -1, 0, 0, 0, INF, 0, INF, -1, -1, 0, -1, 0, 0, 0,
						INF },
				{INF, 0, -1, INF, 0, -1, -1, -1, -1, 0, 0, INF, INF, -1, -1, INF, -1, -1, INF, INF, -1, 0, -1, INF, 0,
						INF, -1, INF, 0, INF, 0, INF, -1, INF, 0, INF, -1, INF, 0, INF, INF, 0, -1, INF, -1, -1, -1, 0,
						INF } };
		int[][] output = {{0,1,-1,2,1,-1,-1,0,0,-1,1,3,0,-1,4,3,2,1,0,1,0,-1,-1,-1,-1,2,-1,-1,1,2,-1,-1,0,0,-1,0,0,0,1,0,2,-1,-1,0,-1,0,0,0,1},{1,0,-1,1,0,-1,-1,-1,-1,0,0,2,1,-1,-1,4,-1,-1,1,2,-1,0,-1,1,0,1,-1,1,0,1,0,1,-1,1,0,1,-1,1,0,1,1,0,-1,1,-1,-1,-1,0,1}};
		
		int[][] expected = {{0,1,-1,2,1,-1,-1,0,0,-1,1,1,0,-1,4,3,2,1,0,1,0,-1,-1,-1,-1,2,-1,-1,1,2,-1,-1,0,0,-1,0,0,0,1,0,1,-1,-1,0,-1,0,0,0,1},{1,0,-1,1,0,-1,-1,-1,-1,0,0,1,1,-1,-1,4,-1,-1,1,2,-1,0,-1,1,0,1,-1,1,0,1,0,1,-1,1,0,1,-1,1,0,1,1,0,-1,1,-1,-1,-1,0,1}};

		System.out.println("Input:");
		print(input);
		
		System.out.println("Output:");
		print(output);
		
		System.out.println("Expected:");
		print(expected);
	}

	static void print(int[][] rooms) {
		for (int i = 0; i < rooms.length; i++) {
			for (int j = 0; j < rooms[0].length; j++) {
				System.out.format("%4s", (rooms[i][j] == INF ? "INF" : rooms[i][j]));
			}
			System.out.println();
		}
	}

}
