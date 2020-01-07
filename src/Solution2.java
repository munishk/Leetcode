public class Solution2 {

	int[][] lockMatrix = { { 1, 2, 3 }, { 4, 5, 6 }, { 7, 8, 9 } };

	public int numberOfPatterns(int m, int n) {
		int count = 0;
		int[] selectedKeys = new int[n];
		for (int keyCount = m; keyCount <= n; keyCount++) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					count += numberOfPatternsUtil(i, j, selectedKeys, 0, keyCount);
				}
			}
		}
		return count;
	}

	private int numberOfPatternsUtil(int x, int y, int[] selectedKeys, int selectedKeyIndex, int keyCount) {
		if (selectedKeyIndex == keyCount - 1) {
			selectedKeys[selectedKeyIndex] = lockMatrix[x][y];
			return 1;
		}

		int count = 0;
		selectedKeys[selectedKeyIndex++] = lockMatrix[x][y];
		int[] xmove = { -1, -1, -1, 0, 0, 1, 1, 1 };
		int[] ymove = { -1, 0, 1, -1, 1, -1, 0, 1 };
		for (int i = 0; i < xmove.length; i++) {
			for (int j = 0; j < ymove.length; j++) {
				int newX = x + xmove[i];
				int newY = y + ymove[i];
				if (valid(newX, newY, selectedKeys, selectedKeyIndex)) {
					count += numberOfPatternsUtil(newX, newY, selectedKeys, selectedKeyIndex, keyCount);
				}
			}
		}
		return count;
	}

	private boolean valid(int x, int y, int[] selectedKeys, int selectedKeyIndex) {
		// boundary check
		if (x < 0 || x > 2 || y < 0 || y > 2) {
			return false;
		}

		// check if key is already selected
		for (int i = 0; i <= selectedKeyIndex; i++) {
			if (selectedKeys[i] == lockMatrix[x][y]) {
				return false;
			}
		}
		return true;
	}
    
	
    void print(int[] selectedKeys, int selectedKeyIndex) {
    	StringBuilder sb = new StringBuilder();
    	sb.append(selectedKeys[0]);
    	for(int i=1; i<=selectedKeyIndex; i++) {
    		sb.append("->").append(selectedKeys[i]);
    	}
    	System.out.println(sb);
    }

	public static void main(String[] args) {
		Solution2 sol = new Solution2();
		System.out.println(sol.numberOfPatterns(1, 2));

	}

}
