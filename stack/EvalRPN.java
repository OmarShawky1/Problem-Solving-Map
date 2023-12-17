package stack;

import java.util.Stack;

public class EvalRPN {
    public int evalRPN(String[] tokens) {
        if (tokens.length < 1) return 0;

        int first, second;
        Stack<Integer> stack = new Stack<>();
        for (String s : tokens) {
            switch (s) {
                case "+":
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first + second);
                    break;
                case "-":
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first - second);
                    break;
                case "*":
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first * second);
                    break;
                case "/":
                    second = stack.pop();
                    first = stack.pop();
                    stack.push(first / second);
                    break;
                default:
                    stack.push(Integer.parseInt(s));
            }
        }
        return stack.pop();
    }

    public static void test() {
        EvalRPN e = new EvalRPN();
        assert e.evalRPN(new String[]{"2", "1", "+", "3", "*"}) == 9;
        assert e.evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}) == 22;
    }
}
