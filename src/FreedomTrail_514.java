public class FreedomTrail_514 {
	
	 public int findRotateSteps(String ring, String key) {
	        return rotateStepsUtil(ring, key, 0);
	    }
	    
	    public int rotateStepsUtil(String ring, String key, int keyIndex) {
	        if(keyIndex == key.length() || ring ==null || ring.length() == 0) {
	            return 0;
	        }
	        
	        char c = key.charAt(keyIndex);
	        if(ring.charAt(0) == c) {
	            return 1 + rotateStepsUtil(ring, key, keyIndex+1);
	        }
	        
	        int rotation  = rotateAntiClockwise(ring, c);
	        int antiCount = rotation + rotateStepsUtil(ring.substring(rotation) + ring.substring(0, rotation),key,  keyIndex+1);
	        
	        rotation  = rotateClockwise(ring, c);
	        int clockCount = rotation + rotateStepsUtil(ring.substring(ring.length()-rotation) + ring.substring(0, ring.length() -rotation), key, keyIndex+1);
	        
	        return 1 + Math.min(antiCount, clockCount);
	        
	    }
	    
	    private int rotateAntiClockwise(String ring, char c) {
	        int index = 0;
	        while(ring.charAt(index) != c) {
	            index++;
	        }
	        return index;
	    }
	    
	    private int rotateClockwise(String ring, char c) {
	        int index = ring.length()-1;
	        while(ring.charAt(index) != c) {
	            index--;
	        }

	        return ring.length()-index;
	    }
	    
	    public int findRotateStepsDp(String ring, String key) {
	        int n = ring.length();
	        int m = key.length();
	        int[][] dp = new int[m + 1][n];
	        
	        for (int i = m - 1; i >= 0; i--) {
	            for (int j = 0; j < n; j++) {
	                dp[i][j] = Integer.MAX_VALUE;
	                for (int k = 0; k < n; k++) {
	                    if (ring.charAt(k) == key.charAt(i)) {
	                        int diff = Math.abs(j - k);
	                        int step = Math.min(diff, n - diff);
	                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
	                    }
	                }
	            }
	        }
	        
	        return dp[0][0] + m;
	    }

	public static void main(String[] args) {
		FreedomTrail_514 obj = new FreedomTrail_514();
		System.out.println(obj.findRotateSteps("godding", "gd"));
		System.out.println(obj.findRotateStepsDp("godding", "gd"));

	}

}
