import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreeLinkNodeSolution {
	
	static class TreeLinkNode {
		TreeLinkNode left, right, next;
		int val;
		TreeLinkNode(int val) {
			this.val = val;
		}
		
		@Override
		public String toString() {
			return "" + val;
		}
	}
	
	public void connect(TreeLinkNode root) {
        List<TreeLinkNode> list = new ArrayList<>();
        connect(root, 0, list);
    }
    
    private void connect(TreeLinkNode root, int depth, List<TreeLinkNode> list) {
        if(root == null) {
            return;
        }
        if(list.size() > depth) {
            TreeLinkNode prev = list.get(depth); 
            prev.next = root;
            list.remove(depth);
            list.add(depth, root);
            
        }else {
            list.add(depth, root);
        }
        
        connect(root.left, depth+1, list);
        connect(root.right, depth+1, list);
        
    }
    
    TreeLinkNode buildTree(Object[] values) {
		int index = 0;
		TreeLinkNode root = new TreeLinkNode((int) values[index++]);
		Queue<TreeLinkNode> q = new LinkedList<>();
		q.add(root);

		while (index < values.length) {
			TreeLinkNode curr = q.remove();

			Object value = values[index++];
			if (value != null) {
				curr.left = new TreeLinkNode((int) value);
				q.add(curr.left);
			}

			if (index < values.length) {
				value = values[index++];
				if (value != null) {
					curr.right = new TreeLinkNode((int) value);
					q.add(curr.right);
				}
			}
		}
		return root;
	}
	
    public static String printTree(TreeLinkNode node) {
		if (node == null) {
			return "NULL";
		}
		if (node.left == null && node.right == null) {
			return node.toString();
		}

		StringBuilder sb = new StringBuilder();

		String[] lhs = printTree(node.left).split("\n");
		String[] rhs = printTree(node.right).split("\n");

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

	public static void main(String[] args) {
		TreeLinkNodeSolution sol = new TreeLinkNodeSolution();
		Object[] values = {0,1,2,3,4,5,6};
		TreeLinkNode root =  sol.buildTree(values);
		System.out.println(printTree(root));
		sol.connect(root);
System.out.println(root);
	}

}
