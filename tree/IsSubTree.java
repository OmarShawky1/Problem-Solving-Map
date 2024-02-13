package tree;

public class IsSubTree {
    // Most Maintainable
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // If root is null (base case) return false unless subroot is also null
        if (root == null) return subRoot == null;
        // Else, if values match, use isSameTree. Otherwise, keep trying.
        return (root.val == subRoot.val && isSameTree(root, subRoot))
                || isSubtree(root.left, subRoot)
                || isSubtree(root.right, subRoot);
    }

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // If both are null, they are the same
        if (p == null && q == null) return true;
        // But if one of the nodes only is null, they are not the same
        if (p == null || q == null) return false;
        // However, if both are not null, check their values and their sub-trees
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void test() {
        IsSubTree i = new IsSubTree();
        assert i.isSubtree(new TreeNode(1, new TreeNode(1), null), new TreeNode(1));
    }
}
