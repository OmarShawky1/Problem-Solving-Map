package tree;

public class LowestCommonAncestor {

    // Not most optimal nor maintainable
    public TreeNode lowestCommonAncestor1(TreeNode root, TreeNode p, TreeNode q) {
        if (isChild(p, q)) return p;
        if (isChild(q, p)) return q;
        return SAP(root, p, q); // 10e5 which is the maximum tree size
    }

    private TreeNode SAP(TreeNode cur, TreeNode source, TreeNode target) {
        if (cur == null) return null;

        // If one node on left and other on right, then return current node
        boolean sourceOnLeft = isChild(cur.left, source);
        boolean sourceOnRight = !sourceOnLeft;
        boolean targetOnLeft = isChild(cur.left, target);
        boolean targetOnRight = !targetOnLeft;
        if ((sourceOnLeft && targetOnRight) || (sourceOnRight && targetOnLeft)) return cur;

        // Else, both on one side
        if (sourceOnLeft) return SAP(cur.left, source, target);
        else return SAP(cur.right, source, target);
    }

    private boolean isChild(TreeNode source, TreeNode target) {
        if (source == null) return false;
        return source == target || isChild(source.left, target) || isChild(source.right, target);
    }

    // Most Maintainable & Optimal solution
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // If both pq and q are on one side only, go to this side
        if (p.val < root.val && q.val < root.val) return lowestCommonAncestor(root.left, p, q);
        else if (p.val > root.val && q.val > root.val) return lowestCommonAncestor(root.right, p, q);
        else return root; // root == p || root == q || p on left of root and q on right or vice-versa
    }

    public static void test() {
        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(2);
        root.right = new TreeNode(8);
        root.left.left = new TreeNode(0);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(7);
        root.right.right = new TreeNode(9);
        root.left.right.left = new TreeNode(3);
        root.left.right.right = new TreeNode(5);

        TreeNode p = root.left;
        TreeNode q = root.right;

        LowestCommonAncestor l = new LowestCommonAncestor();
        assert l.lowestCommonAncestor(root, p, q) == root;
    }
}
