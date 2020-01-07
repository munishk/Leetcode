public class FlatternTree {
	
	public void flatten(TreeNode root) {
	      flattenUtil(root);
	    }
	    
	    
	     public TreeNode flattenUtil(TreeNode root) {
	        if(root == null) {
	            return null;
	        }
	        
	        TreeNode left = flattenUtil(root.left);
	        TreeNode right = flattenUtil(root.right);
	        
	        if(left == null && right == null) {
	            return root;
	        }
	        
	        if(left != null) {
	             TreeNode tailLeft = findTail(left);
	             tailLeft.right = right;
	             root.right = left;
	             root.left = null;
	        }
	        
	        return root;
	    }
	    
	    private TreeNode findTail(TreeNode root) {
	        TreeNode current = root;
	        while(current.right != null) {
	            current = current.right;
	        }
	        return current;
	    }
	
	public static void main(String[] args) {
		FlatternTree sol = new FlatternTree();
		Object[] values = {1,2,5,3,4,null,6};
		TreeNode root = TreeNode.buildTree(values);
		sol.flatten(root);
		System.out.println("######## After ############");
		TreeNode.print(root);
	}

}
