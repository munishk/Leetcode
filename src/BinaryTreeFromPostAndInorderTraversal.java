public class BinaryTreeFromPostAndInorderTraversal {
	
	 int postIndex;
	    public TreeNode buildTree(int[] inorder, int[] postorder) {
	        postIndex = postorder.length-1;
	       return buildTree(inorder, postorder, 0, inorder.length-1);
	    }
	    
	    private TreeNode buildTree(int[] inorder, int[] postorder, int low, int high) {
		        if(high < low) {
		            return null;
		        }
		        int rootVal = postorder[postIndex--];
		        TreeNode root = new TreeNode(rootVal);
		        if(low == high) {
		            return root;
		        }
		        
		        
		        int inorderIndex = findRootIndex(inorder, low, high, rootVal);
		        root.right = buildTree(inorder, postorder, inorderIndex+1, high);
		        root.left = buildTree(inorder, postorder, low, inorderIndex-1);
		        return root;
		    }
		    
		    private int findRootIndex(int[] inorder, int low, int high, int rootVal) {
		        for(int i=low; i<=high; i++) {
		            if(inorder[i] == rootVal) {
		                return i;
		            }
		        }
		      throw new IllegalArgumentException("Illegal argument");
		    }

	public static void main(String[] args) {
		BinaryTreeFromPostAndInorderTraversal sol = new BinaryTreeFromPostAndInorderTraversal();
		int[] inorder = {1,3,2};
		int[] postorder = {3, 2,1};
		TreeNode root = sol.buildTree(inorder, postorder);
		TreeNode.print(root);
	}

}
