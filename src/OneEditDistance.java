public class OneEditDistance {
	
	 public boolean isOneEditDistance(String s, String t) {
	        return calculateDistance(s, s.length(), t, t.length()) == 1;
	    }
	    /*
	     * Calculate edit distance recursively. Time limit exceeds for larger input
	     */
	    private int calculateDistance(String s, int m,  String t, int n) {
	        if(m == 0 && n ==0) {
	            return 0;
	        }
	        
	        if(m == 0) {
	            return n;
	        }
	        
	        if(n ==0) {
	            return m;
	        }
	        
	        if(s.charAt(m-1) == t.charAt(n-1)) {
	            return calculateDistance(s, m-1, t, n-1);
	        }else {
	           return 1 + Math.min(calculateDistance(s, m, t, n-1), Math.min(calculateDistance(s,m-1, t,n), calculateDistance(s,m-1,t,n-1)));
	        }
	    }
	    
	    /*
	     * Memory limit exceeds for larger input
	     */
	    private int calculateDistanceDp(String s, int m,  String t, int n) {
	        int[][]dp = new int[m+1][n+1];
	        for(int i=0; i<=m; i++) {
	        	dp[i][0] =i;
	        }
	        
	        for(int i=0;i<=n;i++) {
	        	dp[0][i] =i;
	        }
	        
	        for(int i=1; i<=m; i++) {
	        	for(int j=1; j<=n; j++) {
	        		if(s.charAt(i-1) == t.charAt(j-1)) {
	        			dp[i][j] = dp[i-1][j-1];
	        		}else {
	        			dp[i][j] = 1 + Math.min(dp[i][j-1], Math.min(dp[i-1][j], dp[i-1][j-1]));
	        		}
	        	}
	        }
	        return dp[m][n];
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
