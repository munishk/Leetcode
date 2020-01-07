import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ExpressionAddOperators_282 {

    char[] op = {'+', '-', '*' };
    public List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        long val = 0;
        for(int i=0; i<num.length(); i++) {
            val = val*10 + (num.charAt(i) - '0');
            addOperators(num, i+1, target, res, ""+val);
        }
        return res;
    }
    
    private void addOperators(String num, int index,int target, List<String> res, String expr) {
        if(index == num.length() && target == eval(expr)) {
            res.add(expr);
            return;
        }
        
        if(index >= num.length()) {
            return;
        }
        
        int val = 0;
        for(int i=index; i<num.length(); i++) {
            val = val*10 + (num.charAt(i) - '0');
            for(int j=0; j<3; j++) {
                char operator = op[j];
                addOperators(num, i+1, target, res, expr + op[j] + val);
            }
            //if char at index is 0, then do not try for next digit
            if(num.charAt(index) == '0') {
            	return;
            }
        }
        
    }
    
    private long eval(long first, long second, char op) {
        switch(op) {
            case '+' : return first + second;
            case '-' : return first-second;
            case '*' : return first * second;
            default : throw new IllegalArgumentException();
        }
    }
    
    private int getPrecedence(char c) {
        switch(c) {
            case '+':
            case '-': return 1;
            case '*': return 2;
            default: throw new IllegalArgumentException();
        }
    }
    
    private long eval(String exp) {
        Stack<Long> operand = new Stack<>();
        Stack<Character> op = new Stack<>();
        long val = 0;
        for(int i=0; i<exp.length(); i++) {
            char c = exp.charAt(i);
            switch(c) {
                case '*' : 
                case '-' :
                case '+' :
                           operand.push(val);
                           val = 0;
                           while(!op.isEmpty() && getPrecedence(op.peek()) >= getPrecedence(c)) {
                               char oper = op.pop();
                               long second = operand.pop();
                               long first = operand.pop();
                               operand.push(eval(first, second, oper));
                           }
                           op.push(c);
                           break;
                default: val = val*10 + (c-'0');
            }
        }
        operand.push(val);
        while(!op.isEmpty()) {
            char oper = op.pop();
            long second = operand.pop();
            long first = operand.pop();
            operand.push(eval(first, second, oper));  
         }
         return operand.peek();
    }

	public static void main(String[] args) {
		ExpressionAddOperators_282 obj = new ExpressionAddOperators_282();
		System.out.println(obj.addOperators("2147483648", -2147483648));
		
		int val = 214748364;
		System.out.println(val*10+8);

	}

}
