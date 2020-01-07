public class ClosestPalindrome_564 {
	
	public String nearestPalindromic(String n) {
        if(n == null || n.length() == 0) {
            return "";
        }
        
        if(n.length() == 1) {
          return String.valueOf(Integer.parseInt(n)-1);
        }
        
        if(n.length() == 2) {
            if(n.equals("10")) { //special case
                return "9";
            }
                char curr = n.charAt(0);
                char prev = (char)(curr-1);
                char next = (char)(curr+1);
                String prevStr = "" + curr + curr;
                String nextStr = "" + next + next;
                return Integer.parseInt(n) - Integer.parseInt(prevStr) <= Integer.parseInt(nextStr) - Integer.parseInt(n) ? prevStr : nextStr;
        }
        return n.charAt(0) + nearestPalindromic(n.substring(1, n.length()-1)) + n.charAt(0); 
    }

	public static void main(String[] args) {
		ClosestPalindrome_564 obj = new ClosestPalindrome_564();
		for(int i=1; i<100; i++) {
		   System.out.println(String.valueOf(i) + "->" + obj.nearestPalindromic(String.valueOf(i)));
		}
	}

}
