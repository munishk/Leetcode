public class MinimumDeleteDistance_583 {
	
	public int minDistance(String word1, String word2) {
	       return minDistance(word1, word2, word1.length(), word2.length());
	    }
	    
	    /*
	     * Recursive approach. Complexity : O(2^m+n)
	     */
	    private int minDistance(String word1, String word2, int i, int j) {
	        if(i == 0 && j ==0) {
	            return 0;
	        }
	        
	        if( i ==0) {
	            return j;
	        }
	        
	        if(j ==0) {
	            return i;
	        }
	        
	        if(word1.charAt(i-1) == word2.charAt(j-1)) {
	            return minDistance(word1, word2, i-1, j-1);
	        }
	        return 1 + Math.min(minDistance(word1, word2, i-1, j), minDistance(word1, word2, i, j-1));
	    }
	    
	    /*
	     * Using DP. Complexity: O(mn)
	     */
	    public int minDistanceDP(String word1, String word2) {
	        int m = word1.length();
	        int n = word2.length();
	        
	      int[][] dp = new int[m+1][n+1];
	      for(int i=1; i<=n; i++) {
	          dp[0][i] = i;
	      }
	      
	      for(int i=1; i<=m; i++) {
	          dp[i][0] = i;
	      }
	      
	      for(int i=1; i<=m; i++) {
	          for(int j=1; j<=n; j++) {
	              if(word1.charAt(i-1) == word2.charAt(j-1)) {
	                  dp[i][j] = dp[i-1][j-1];
	              }else {
	                  dp[i][j] = 1 + Math.min(dp[i-1][j], dp[i][j-1]);
	              }
	          }
	      }
	      return dp[m][n];
	    }

	public static void main(String[] args) {
		MinimumDeleteDistance_583 obj = new MinimumDeleteDistance_583();
		System.out.println(obj.minDistanceDP("sea", "eat"));

	}

}
