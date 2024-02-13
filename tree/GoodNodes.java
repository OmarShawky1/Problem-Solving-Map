package tree;

public class GoodNodes {
    // Most Maintainable and Optimal
    public int goodNodes(TreeNode root) {
        return goodNodesDFS(root, root.val, 0);
    }

    private int goodNodesDFS(TreeNode cur, int curMax, int goodNodes) {
        if (cur == null) return goodNodes;

        // if (cur.val >= curMax) return 1 + goodNodesDFS(cur.left, cur.val, goodNodes) + goodNodesDFS(cur.right, cur.val, goodNodes);
        // More readable
        if (cur.val >= curMax) {
            curMax = cur.val;
            return 1 + goodNodesDFS(cur.left, curMax, goodNodes) + goodNodesDFS(cur.right, curMax, goodNodes);
        }
        return goodNodesDFS(cur.left, curMax, goodNodes) + goodNodesDFS(cur.right, curMax, goodNodes);
    }

    public static void test() {
        GoodNodes g = new GoodNodes();
        assert g.goodNodes(new TreeNode(1)) == 1;
    }
}
