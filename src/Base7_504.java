import java.util.Stack;

public class Base7_504 {
	
	public String convertTo7(int num) {
        Stack<Character> stack = new Stack<>();
        int multiplier = 1;
        if(num < 0) {
            multiplier = -1;
            num = num * -1;
        }
        while(num >= 7) {
            stack.push((char)(num%7 + '0'));
            num = num/7;
        }
        stack.push((char)(num%7 + '0'));
        
        StringBuilder sb = new StringBuilder();
        if(multiplier == -1) {
            sb.append('-');
        }
        
        while(!stack.isEmpty()) {
            sb.append(stack.pop());
        }
        return sb.toString();
    }

	public static void main(String[] args) {
		Base7_504 obj = new Base7_504();
		System.out.println(obj.convertTo7(100));

	}

}
