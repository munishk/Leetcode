public class LongestPalindromeSubstring_5 {
	
	/*
	 * Using Dynamic programming with n^2 space and n^2 complexity. n = length of substring
	 */
	public String longestPalindrome(String s) {
        int n = s.length();
       int[][] dp = new int[n][n];
       for(int i=0; i<n; i++) {
           dp[i][i] = 1;
       }
       
       int start = 0, end = 0;
       for(int j=1; j<n; j++) {
           for(int i=j-1; i>=0; i--) {
               if(s.charAt(i) == s.charAt(j) ) {
                   dp[i][j] = 2 + dp[i+1][j-1];
               }else {
                   dp[i][j] = Math.max(dp[i][j-1], dp[i+1][j]);
               }
               
               if(dp[i][j] > end-start+1) {
                   start = i;
                   end = j;
               }
           }
       }
       return s.substring(start, end+1);
    }
    
	/*
	 * Using recursion. runtime complexity - 2^n  where n - length of string.
	 */
    private String longestPalindrome(String s, int start, int end) {
        if(isPalindrome(s, start, end)) {
            return s.substring(start, end+1);
        }
        
        String left = longestPalindrome(s, start, end-1);
        String right = longestPalindrome(s, start+1, end);
       return left.length() > right.length() ? left : right;
    }
    
    private boolean isPalindrome(String s, int start, int end) {
        int low = start; int high = end;
        while(low < high) {
            if(s.charAt(low) != s.charAt(high)) {
                return false;
            }
            low++;
            high--;
        }
        return true;
    }

	public static void main(String[] args) {
		LongestPalindromeSubstring_5 obj = new LongestPalindromeSubstring_5();
		System.out.println(obj.longestPalindrome("babad"));

	}

}
