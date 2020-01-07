public class AdditiveNumber_306 {

	public boolean isAdditiveNumber(String num) {
		if (num == null || num.length() < 3) {
			return false;
		}
		String first = "";
		String second = "";
		for (int i = 1; i < num.length() - i; i++) {
			first = num.substring(0, i);
			for (int j = 1; j < (num.length() - Math.max(i, j)); j++) {
				if(num.charAt(i) == '0') {
					break;
				}
				second = num.substring(i, i + j);
				boolean valid = isValid(first, second, i+j, num);
				if (valid) {
					System.out.println(first + ":" + second);
					return true;
				}
			}
		}
		return false;
	}

	private boolean isValid(String first, String second, int index, String num) {
		while (index < num.length()) {
			String sum = add(first, second);
			if (index + sum.length() > num.length()) {
				return false;
			}
			if (num.substring(index, index + sum.length()).equals(sum)) {
				first = second;
				second = sum;
				index = index + sum.length();
			} else {
				return false;
			}
		}
		return true;
	}

	private String add(String first, String second) {
		int overflow = 0;
		int m = first.length() - 1;
		int n = second.length() - 1;
		String res = "";
		while (m >= 0 && n >= 0) {
			int a = first.charAt(m) - '0';
			int b = second.charAt(n) - '0';
			int sum = a + b + overflow;
			if (sum >= 10) {
				res = (sum - 10) + res;
				overflow = 1;
			} else {
				res = sum + res;
				overflow = 0;
			}
			m--;
			n--;
		}

		while (m >= 0) {
			int sum = (first.charAt(m) - '0') + overflow;
			if (sum >= 10) {
				res = (sum - 10) + res;
				overflow = 1;
			} else {
				res = sum + res;
				overflow = 0;
			}
			m--;
		}

		while (n >= 0) {
			int sum = (second.charAt(n) - '0') + overflow;
			if (sum >= 10) {
				res = (sum - 10) + res;
				overflow = 1;
			} else {
				res = sum + res;
				overflow = 0;
			}
			n--;
		}

		if (overflow > 0) {
			res = overflow + res;
		}
		return res;
	}

	public static void main(String[] args) {
		AdditiveNumber_306 obj = new AdditiveNumber_306();
		System.out.println(obj.isAdditiveNumber("101020305080130210"));

	}

}
