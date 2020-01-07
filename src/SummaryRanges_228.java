import java.util.ArrayList;
import java.util.List;

public class SummaryRanges_228 {

	public List<String> summaryRanges(int[] nums) {
		List<String> res = new ArrayList<>();
		if (nums == null || nums.length == 0) {
			return res;
		}

		int start = nums[0];
		for (int i = 1; i < nums.length; i++) {
			if (nums[i - 1]+1 < nums[i] ) {
				appendRange(res, start, nums[i - 1]);
				start = nums[i];
			}
		}

		appendRange(res, start, nums[nums.length - 1]);
		return res;
	}

	private void appendRange(List<String> res, int start, int end) {

		if (start == end) {
			res.add("" + start);
			return;
		}

		res.add(start + "->" + end);
	}

	public static void main(String[] args) {
		SummaryRanges_228 obj = new SummaryRanges_228();
		int[] nums = { -2147483648, -2147483647, 2147483647 };
		System.out.println(obj.summaryRanges(nums));

	}

}
