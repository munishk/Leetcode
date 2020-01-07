public class DistinctSubsequence {
	
	 public int numDistinct(String s, String t) {
	        if(t.length() > s.length()) {
	            return 0;
	        }
	        return numDistinct(t, t.length(), s, s.length());
	    }
	    
	    /*
	     * Recursive approach.
	     */
	    private int numDistinct(String t, int m, String s, int n) {
	        if(m == 0) {
	            return 1;
	        }
	        
	        if(n == 0 || m > n) {
	            return 0;
	        }
	        
	        int res = 0;
	        for(int i=n; i>=m; i--) {
	            if(t.charAt(m-1) == s.charAt(i-1)) {
	                res+=numDistinct(t, m-1, s, i-1);
	            }
	        }
	      return res;
	    }

	    /*
	     * Using DP approach. Time complexity: O(m*n*n)
	     */
	    public int numDistinctDP(String s, String t) {
	        int m = t.length();
	        int n = s.length();
	        
	        if(m > n) {
	            return 0;
	        }
	        int[][]dp = new int[m+1][n+1];
	        dp[0][0] = 1;
	        
	        for(int i=1; i<=m; i++) {
	            for(int j=1; j<=n; j++) {
	                if(t.charAt(i-1) == s.charAt(j-1)) {
	                   for(int k=j-1; k>=0; k--) {
	                          dp[i][j]+=dp[i-1][k];
	                   }
	                }
	            }
	        }
	        int count = 0;
	        for(int i=0; i<=n; i++) {
	            count+=dp[m][i];
	        }
	        return count;
	     }
	    
	    
	public static void main(String[] args) {
		DistinctSubsequence obj = new DistinctSubsequence();
		String s = "aabb";
		String t = "ab";
		System.out.println(obj.numDistinctDP(s, t));

	}

}
