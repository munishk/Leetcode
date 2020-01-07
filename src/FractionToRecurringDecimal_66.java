import java.util.HashSet;
import java.util.Set;

public class FractionToRecurringDecimal_66 {

	public String fractionToDecimal(int numerator, int denominator) {
		StringBuilder sb = new StringBuilder();
		int nonFraction = numerator / denominator;
		sb.append(nonFraction);
		int fractionNumerator = numerator % denominator;
		if (fractionNumerator > 0) {
			sb.append('.');
			fractionToDecimal(fractionNumerator, denominator, sb, new HashSet<>(), "");
		}
		return sb.toString();
	}

	private void fractionToDecimal(int fractionNumerator, int denominator, StringBuilder sb, Set<Integer> visited,
			String prefix) {
		if (fractionNumerator == 0) {
			sb.append(prefix);
			return;
		}
		if (visited.contains(fractionNumerator)) {
			sb.append('(').append(prefix).append(')');
			return;
		}

		visited.add(fractionNumerator);
		int decimalDigit = (fractionNumerator * 10) / denominator;
		fractionToDecimal((fractionNumerator * 10) % denominator, denominator, sb, visited, prefix + decimalDigit);
	}
	
	public String fractionToDecimal_2(int numerator, int denominator) {
		StringBuilder sb = new StringBuilder();
		int nonFraction = numerator / denominator;
		sb.append(nonFraction);
		int fractionNumerator = numerator % denominator;
		if (fractionNumerator > 0) {
			sb.append('.');
			fractionToDecimal_2(fractionNumerator, denominator, sb);
		}
		return sb.toString();
	}

	private void fractionToDecimal_2(int fractionNumerator, int denominator, StringBuilder sb) {
        String decimalDigit = "";
       int numerator = fractionNumerator*10;
        while(numerator < denominator) {
            numerator = numerator*10;
            decimalDigit = decimalDigit+ "0";
        }

		decimalDigit = decimalDigit + numerator / denominator;
		int remainder = numerator%denominator;
		
		if(remainder == fractionNumerator) {
		    sb.append('(').append(decimalDigit).append(')');
		    return;
		}
		
		sb.append(decimalDigit);
		if(remainder == 0) {
		    return;
		}

		fractionToDecimal_2(remainder, denominator, sb);
	}

	public static void main(String[] args) {
		FractionToRecurringDecimal_66 obj = new FractionToRecurringDecimal_66();
		System.out.println(obj.fractionToDecimal(1, 17));
		
		System.out.println(obj.fractionToDecimal_2(1, 17));

	}

}
