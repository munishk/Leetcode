public class LongestValidParentesis_32 {
	
	 public int longestValidParentheses(String s) {
	        if(s == null || s.isEmpty()) {
	            return 0;
	        }
	        
	        if(isValid(s)) {
	            return s.length();
	        }
	        
	        return Math.max(longestValidParentheses(s.substring(0, s.length()-1)), longestValidParentheses(s.substring(1, s.length())));
	    }
	    
	    private boolean isValid(String s) {
	        int count = 0;
	        for(int i=0; i<s.length(); i++) {
	            char c = s.charAt(i);
	            if(c == '(') {
	                count++;
	            }else {
	                count--;
	            }
	            if(count < 0) {
	                return false;
	            }
	        }
	        return count == 0;
	    }

	public static void main(String[] args) {
		LongestValidParentesis_32 obj = new LongestValidParentesis_32();
		String s  = ")(()(()(((())(((((()()))((((()()(()()())())())()))()()()())(())()()(((()))))()((()))(((())()((()()())((())))(())))())((()())()()((()((())))))((()(((((()((()))(()()(())))((()))()))())";
		System.out.println(obj.longestValidParentheses(s));

	}

}
