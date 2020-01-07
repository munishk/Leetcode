public class ShortestPalindrome_214 {

	public String shortestPalindrome(String s) {
		return shortestPalindrome(s, 0, s.length() - 1);
	}

	private String shortestPalindrome(String s, int m, int n) {
		System.out.printf("(%s,%s)\n", m,n);
		if (m > n) {
			return "";
		}
		if (m == n) {
			return "" + s.charAt(m);
		}

		if (s.charAt(m) == s.charAt(n)) {
			return s.charAt(m) + shortestPalindrome(s, m + 1, n - 1) + s.charAt(n);
		}

		return s.charAt(n) + shortestPalindrome(s, m, n - 1) + s.charAt(n);
	}

	public static void main(String[] args) {
		ShortestPalindrome_214 obj = new ShortestPalindrome_214();
		String s = generateString(6700);
		System.out.println(obj.shortestPalindrome(s));
	}
	
	private static String generateString(int count) {
		StringBuilder  sb = new StringBuilder();
		for(int i=0; i<count; i++) {
			sb.append('a');
		}
		return sb.toString();
	}

}
