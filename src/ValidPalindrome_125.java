public class ValidPalindrome_125 {
	
	public boolean isPalindrome(String s) {
        char[] letters = s.toCharArray();
        int low = 0; int high = s.length()-1;
        while(low < high) {
        	char head = letters[low];
        	char tail = letters[high];
            if(Character.isLetterOrDigit(head)) {
                low++;
                continue;
            }
            
            if(Character.isLetterOrDigit(tail)) {
                high--;
                continue;
            }
            
            if(Character.toLowerCase(head) != Character.toLowerCase(tail)) {
                return false;
            }
            high--;
            low++;
            
        }
        return true;
    }

	public static void main(String[] args) {
		ValidPalindrome_125 obj = new ValidPalindrome_125();
		System.out.println(obj.isPalindrome("a."));

	}

}
