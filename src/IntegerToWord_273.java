public class IntegerToWord_273 {

	String[] lt20 = { "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Eleven",
			"Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen" };
	String[] lt90 = { "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety" };
	int[] lt90Numbers = { 20, 30, 40, 50, 60, 70, 80, 90 };
	String[] thousands = { "Thousand", "Million", "Billion" };

	public String numberToWords(int num) {
		StringBuilder sb = new StringBuilder();
		if (num == 0) {
			return "Zero";
		}
		boolean appendSpace = false;
		for (int i = thousands.length - 1; i >= 0; i--) {
			int value = (int) Math.pow(1000, (i + 1));
			if (num >= value) {
				int th = num / value;
				sb.append(numberToWordUtil(th, appendSpace)).append(" ").append(thousands[i]);
				num = num - th * value;
				appendSpace = true;
			}
		}
		sb.append(numberToWordUtil(num, appendSpace));
		return sb.toString();
	}

	private String numberToWordUtil(int num, boolean appendSpace) {
		StringBuilder sb = new StringBuilder();
		if (num >= 100) {
			int hundereds = num / 100;
			sb.append(numberToWordUtil(hundereds, appendSpace)).append(" Hundred");
			num = num - hundereds * 100;
			appendSpace = true;
		}

		for (int i = lt90.length - 1; i >= 0; i--) {
			if (num >= lt90Numbers[i]) {
				if (appendSpace) {
					sb.append(" ");
				}
				 sb.append(lt90[i]);
				 if(num - lt90Numbers[i] > 0 ) {
					 sb.append(" " + lt20[num]);
				 }
				 return sb.toString();
			}
		}
		if (appendSpace) {
			sb.append(" ");
		}
		sb.append(lt20[num]);
		return sb.toString();
	}

	public static void main(String[] args) {
		IntegerToWord_273 obj = new IntegerToWord_273();
		System.out.println(obj.numberToWords(20));

	}

}
