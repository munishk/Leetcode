import java.util.Stack;

public class SimplifyPath_71 {
	
	 public String simplifyPath(String path) {
	        String[] dirs = path.split("/");
	        Stack<String> stack = new Stack<>();
	        for(String dir: dirs) {
	            switch(dir) {
	                case "." : break;
	                case "" :  break;
	                case "..": stack.pop(); 
	                           break;
	                default: stack.push(dir);
	            }
	        }
	        
	        StringBuilder sb = new StringBuilder();
	        while(!stack.isEmpty()) {
	           sb.append('/').append(stack.pop()); 
	        }
	        return sb.toString();
	    }

	public static void main(String[] args) {
		SimplifyPath_71 obj = new SimplifyPath_71();
		System.out.println(obj.simplifyPath("/a/./b/../../c/"));

	}

}
