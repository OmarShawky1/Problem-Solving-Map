package tree;public class SameTree {

    public boolean isSameTree(TreeNode p, TreeNode q) {
        // If both are null, they are the same
        if (p == null && q == null) return true;
        // But if one of the nodes only is null, they are not the same
        if (p == null || q == null) return false;
        // However, if both are not null, check their values and their sub-trees
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    private boolean isSameTree(TreeNode p, TreeNode q, boolean equal) {
        return p.val == q.val && isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }

    public static void test() {

    }
}
