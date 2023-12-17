package stack;

import java.util.Stack;

public class ValidParentheses {

    // Most Maintainable code, use array instead of stack if you want it faster.
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            switch (c) {
                case ')':
                    if (stack.isEmpty() || stack.pop() != '(') return false;
                    break;
                case ']':
                    if (stack.isEmpty() || stack.pop() != '[') return false;
                    break;
                case '}':
                    if (stack.isEmpty() || stack.pop() != '{') return false;
                    break;
                default:
                    stack.push(c);
            }
        }
        return stack.isEmpty();
    }



    public static void test() {
        ValidParentheses v = new ValidParentheses();
        assert v.isValid("()");
        assert v.isValid("()[]{}");
        assert !v.isValid("(]");
        assert v.isValid("(([]){})");
    }
}
