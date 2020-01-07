public class WiggleMaxLength {

	public int wiggleMaxLength(int[] nums) {
		if (nums.length < 1) {
			return 0;
		}
		int[] dp = new int[nums.length];
		int[] diff = new int[nums.length];
		if (nums.length < 2) {
			return 1;
		}

		for (int i = 1; i < nums.length; i++) {
			int currDiff = nums[i] - nums[0];
			int currDiffSign = currDiff != 0 ? (currDiff < 0 ? -1 : 1) : 0;
			if (currDiffSign != 0) {
				dp[i] = 2;
			} else {
				dp[i] = 1;
			}
			diff[i] = currDiffSign;
		}

		diff[1] = nums[1] - nums[0];
		diff[1] = diff[1] == 0 ? 0 : (diff[1] < 0 ? -1 : 1);
		for (int i = 2; i < nums.length; i++) {
			for (int j = i - 1; j > 0; j--) {
				int currDiff = nums[i] - nums[j];
				int currDiffSign = currDiff != 0 ? (currDiff < 0 ? -1 : 1) : 0;
				if(currDiffSign == 0) {
					continue;
				}
				if (currDiffSign != diff[j]) {
					if (dp[i] < 1 + dp[j]) {
						dp[i] = 1 + dp[j];
						diff[i] = currDiffSign;
					}
				}
			}
		}
		int max = 1;
		for (int i = 1; i < nums.length; i++) {
			max = Math.max(max, dp[i]);
		}
		return max;
	}

	public static void main(String[] args) {
		WiggleMaxLength sol = new WiggleMaxLength();
		int[] nums = { 1, 2,3,4,5,6,7,8,9 };
		System.out.println(sol.wiggleMaxLength(nums));

	}

}
