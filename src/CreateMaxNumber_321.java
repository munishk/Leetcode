public class CreateMaxNumber_321 {

	public int[] maxNumber(int[] nums1, int[] nums2, int k) {
		int m = nums1.length;
		int n = nums2.length;
		int[] res = new int[k];
		for (int i = Math.max(0, k - n); i <= k && i <= m; i++) {
			int[] candidate = merge(maxArray(nums1, i), maxArray(nums2, k - i));
			if (greater(candidate, 0, res, 0))
				res = candidate;
		}
		return res;
	}

	private int[] merge(int[] nums1, int[] nums2) {
		int m = nums1.length;
		int n = nums2.length;
		int[] res = new int[m + n];
		int r = 0;
		int i = 0;
		int j = 0;
		while (i < m && j < n) {
			if (greater(nums1, i, nums2, j)) {
				res[r++] = nums1[i++];
			} else {
				res[r++] = nums2[j++];
			}
		}

		while (i < m) {
			res[r++] = nums1[i++];
		}

		while (j < n) {
			res[r++] = nums2[j++];
		}
		return res;
	}

	private boolean greater(int[] nums1, int i, int[] nums2, int j) {
		while (i < nums1.length && j < nums2.length && nums1[i] == nums2[j]) {
			i++;
			j++;
		}

		return j == nums2.length || (i < nums1.length && nums1[i] > nums2[j]);
	}

	private int[] maxArray(int[] nums, int k) {
		int j = 0;
		int[] res = new int[k];
		for (int i = 0; i < nums.length; i++) {
			while (j > 0 && res[j - 1] < nums[i] && nums.length - i + j > k) {
				j--;
			}
			if (j < k)
				res[j++] = nums[i];
		}
		return res;
	}

	public static void main(String[] args) {
		int[] nums1 = { 4,9,5 };
		int[] nums2 = {8,7,4};
		CreateMaxNumber_321 obj = new CreateMaxNumber_321();
		int[] res = obj.maxNumber(nums1, nums2, 3);
		for (int val : res) {
			System.out.print(val + " ");
		}

	}

}
