import java.util.*;

public class ZigzagLevelOrderTraversal {
	
	/*
	 * Traverse tree using level order and keep adding element in queue or stack fashion based on whether we are on odd row or even row.
	 */
	public List<List<Integer>> zigzagLevelOrderBFS(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if(root == null) {
			return res;
		}
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		int size = 1;
		boolean evenRow = true;
		while(!q.isEmpty()) {
			LinkedList<Integer> list = new LinkedList<>();
			for(int i=0; i<size; i++) {
				TreeNode curr = q.poll();
				if(evenRow) {
					list.add(curr.val);
				}else {
					list.add(0, curr.val);
				}
				
				if(curr.left != null) q.add(curr.left);
				if(curr.right != null) q.add(curr.right);
			}
			size = q.size();
			evenRow = !evenRow;
			res.add(list);	
		}
		return res;
	}
	

	/*
	 * Recursive approach using DFS. if even row, then add element in queue fashion. For odd line, add element in stack fashion.
	 */
	public List<List<Integer>> zigzagLevelOrderDFS(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		travel(root, res, 0);
		return res;
	}
	
	private void travel(TreeNode root, List<List<Integer>> res, int level) {
		if(root == null) {
			return;
		}
		
		if(res.size() <= level) {
			res.add(new LinkedList<>());
		}
		
		List<Integer> col = res.get(level);
		if(level%2 == 0) {
			col.add(root.val);
		}else {
			col.add(0, root.val);
		}
		
		travel(root.left, res, level+1);
		travel(root.right, res, level+1);
	}
	
	/*
	 * Very naive implementation which uses 2 stacks and creates multiple lists.
	 */
	 public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
	        List<List<Integer>> res = new ArrayList<>();
	        if(root == null) {
	            return res;
	        }
	        
	        Stack<TreeNode> s1 = new Stack<>();
	        Stack<TreeNode> s2 = new Stack<>();
	        s1.push(root);
	        
	        while(!s1.isEmpty() || !s2.isEmpty()) {
	            List<Integer> subList = new ArrayList<>();
	            while(!s1.isEmpty()) {
	                TreeNode top = s1.pop();
	                subList.add(top.val);
	                if(top.left != null) {
	                    s2.push(top.left);
	                }
	                 if(top.right != null) {
	                    s2.push(top.right);
	                }
	            }
	            if(!subList.isEmpty()) {
	            res.add(subList);
	            }
	            subList = new ArrayList<>();
	            while(!s2.isEmpty()) {
	                TreeNode top = s2.pop();
	                subList.add(top.val);
	                if(top.right != null) {
	                    s1.push(top.right);
	                }
	                 if(top.left != null) {
	                    s1.push(top.left);
	                }
	            }
	            if(!subList.isEmpty()) {
	            res.add(subList);
	            }
	        }
	        return res;
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
