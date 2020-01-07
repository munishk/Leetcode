public class PerfectSquareCount {

	public int numSquares(int n, String val) {
		if (Math.floor(Math.sqrt(n)) == Math.sqrt(n)) {
			System.out.println(val + n);
			return 1;
		}

		int sqrt = (int) Math.sqrt(n);
		int minCount = Integer.MAX_VALUE;
		for (int i = sqrt; i >= 1; i--) {
			int count = 1 + numSquares(n - i * i,  val+i*i );
			minCount = Math.min(minCount, count);
		}
		return minCount;
	}
	
	 public int numSquaresDp(int n) {
	        int[] dp = new int[n+1];
	        dp[1] = 1;
	        for(int i=2; i<=n; i++) {
	           int sqrt = (int) Math.sqrt(i);
	           if(sqrt * sqrt == i) { //perfect square
	               dp[i] = 1;
	           }else {
	               dp[i] = Integer.MAX_VALUE;
	               for(int j=sqrt; j>=1; j--) {
	                   dp[i] = Math.min(dp[i], 1 + dp[i- sqrt*sqrt]);
	               }
	           }
	        }
	        return dp[n];
	    }

	public static void main(String[] args) {
		PerfectSquareCount sol = new PerfectSquareCount();
		//System.out.println(sol.numSquares(13, ""));
		System.out.println(sol.numSquaresDp(12));
	}

}
