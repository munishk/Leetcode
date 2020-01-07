public class PalindromePartitionII_132 {
	
	 public int minCutRecursive(String s) {
	       if(s == null || s.length() < 2 || isPalindrome(s, 0, s.length()-1)) {
	           return 0;
	       }
	       int min = Integer.MAX_VALUE;
	       for(int i=1; i<s.length(); i++) {
	           if(isPalindrome(s, 0, i-1) && isPalindrome(s, i, s.length()-1)) {
	               return 1;
	           }else {
	               min = Math.min(min, 1 + minCutRecursive(s.substring(0,i)) + minCutRecursive(s.substring(i)));
	           }
	       }
	       return min;
	    }
	    
	 
	 public int minCutDP(String s) {
	       if(s == null || isPalindrome(s, 0, s.length()-1)) {
	           return 0;
	       }
	       int n = s.length();
	       int[][] dp = new int[n][n];
	       
	       //Populate for length =1
	       for(int i=0; i<n; i++) {
	           dp[i][i] = 0;
	       }
	       
	       for(int l=2; l<=n; l++) {
	           for(int i=0; i+l <=n; i++) {
	                int k = i +l-1;
	                if(isPalindrome(s, i, k)) {
	                     dp[i][k] = 0;
	                 }else {
	                     dp[i][k] = l-1;
	                     for(int j=i; j<k; j++) {
	                         dp[i][k] = Math.min(dp[i][k], 1 + dp[i][j] + dp[j+1][k]);
	                     }
	                 }
	                 
	               }
	           }
	           return dp[0][n-1];
	       }
    
    private boolean isPalindrome(String s, int low, int high) {
        while(low < high) {
            if(s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }
    
    int minCut(String s) {
    	char[] st = s.toCharArray();
        int n = s.length();
        int[] cut = new int[n+1];  // number of cuts for the first k characters
        for (int i = 0; i <= n; i++) cut[i] = i-1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; i-j >= 0 && i+j < n && st[i-j]==st[i+j] ; j++) // odd length palindrome
                cut[i+j+1] = Math.min(cut[i+j+1],1+cut[i-j]);

            for (int j = 1; i-j+1 >= 0 && i+j < n && st[i-j+1] == st[i+j]; j++) // even length palindrome
                cut[i+j+1] = Math.min(cut[i+j+1],1+cut[i-j+1]);
        }
        return cut[n];
    }
    

	public static void main(String[] args) {
		PalindromePartitionII_132 obj = new PalindromePartitionII_132();
		String s = "abcbm";
		System.out.println(obj.minCut(s));

	}

}
