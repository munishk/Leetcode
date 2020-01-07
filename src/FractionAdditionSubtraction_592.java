import java.util.ArrayList;
import java.util.List;

public class FractionAdditionSubtraction_592 {

	public String fractionAddition(String expression) {
		List<Integer> numerators = new ArrayList<>();
		List<Integer> denominators = new ArrayList<>();
		char c = expression.charAt(0);
		if (Character.isDigit(c)) {
			expression = "+" + expression;
		}

		int index = 0;
		int multiplier = 1;

		while (index < expression.length()) {
			multiplier = 1;
			c = expression.charAt(index++);
			if (c == '-') {
				multiplier = -1;
			}
			int numerator = 0;
			while (expression.charAt(index) != '/') {
				numerator = numerator * 10 + (expression.charAt(index++) - '0');
			}
			numerator = numerator * multiplier;
			index++;
			int denominator = 0;
			while (index < expression.length() && Character.isDigit(expression.charAt(index))) {
				denominator = denominator * 10 + (expression.charAt(index++) - '0');
			}
			numerators.add(numerator);
			denominators.add(denominator);
		}

		Integer[] nums = numerators.toArray(new Integer[0]);
		Integer[] denms = denominators.toArray(new Integer[0]);
		int lcm = lcm(denms);
		int res = 0;
		for (int i = 0; i < nums.length; i++) {
			int lcmRatio = lcm / denms[i];
			res += nums[i] * lcmRatio;
		}

		if (res % lcm == 0) {
			return res + "/" + "1";
		}
		int gcd = gcd(Math.abs(res), lcm);
		res /= gcd;
		lcm /= gcd;

		return res + "/" + lcm;
	}

	private int lcm(Integer[] arr) {
		int res = arr[0];
		for (int i = 1; i < arr.length; i++) {
			res = (res * arr[i]) / gcd(res, arr[i]);
		}
		return res;
	}

	private int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public static void main(String[] args) {
		FractionAdditionSubtraction_592 obj = new FractionAdditionSubtraction_592();
		System.out.println(obj.fractionAddition("1/3-1/2"));
	}

}
