public class SumRootToLeafNumbers {

	int sum = 0;

	public int sumNumbers(TreeNode root) {
		sumNumbersUtil(root, 0);
		return sum;
	}

	public void sumNumbersUtil(TreeNode root, int prevVal) {
		if (root == null) {
			return;
		}
		int newVal = prevVal * 10 + root.val;
		if (root.left == null && root.right == null) {
			sum += newVal;
			return;
		}
		sumNumbersUtil(root.left, newVal);
		sumNumbersUtil(root.right, newVal);
	}

	public static void main(String[] args) {
		SumRootToLeafNumbers sol = new SumRootToLeafNumbers();
		Object[] val = { 1, 2, 5, 3 };
		TreeNode root = TreeNode.buildTree(val);
		int sum = sol.sumNumbers(root);
		System.out.println(sum);

	}

}
