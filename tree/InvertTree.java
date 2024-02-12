package tree;

import java.util.ArrayList;

public class InvertTree {
    // BFS, More memory
    public TreeNode invertTree1(TreeNode root) {
        ArrayList<TreeNode> queue = new ArrayList<>();
        queue.add(root);
        while (!queue.isEmpty() && queue.get(0) != null) {
            TreeNode cur = queue.get(0);
            queue.remove(0);
            if (cur.left != null) queue.add(cur.left);
            if (cur.right != null) queue.add(cur.right);

            TreeNode left = cur.left;
            cur.left = cur.right;
            cur.right = left;
        }
        return root;
    }

    // DFS, Recursion
    public TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = invertTree(root.left);
        TreeNode right = invertTree(root.right);
        root.left = right;
        root.right = left;
        return root;
    }

    public static void test() {

    }
}
