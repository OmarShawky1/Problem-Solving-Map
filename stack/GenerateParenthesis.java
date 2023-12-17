package stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class GenerateParenthesis {
    private int n;
    private final ArrayList<String> strings = new ArrayList<>();
    private final Stack<String> stack = new Stack<>();

    // Using Stack
    public List<String> generateParenthesis1(int n) {
        this.n = n;
        generateParenthesis(0, 0);
        return strings;
    }

    private void generateParenthesis(int o, int c) {
        // Base case
        if (o == n && c == n) {
            strings.add(String.join("", stack)); // Add string pattern
            return; // Unnecessary, but kept for recursion convention
        }

        if (o < n) {
            stack.push("(");
            generateParenthesis(o + 1, c);
            stack.pop();
        }

        if (o > c) {
            stack.push(")");
            generateParenthesis(o, c + 1);
            stack.pop();
        }
    }

    // Most Maintainable Using String concatenation
    public List<String> generateParenthesis2(int n) {
        List<String> result = new ArrayList<>();
        generate("", n, n, result);
        return result;
    }

    private void generate(String current, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(current);
            return; // Unnecessary, but kept for recursion convention
        }

        if (left > 0) generate(current + "(", left - 1, right, result);
        if (right > left) generate(current + ")", left, right - 1, result);
    }

    // Fastest, 0 Time, Using String Builder Concatenation.
    public List<String> generateParenthesis(int n) {
        List<String> result = new ArrayList<>();
        generate(new StringBuilder(), n, n, result);
        return result;
    }

    private void generate(StringBuilder current, int left, int right, List<String> result) {
        if (left == 0 && right == 0) {
            result.add(current.toString());
            return; // Unnecessary, but kept for recursion convention
        }

        if (left > 0) {
            current.append( "(");
            generate(current, left - 1, right, result);
            current.deleteCharAt(current.length() - 1);
        }

        if (right > left) {
            current.append( ")");
            generate(current, left, right - 1, result);
            current.deleteCharAt(current.length() - 1);
        }
    }


    public static void test() {
        GenerateParenthesis g = new GenerateParenthesis();
        System.out.println(g.generateParenthesis(1).get(0));
        assert g.generateParenthesis(1).get(0).equals("()");
        System.out.println(Arrays.toString(g.generateParenthesis(2).toArray()));
        assert g.generateParenthesis(2).get(0).equals("(())");
        assert g.generateParenthesis(2).get(1).equals("()()");
    }
}
