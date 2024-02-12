package tree;

public class DiameterOfBinaryTree {
    // Longest Path using DFS
    int maxDiameter = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        // Recursively calculate the diameter (longest path) of the node from the two longest depths in tree
         longestPath(root);
         return maxDiameter;
    }

    // Normal Recursion and using global variable
    private int longestPath(TreeNode node) {
        if (node == null) return 0;

        int leftHeight = longestPath(node.left);
        int rightHeight = longestPath(node.right);

        // Update the maxDiameter if necessary
        maxDiameter = Math.max(maxDiameter, leftHeight + rightHeight);

        // Return the maximum depth of the current node by adding 1 to the maximum depth of its deepest subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public int diameterOfBinaryTree1(TreeNode root) {
        // Recursively calculate the diameter (longest path) of the node from the two longest depths in tre
        int[] maxDiam = new int[1];
        longestPath(root, maxDiam);
        return maxDiam[0];
    }

    // Normal Recursion and using pass by reference
    private int longestPath(TreeNode node, int[] maxDiam) {
        if (node == null) return 0;

        int leftHeight = longestPath(node.left, maxDiam);
        int rightHeight = longestPath(node.right, maxDiam);

        // Update the maxDiameter if necessary
        maxDiam[0] = Math.max(maxDiam[0], leftHeight + rightHeight);

        // Return the maximum depth of the current node by adding 1 to the maximum depth of its deepest subtree
        return 1 + Math.max(leftHeight, rightHeight);
    }

    public static void test() {
        TreeNode tree1 =
                new TreeNode(1,
                        new TreeNode(2,
                                new TreeNode(4),
                                new TreeNode(5)),
                        new TreeNode(3));
        TreeNode tree2 = new TreeNode(1, new TreeNode(2), null);
        TreeNode tree3 =
                new TreeNode(4,
                        new TreeNode(-7, null, null),
                        new TreeNode(-3,
                                new TreeNode(-9,
                                        new TreeNode(9,
                                                new TreeNode(6,
                                                        new TreeNode(0,
                                                                null,
                                                                new TreeNode(-1)),
                                                        new TreeNode(6,
                                                                new TreeNode(-4),
                                                                null)),
                                                null),
                                        null),
                                new TreeNode(-7,
                                        new TreeNode(-6,
                                                null,
                                                new TreeNode(5)),
                                        new TreeNode(-6,
                                                new TreeNode(9,
                                                        new TreeNode(-2),
                                                        null),
                                                null))));

        DiameterOfBinaryTree d = new DiameterOfBinaryTree();

        // Testing the diameter calculation for each tree
        System.out.println("Diameter of tree 1: " + d.diameterOfBinaryTree(tree1));
        assert d.diameterOfBinaryTree(tree1) == 3;
        System.out.println("Diameter of tree 2: " + d.diameterOfBinaryTree(tree2));
        assert d.diameterOfBinaryTree(tree2) == 1;
        System.out.println("Diameter of tree 3: " + d.diameterOfBinaryTree(tree3));
        assert d.diameterOfBinaryTree(tree3) == 8;
    }
}
