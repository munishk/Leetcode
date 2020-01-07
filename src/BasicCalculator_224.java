import java.util.Stack;

public class BasicCalculator_224 {

	public int calculate(String s) {
		Stack<Integer> operand = new Stack<>();
		Stack<Character> operator = new Stack<>();
		int val = 0;
		int multiplier = 1;
		for (int i = 0; i < s.length(); i++) {
			char c = s.charAt(i);
			switch (c) {
			case ' ':
				break;
			case '+':
			case '-':
			case '*':
			case '/':
				if (val != 0) {
					operand.push(val * multiplier);
				}
				val = 0;
				multiplier = 1;
				while (!operator.isEmpty() && precedence(c) <= precedence(operator.peek())) {
					int second = operand.pop();
					int first = operand.pop();
					operand.push(eval(first, second, operator.pop()));
				}
				operator.push(c);
				break;

			case '(':
				operator.push(c);
				break;
			case ')':
				if (val != 0) {
					operand.push(val * multiplier);
				}
				val = 0;
				multiplier = 0;
				while (operator.peek() != '(') {
					int second = operand.pop();
					int first = operand.pop();
					operand.push(eval(first, second, operator.pop()));
				}
				operator.pop();
				break;
			default:
				val = val * 10 + c - '0';
			}
		}

		if (val != 0) {
			operand.push(val);
		}
		while (!operator.isEmpty()) {
			int second = operand.pop();
			int first = operand.pop();
			operand.push(eval(first, second, operator.pop()));
		}
		return operand.peek();
	}

	int precedence(char c) {
		switch (c) {
		case '+':
		case '-':
			return 1;
		case '*':
		case '/':
			return 2;
		case '(':
			return 0;
		case ')':
			return 3;
		default:
			throw new IllegalArgumentException();
		}
	}

	int eval(int first, int second, char op) {
		switch (op) {
		case '+':
			return first + second;
		case '*':
			return first * second;
		case '/':
			return first / second;
		case '-':
			return first - second;
		default:
			throw new IllegalArgumentException();
		}
	}

	public static void main(String[] args) {
		BasicCalculator_224 calc = new BasicCalculator_224();
		System.out.println(calc.calculate("(1+(4+5+2)-3)+(6+8)"));

	}

}
