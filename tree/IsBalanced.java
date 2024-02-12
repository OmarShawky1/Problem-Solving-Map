package tree;

public class IsBalanced {
    // Most Maintainable
    public boolean isBalanced1(TreeNode root) {
        if (root == null) return true;
        return (Math.abs(weight(root.left) - weight(root.right)) <= 1)
                && isBalanced(root.right)
                && isBalanced(root.left);
    }

    private int weight(TreeNode node) {
        if (node == null) return 0;
        return 1 + Math.max(weight(node.left), weight(node.right));
    }

    public boolean isBalanced2(TreeNode root) {
        // bf: (balanced factor), This takes the absolute difference of left and right
        boolean[] bf = new boolean[]{true};
        height(root, bf);
        return bf[0];
    }

    // Most optimal
    // Create a method that gets the height
    public int height(TreeNode node, boolean[] bf) {
        // Check if the node is null if so return 0
        if (node == null) return 0;
        // left will get the height of left
        int left = height(node.left, bf);
        //and right will get the height of right
        int right = height(node.right, bf);
        // if bf is true, check if it might be false, otherwise, don't check
        if (bf[0]) bf[0] = Math.abs(left - right) <= 1;
        return 1 + Math.max(left, right);
    }

    // Alternative to the above
    public boolean isBalanced(TreeNode root) {
        // If it's not -1 then its balanced otherwise its not balanced(-1)
        return height(root) != -1;
    }

    // Create a method that gets the height
    public int height(TreeNode node) {
        // Check if the node is null if so return 0
        if (node == null) return 0;
        // left will get the height of left
        int left = height(node.left);
        //and right will get the height of right
        int right = height(node.right);
        // bf: (balanced factor), This takes the absolute difference of left and right
        int bf = Math.abs(left - right);
        //if left and right are -1 you need to return -1
        if (bf > 1 || left == -1 || right == -1) return -1;
        //Now return the height
        return 1 + Math.max(left, right);
    }

    public static void test() {
        IsBalanced i = new IsBalanced();
        assert i.isBalanced(
                new TreeNode(1,
                        new TreeNode(2,
                                new TreeNode(3),
                                null),
                        new TreeNode(2,
                                null,
                                new TreeNode(3))
                ));

    }
}
