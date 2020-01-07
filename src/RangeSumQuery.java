public class RangeSumQuery {

	int[] arr;
	int n;

	public RangeSumQuery(int[] nums) {
	        int h = (int)Math.ceil(Math.log(nums.length) / Math.log(2));
			int size = (1 << (h + 1)) - 1;
	        arr = new int[size];
	        n = nums.length;
	        build(nums, 0, 0,n-1);
	    }

	private int build(int[] nums, int si, int ss, int se) {
		if (ss > se) {
			return 0;
		}
		if (ss == se) {
			arr[si] = nums[ss];
			return nums[ss];
		}
		int mid = ss + (se - ss) / 2;
		int sum = build(nums, si * 2 + 1, ss, mid) + build(nums, si * 2 + 2, mid + 1, se);
		arr[si] = sum;
		return sum;
	}

	public void update(int i, int val) {
		update(0, 0, n - 1, i, val);
	}

	private int update(int si, int ss, int se, int i, int val) {
		if (i < ss || i > se) {
			return 0;
		}
		if (ss == se) {
			int diff = val -arr[si];
			arr[si] = val;
			return diff;
		}
		int mid = ss + (se - ss) / 2;
		int diff = update(si * 2 + 1, ss, mid, i, val) + update(si * 2 + 2, mid + 1, se, i, val);
		arr[si] = arr[si] + diff;
		return diff;
	}

	public int sumRange(int i, int j) {
		return sum(0, 0, n - 1, i, j);
	}

	private int sum(int si, int ss, int se, int low, int high) {
		if ((low < ss && high < ss) || (high > se && low > se)) {
			return 0;
		}

		if (ss >= low && se <= high) {
			return arr[si];
		}
	

		int mid = ss + (se - ss) / 2;
		return sum(si * 2 + 1, ss, mid, low, high) + sum(si * 2 + 2, mid + 1, se, low, high);
	}

	public static void main(String[] args) {
		int[] nums = {0,9,5,7,3};
		RangeSumQuery rsm = new RangeSumQuery(nums);
		System.out.println(rsm.sumRange(2, 4));

	}

}
