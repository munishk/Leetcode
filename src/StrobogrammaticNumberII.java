import java.util.ArrayList;
import java.util.List;

public class StrobogrammaticNumberII {

	char[] single = { '0', '8', '1' };

	public List<String> findStrobogrammatic(int n) {
		List<String> res = new ArrayList<>();
		findStrobogrammatic(new char[n], 0, n - 1, res);
		return res;
	}

	private void findStrobogrammatic(char[] charArray, int low, int high, List<String> res) {
		if (low > high) {
			res.add(new String(charArray));
			return;
		}

		if (low == high) {
			for (char c : single) {
				charArray[low] = c;
				res.add(new String(charArray));
			}
			return;
		}

		for (char c : single) {
			charArray[low] = charArray[high] = c;
			findStrobogrammatic(charArray, low + 1, high - 1, res);
		}
		// try 6***9
		charArray[low] = '6';
		charArray[high] = '9';
		findStrobogrammatic(charArray, low + 1, high - 1, res);

		// try 9**6
		charArray[low] = '9';
		charArray[high] = '6';
		findStrobogrammatic(charArray, low + 1, high - 1, res);
	}

	public static void main(String[] args) {
		StrobogrammaticNumberII obj = new StrobogrammaticNumberII();
		System.out.println(obj.findStrobogrammatic(2));

	}

}
