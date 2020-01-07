public class BestMeetingPoint_296 {
	
	public int minTotalDistance(int[][] grid) {
	    int m = grid.length, n = grid[0].length;
	    int[] I = new int[m], J = new int[n];
	    for (int i=0; i<m; ++i)
	        for (int j=0; j<n; ++j)
	            if (grid[i][j] == 1) {
	                ++I[i];
	                ++J[j];
	            }
	    int total = 0;
	    for (int[] K : new int[][]{ I, J }) {
	        int i = 0, j = K.length - 1;
	        while (i < j) {
	            int k = Math.min(K[i], K[j]);
	            total += k * (j - i);
	            if ((K[i] -= k) == 0) ++i;
	            if ((K[j] -= k) == 0) --j;
	        }
	    }
	    return total;
	}

	public static void main(String[] args) {
		BestMeetingPoint_296 obj = new BestMeetingPoint_296();
		int[][] grid = {{1,0,0,0,1},
				        {0,0,0,0,0},
				        {0,0,1,0,0}};
		System.out.println(obj.minTotalDistance(grid));

	}

}
