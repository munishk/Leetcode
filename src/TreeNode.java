import java.util.LinkedList;
import java.util.Queue;

public class TreeNode {

    
    public int val;
	public TreeNode left, right;

	TreeNode(int value) {
		this.val = value;
	}
	
	public String toString() {
		return "" +val;
	}
	
	
	static TreeNode buildTree(Object[] values) {
		int index = 0;
		TreeNode root = new TreeNode((int) values[index++]);
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		while (index < values.length) {
			TreeNode curr = q.remove();

			Object value = values[index++];
			if (value != null) {
				curr.left = new TreeNode((int) value);
				q.add(curr.left);
			}

			if (index < values.length) {
				value = values[index++];
				if (value != null) {
					curr.right = new TreeNode((int) value);
					q.add(curr.right);
				}
			}
		}
		
	    print(root);
		return root;
	}
	
	static void print(TreeNode root) {
		System.out.println(stringUtil(root));
	}
	
	static  String stringUtil(TreeNode node) {
		if (node == null) {
			return "NULL";
		}
		if (node.left == null && node.right == null) {
			return node.toString();
		}

		StringBuilder sb = new StringBuilder();

		String[] lhs = stringUtil(node.left).split("\n");
		String[] rhs = stringUtil(node.right).split("\n");

		int maxCol = getMaxCol(lhs) + getMaxCol(rhs) + 4;
		alignAtCenter(sb, node.toString(), maxCol, true);
		alignAtCenter(sb, "|", maxCol, true);
		addLineSeparator(sb, maxCol, getMaxCol(lhs), getMaxCol(rhs));
		addChildNode(sb, lhs, rhs, maxCol);
		return sb.toString();
	}

	private static int getMaxCol(String[] strs) {
		int max = 0;
		for (String str : strs) {
			if (str.length() > max) {
				max = str.length();
			}
		}
		return max;
	}

	private static void addChildNode(StringBuilder sb, String[] lhs, String[] rhs, int maxCol) {
		for (int i = 0; i < lhs.length || i < rhs.length; i++) {
			int currentPos = 0;
			if (i < lhs.length) {
				sb.append(lhs[i]);
				currentPos += lhs[i].length();
			}
			int rhsPosition = maxCol;
			if (i < rhs.length) {
				rhsPosition -= rhs[i].length();
			}
			for (int j = currentPos; j < rhsPosition; j++) {
				sb.append(' ');
			}
			if (i < rhs.length) {
				sb.append(rhs[i]);
			}
			sb.append("\n");
		}
	}

	private static  void addLineSeparator(StringBuilder sb, int maxCol, int leftLen, int rightLen) {
		int left = 0, right = 0;
		boolean firstOccurence = true;
		for (int i = 0; i < maxCol; i++) {
			if (i < leftLen / 2 || i > maxCol - rightLen / 2) {
				sb.append(' ');
			} else {
				if (firstOccurence) {
					left = i;
					firstOccurence = false;
				} else {
					right = i;
				}
				sb.append('-');
			}
		}
		/*
		 * sb.append("\n"); for(int i=0; i < maxCol; i++) { if(i == left -1 || i
		 * == right +1) { sb.append("|"); }else { sb.append(' '); } }
		 */
		sb.append("\n");
	}

	private static void alignAtCenter(StringBuilder sb, String str, int maxCol, boolean newLine) {
		for (int i = 0; i < maxCol;) {
			if (maxCol / 2 == i) {
				sb.append(str);
				i = i + str.length();
			} else {
				sb.append(' ');
				i++;
			}
		}

		if (newLine) {
			sb.append("\n");
		}
	}
    
}
