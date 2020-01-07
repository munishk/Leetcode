public class InterleavingString_97 {

	public boolean isInterleave(String s1, String s2, String s3) {
		int m = s1.length();
		int n = s2.length();
		int p = s3.length();

		if (m + n != p) {
			return false;
		}

		int i1 = 0, i2 = 0;
		for (int i = 0; i < p; i++) {
			if (i1 == s1.length()) {
				return s2.substring(i2).equals(s3.substring(i));
			}

			if (i2 == s2.length()) {
				return s1.substring(i1).equals(s3.substring(i));
			}

			if (s1.charAt(i1) != s3.charAt(i) && s2.charAt(i2) != s3.charAt(i)) {
				return false;
			}

			if (s1.charAt(i1) == s2.charAt(i2)) {
				return isInterleave(s1.substring(i1 + 1), s2.substring(i2), s3.substring(i + 1))
						|| isInterleave(s1.substring(i1), s2.substring(i2 + 1), s3.substring(i + 1));
			}

			if (s1.charAt(i1) == s3.charAt(i)) {
				i1++;
			} else {
				i2++;
			}
		}
		return true;
	}
	
	 public boolean isInterleaveDP(String s1, String s2, String s3) {
	      int m = s1.length();
	      int n = s2.length();
	      if(m +n != s3.length()) {
	          return false;
	      }
	      
	      boolean[][] dp = new boolean[m+1][n+1];
	      
	      dp[0][0] = true;
	      //Considering S2 is empty.
	      for(int i=1; i<=m; i++) {
	          dp[i][0] = dp[i-1][0] && s1.charAt(i-1) == s3.charAt(i-1);
	      }
	      
	      //Considering S1 is empty
	      for(int i=1; i<=n; i++) {
	          dp[0][i] = dp[0][i-1] && s2.charAt(i-1) == s3.charAt(i-1);
	      }
	      
	      for(int i=1; i<=m; i++) {
	          for(int j=1;j<=n; j++) {
	              dp[i][j] = dp[i][j-1] && s1.charAt(i-1) == s3.charAt(i+j-1) || dp[i-1][j] && s2.charAt(j-1) == s3.charAt(i+j-1);
	          }
	      }
	      
	      return dp[m][n];
	    }
	  

	public static void main(String[] args) {
		InterleavingString_97 obj = new InterleavingString_97();
		String s1 = "a";
		String s2 = "";
		String s3 = "a";
		System.out.println(obj.isInterleave(s1, s2, s3));
		System.out.println(obj.isInterleaveDP(s1, s2, s3));

	}

}
