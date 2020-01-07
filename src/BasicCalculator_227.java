import java.util.Stack;

public class BasicCalculator_227 {
	
	 public int calculate(String s) {
	        if(s == null || s.trim().length() == 0) {
	            return 0;
	        }
	        Stack<Integer> operand = new Stack<>();
	        Stack<Character> operator = new Stack<>();
	        int val = 0;
	        for(int i=0; i<s.length(); i++) {
	            char c = s.charAt(i);
	            //System.out.print(c);
	            switch(c) {
	                case '+':
	                case '-':
	                          operand.push(val);
	                          val = 0;
	                          while(!operator.isEmpty()) {
	                               int second = operand.pop();
	                               int first = operand.pop();
	                               char op = operator.pop();
	                               int res = eval(first, second, op);
	                               operand.push(res);
	                          }
	                          operator.push(c);
	                          break;
	                case '*':
	                case '/': operand.push(val);
	                          val = 0;
	                          while(!operator.isEmpty() && getPrecedence(c) <= getPrecedence(operator.peek())) {
	                               int second = operand.pop();
	                               int first = operand.pop();
	                               char op = operator.pop();
	                               int res = eval(first, second, op);
	                               operand.push(res);
	                           }
	                           operator.push(c);
	                          break;
	                case ' ': break;
	                default : val = val * 10 + (c - '0');
	            }
	        }
	        
	        operand.push(val);
	        while(!operator.isEmpty()) {
	            int second = operand.pop();
	            int first = operand.pop();
	            char op = operator.pop();
	            int res = eval(first, second, op);
	            operand.push(res);
	        }
	        return operand.peek();
	    }
	    
	    private int getPrecedence(char c) {
	        switch(c) {
	            case '+':
	            case '-': return 1;
	            
	            case '*':
	            case '/': return 2;
	            default: throw new IllegalArgumentException();
	        }
	    }
	    
	    private int eval(int first, int second, char op) {
	        switch(op) {
	            case '+' : return first + second;
	            case '-' : return first - second;
	            case '*' : return first * second;
	            case '/' : return first/second;
	            default: throw new IllegalArgumentException();
	        }
	    }

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
