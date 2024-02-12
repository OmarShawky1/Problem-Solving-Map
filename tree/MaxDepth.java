package tree;

public class MaxDepth {
    // Most Maintainable
    public int maxDepth(TreeNode root) {
        return maxDepth(root, 0); // Tail recursion
    }

    private int maxDepth(TreeNode node, int i) {
        if (node == null) return i; // Base case
        return Math.max(maxDepth(node.left, i + 1), maxDepth(node.right, i + 1));
    }

    public static void test() {

    }
}
