import java.util.Set;

public class WordBreak_139 {
	/*
	 * Using DP approach. Runtime complexity O(n^2)
	 */
	public boolean wordBreakDP(String s, Set<String> wordDict) {
	       boolean[] dp = new boolean[s.length()+1];
	       dp[0] = true;
	       for(int i=1; i<=s.length(); i++) {
	           for(int j=0; j<i; j++) {
	               if(dp[j] && wordDict.contains(s.substring(j, i))) {
	                   dp[i] = true;
	               }
	           }
	       }
	       return dp[s.length()];
	        
	    }
	    
	    /*
	     * Using recursion. Runtime complexity: n! x 2^n?
	     */
	    private boolean wordBreakRecursion(String s, int start, int end, Set<String> wordDict) {
	        if(end <= start ) {
	            return false;
	        }
	        
	        if(wordDict.contains(s.substring(start, end))) {
	            return true;
	        }
	        
	        boolean res = false;
	        for(int i=start+1; i<end; i++) {
	           res = res || (wordBreakRecursion(s, start, i, wordDict) && wordBreakRecursion(s, i, end, wordDict));
	        }
	        return res;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
