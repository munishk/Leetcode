public class CountRepetition_466 {
	
	public int getMaxRepetitions(String s1, int n1, String s2, int n2) {
        return n1/n2 * repetitionCount(s1, n1, s2, n2);
    }
    
    private int repetitionCount(String s1, int n1, String s2, int n2) {
        int j = 0; int m = 0; int n=1;
        for(int i=0; i<s1.length(); i++) {
             if(s1.charAt(i) == s2.charAt(j)) {
                 j++;
             }
             if(j == s2.length()) {
                 m++;
                 j = 0;
             }
             
             if(i == s1.length()-1 && n == 0) {
                 i = 0;
                 n++;
             }
        }
        return m/n;
    }

	public static void main(String[] args) {
		CountRepetition_466 obj = new CountRepetition_466();
		System.out.println(obj.getMaxRepetitions("acb", 4, "ab", 2));

	}

}
