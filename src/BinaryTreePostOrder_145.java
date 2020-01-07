import java.util.*;

public class BinaryTreePostOrder_145 {

	public List<Integer> postorderTraversal(TreeNode root) {
		Stack<TreeNode> stack = new Stack<>();
		LinkedList<Integer> res = new LinkedList<>();
		TreeNode curr = root;
		while (!stack.isEmpty() || curr != null) {
			if (curr != null) {
				stack.push(curr);
				res.addFirst(curr.val);
				curr = curr.right;
			} else {
				curr = stack.pop().left;
			}
		}
		return res;
	}

	public List<Integer> postorderTraversal_1(TreeNode root) {
		List<Integer> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Stack<TreeNode> stack = new Stack<>();
		Set<TreeNode> visited = new HashSet<>();
		stack.push(root);
		while (!stack.isEmpty() && stack.peek().left != null) {
			stack.push(stack.peek().left);
		}

		while (!stack.isEmpty()) {
			TreeNode curr = stack.peek();
			if (curr.right == null || visited.contains(curr.right)) {
				res.add(stack.pop().val);
				visited.add(curr);
			} else {
				if (curr.right != null) {
					stack.push(curr.right);
					while (!stack.isEmpty() && stack.peek().left != null) {
						stack.push(stack.peek().left);
					}
				}
			}
		}
		return res;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
