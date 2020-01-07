public class Solution390 {
	
	 public int lastRemaining(int n) {
	        return lastRemaining(1, n, n, 1, true);
	    }
	    
	    private int lastRemaining(int start, int end, int remaining, int diff, boolean left) {
	        if(remaining == 1) {
	            return start;
	        }
	        
	        if(remaining == 2) {
	            if(left) {
	                return end;
	            }else {
	                return start;
	            }
	        }
	        int res = 0;
	        if(left) {
	            if(remaining %2 == 0) {
	                //even count
	            res = lastRemaining(start+diff, end, remaining/2, diff*2, !left);
	            }else {
	                //odd count
	                res = lastRemaining(start+diff, end-diff, remaining/2, diff*2, !left);
	            }
	        }else {
	           if(remaining %2 == 0) {
	                //even count
	            res = lastRemaining(start, end-diff, remaining/2, diff*2, !left);
	            }else {
	                //odd count
	                res = lastRemaining(start+diff, end-diff, remaining/2, diff*2, !left);
	            } 
	        }
	        return res;
	    }

	public static void main(String[] args) {
		Solution390 sol = new Solution390();
		System.out.println(sol.lastRemaining(9));
	}

}
