import java.util.Stack;

public class BinaryTreeConstruction_536 {
	
	public TreeNode str2tree(String s) {
        if(s == null || s.isEmpty()) {
            return null;
        }
       Stack<TreeNode> stack = new Stack<>();
       TreeNode root = new TreeNode(0);
       stack.push(root);
       int multiplier = 1;
       for(int i=0; i<s.length(); i++) {
           char c = s.charAt(i);
           TreeNode curr = stack.peek();
           switch(c) {
               case '(' :
                   TreeNode newNode = new TreeNode(0);
                   if(curr.left == null) {
                       curr.left = newNode;
                   }else {
                       curr.right = newNode;
                   }
                   stack.push(newNode);
                   break;
               case ')' :
                   stack.pop();
                   break;
                case '-' : multiplier = -1; break;
               default: curr.val = (c -'0') * multiplier;
                        multiplier = 1;
           }
       }
       return root;
    }

	public static void main(String[] args) {
		BinaryTreeConstruction_536 obj = new BinaryTreeConstruction_536();
		String s = "-4(2(3)(1))(6(5)(7))";
		TreeNode root = obj.str2tree(s);
		TreeNode.print(root);

	}

}
