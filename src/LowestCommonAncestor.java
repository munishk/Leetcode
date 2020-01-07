/**
 * https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/
 * 
 * @author munishk
 *
 */
public class LowestCommonAncestor {

	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
		if (root == null) {
			return null;
		}

		if (root.val == p.val || root.val == q.val) {
			return root;
		}

		TreeNode left = lowestCommonAncestor(root.left, p, q);
		TreeNode right = lowestCommonAncestor(root.right, p, q);
		if (left != null && right != null) {
			return root;
		}

		return left != null ? left : right;
	}
	
	
	public static void main(String[] args) {
		LowestCommonAncestor lca = new LowestCommonAncestor();
		Object[] values = { 37, -34, -48, null, -100, -100, 48, null, null, null, null, -54, null, -71, -22, null, null,
				null, 8 };
		TreeNode root = TreeNode.buildTree(values);
		TreeNode p = new TreeNode(-100);
		TreeNode q = new TreeNode(-71);
		TreeNode ans = lca.lowestCommonAncestor(root, p, q);
		System.out.println(ans);

	}

}
