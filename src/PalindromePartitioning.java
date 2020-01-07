import java.util.ArrayList;
import java.util.List;

public class PalindromePartitioning {

	public List<List<String>> partition(String s) {
		List<List<String>> res = new ArrayList<>();
		partitionUtil(s, 0, s.length() - 1, new ArrayList<>(), res);
		return res;
	}

	private void partitionUtil(String s, int start, int end, List<String> list, List<List<String>> res) {
		if (start > end) {
			res.add(new ArrayList<>(list));
			return;
		}

		for (int i = start; i <= end; i++) {
			if (isPalindrome(s, start, i)) {
				list.add(s.substring(start, i + 1));
				partitionUtil(s, i + 1, end, list, res);
				list.remove(list.size() - 1);
			}
		}

	}

	private boolean isPalindrome(String s, int start, int end) {
		int left = start, right = end;
		if (left == right) {
			return true;
		}

		while (left < right) {
			if (s.charAt(left) != s.charAt(right)) {
				return false;
			}
			left++;
			right--;
		}
		return true;
	}

	public static void main(String[] args) {
		PalindromePartitioning pp = new PalindromePartitioning();
		System.out.println(pp.partition("aab"));

	}

}
