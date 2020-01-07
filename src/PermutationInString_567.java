public class PermutationInString_567 {
	
	public boolean checkInclusion(String s1, String s2) {
        int[] chars = new int[26];
        int count = 0;
        for(char c: s1.toCharArray()) {
            chars[c-'a']++;
            count++;
        }
        int[] copy = new int[26];
        for(int i=0; i<s2.length()-s1.length()+1; i++) {
            char c = s2.charAt(i);
            if(chars[c-'a'] > 0) {
              System.arraycopy(chars, 0, copy, 0, 26);
              if(containsAllChar(s2, i, copy, count)) {
                  return true;
              }
            }
        }
        return false;
    }
    
    private boolean containsAllChar(String s2, int index, int[] chars, int count) {
        for(int i=index; i<s2.length(); i++) {
            if(count == 0) {
                return true;
            }
           char c = s2.charAt(i);
            if(chars[c-'a'] > 0) {
                chars[c-'a']--;
                count--;
            }else {
                return false;
            }
        }
        return count == 0;
    }

	public static void main(String[] args) {
		PermutationInString_567 obj = new PermutationInString_567();
		System.out.println(obj.checkInclusion("adc", "dcda"));

	}

}
