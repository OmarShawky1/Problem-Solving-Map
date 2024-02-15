package tree;

import java.util.ArrayList;
import java.util.List;

public class IsValidBST {

    // Most maintainable & Optimal
    public boolean isValidBST(TreeNode root) {
        // Validate using Long Min & Max. Long.Min < Integer.Min as constrains are Integer.Min which violates a case
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    // Validate current node from its value to be in between min and max range.
    private boolean isValidBST(TreeNode node, long min, long max) {
        // If node is null, it is valid and return true
        if (node == null) return true;
        // If node is less than or equal min or bigger than or equal max return false. Initially min & max are Long Maxs
        if (node.val <= min || node.val >= max) return false;

        // Validate left and right tree.
        // If validating left, min should be the same (as we are decreasing) and max should be current.
        // This ensures that left subtree (left & right) are never bigger than current but can be smaller

        // If validating max, min should be current and max should be the same (as we are increasing)
        // This ensures that left subtree (left & right) are never smaller than current but can be bigger
        return isValidBST(node.left, min, node.val) && isValidBST(node.right, node.val, max);
    }

    // Sort then check in O(n)
    public boolean isValidBST1(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inorder(root, list);
        for (int i = 0; i < list.size() - 1; i++) if (list.get(i) >= list.get(i + 1)) return false;
        return true;
    }

    // Sort in O(n)
    public static void inorder(TreeNode root, List<Integer> list) {
        if (root == null) return;
        inorder(root.left, list);
        list.add(root.val);
        inorder(root.right, list);
    }


    public static void test() {
        IsValidBST i = new IsValidBST();
        TreeNode root1 = new TreeNode(2);
        root1.left = new TreeNode(1);
        root1.right = new TreeNode(3);
        assert i.isValidBST(root1, Long.MIN_VALUE, Long.MAX_VALUE);

        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(4);
        root3.right = new TreeNode(6);
        root3.right.left = new TreeNode(3);
        root3.right.right = new TreeNode(7);
        assert !i.isValidBST(root3, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}
