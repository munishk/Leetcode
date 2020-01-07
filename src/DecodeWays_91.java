public class DecodeWays_91 {

	/*
	 * Using DFS approach. Time complexity 2^n
	 */
	public int numDecodingsDFS(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		return numDecodingsDFS(s, 0);
	}

	private int numDecodingsDFS(String s, int index) {
		if (index == s.length()) {
			return 1;
		}
		if (index > s.length()) {
			return 0;
		}
		int sum = 0;
		if (s.charAt(index) - '0' > 0) {
			sum += numDecodingsDFS(s, index + 1);

			if (index + 2 <= s.length()) {
				int charVal = Integer.parseInt(s.substring(index, index + 2));
				if (charVal >= 1 && charVal <= 26) {
					sum += numDecodingsDFS(s, index + 2);
				}
			}
		}

		return sum;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
