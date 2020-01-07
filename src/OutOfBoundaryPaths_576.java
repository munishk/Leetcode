import util.Timer;

public class OutOfBoundaryPaths_576 {
	
	//Recursive way. Complexity O(4^n)
	 public int findPaths(int m, int n, int N, int i, int j) {
	        if(isOutside(m, n, i, j)) {
	            return 1;
	        }
	        if(N ==0) {
	            return 0;
	        }
	        
	        return findPaths(m, n, N-1, i-1, j) //up
	             + findPaths(m, n, N-1, i+1, j) //down
	             + findPaths(m, n, N-1, i, j-1) //left
	             + findPaths(m, n, N-1, i, j+1); //right
	    }
	   
	    private boolean isOutside(int m, int n, int i, int j) {
	        return i<0 || i>=m || j<0||j>=n;
	    }
	    
	    public int findPathsDP(int m, int n, int N, int x, int y) {
	        long[][][] arr = new long[N+1][m][n];
	        for(int l=1; l<=N; l++) {
	            for(int i=0; i<m; i++) {
	                for(int j=0; j<n; j++) {
	                    arr[l][i][j] = ((i== 0?1:arr[l-1][i-1][j]) + 
	                                   (i == m-1?1:arr[l-1][i+1][j]) +
	                                   (j == 0?1:arr[l-1][i][j-1]) + 
	                                   (j == n-1? 1: arr[l-1][i][j+1]))%1000000007;
	                }
	            }
	        }
	        return (int)arr[N][x][y];
	     }
	    
	public static void main(String[] args) {
		OutOfBoundaryPaths_576 obj = new OutOfBoundaryPaths_576();
		
		
		Timer.reset();
		System.out.println(obj.findPathsDP(8,50,23,5,26));
		Timer.finish();

	}

}
