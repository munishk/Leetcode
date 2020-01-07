public class ReverseWordInString_151 {
	
	 public String reverseWords(String s) {
	        if(s == null || s.trim().isEmpty()) {
	           return s.trim(); 
	        }
	        
	        int len = s.trim().length();
	        char[] charArr = s.trim().toCharArray();
	        reverse(charArr, 0, len-1);
	        
	        int start = 0;
	        for(int i=0; i<len; i++) {
	            if(charArr[i] == ' ') {
	                reverse(charArr, start, i-1);
	                start = i+1;
	            }
	        }
	        
	        //reverse last segment
	        reverse(charArr, start, len-1);
	        return toString(charArr);
	    }
	    
	    private String toString(char[] charArr) {
	        StringBuilder sb = new StringBuilder();
	        int index = 0; 
	        while(index < charArr.length) {
	            if(charArr[index] == ' ') {
	                while(index+1 < charArr.length && charArr[index+1] == ' ') {
	                    index++;
	                }
	                sb.append(' ');
	                index++;
	            }else {
	                sb.append(charArr[index++]);
	            }
	        }
	        return sb.toString();
	    }
	    
	    private void reverse(char[] str, int start, int end) {
	        int low = start; int high = end;
	        while(low < high) {
	           swap(str, low, high);
	           low++;
	           high--;
	        }
	    }
	    
	    private void swap(char[] str, int i, int j) {
	         char tmp = str[i];
	            str[i] = str[j];
	            str[j] = tmp;
	    }

	public static void main(String[] args) {
		ReverseWordInString_151 obj = new ReverseWordInString_151();
		System.out.println(obj.reverseWords("   a   b "));
	}

}
