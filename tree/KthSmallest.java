package tree;

import java.util.ArrayList;
import java.util.Stack;

public class KthSmallest {

    // Most Maintainable & Optimal
    public int kthSmallest(TreeNode root, int k) {
        // Perform an inorder traversal and keep track of the elements
        // Using a heap stack instead of main stack to use iteration instead of recursion
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;
        int result = 0;

        // Reach the far left (until current is null) then each left's right (pop from stack)
        while (current != null || !stack.isEmpty()) {
            // Until we reach the far left of current node, push node to stack
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
            // Count from last node (popped from stack) and start searching its right
            current = stack.pop();
            count++;
            // If we reached the k smallest (count is k), store value and stop looping
            if (count == k) {
                result = current.val;
                break;
            }
            // After reaching the far left, start searching its right
            current = current.right;
        }

        return result;
    }

    // Same complexity as above but best and worst case scenario are the same as you will traverse entire tree always
    public int kthSmallest1(TreeNode root, int k) {
        // Perform an inorder traversal recursively and keep track of the count
        // and the result
        int[] result = new int[2]; // result[0] = count, result[1] = kth smallest element
        inorderTraversal(root, k, result);
        return result[1];
    }

    private static void inorderTraversal(TreeNode root, int k, int[] result) {
        if (root == null) return;

        inorderTraversal(root.left, k, result);

        // Increment the count and check if it's equal to k
        result[0]++;
        if (result[0] == k) {
            result[1] = root.val;
            return;
        }

        inorderTraversal(root.right, k, result);
    }

    // Same time complexity but O(n) memory
    public int kthSmallest2(TreeNode root, int k) {
        ArrayList<Integer> ans = new ArrayList<>();
        inOrder(root, ans);
        return ans.get(k - 1);
    }

    private void inOrder(TreeNode root, ArrayList<Integer> ans) {
        if (root == null) return;
        inOrder(root.left, ans);
        ans.add(root.val);
        inOrder(root.right, ans);
    }

    int kthSmallest3(TreeNode root, int k) {
        int[] ans = new int[1];
        boolean[] foundAnswer = new boolean[]{false};
        inOrder3(root, k, ans, foundAnswer);
        return ans[0];
    }

    // Like above recursion but uses a flag to stop traversing like the break in the iterative solution.
    // Flag prevents unnecessary tree traversal but the stack calls will be done in despite of it.
    int inOrder3(TreeNode root, int k, int[] ans, boolean[] foundAnswer) {
        if (foundAnswer[0]) return 0; // To make theta less than O(n) (works like the break in the iteration above)
        if (root == null) return 0;

        int left = inOrder3(root.left, k, ans, foundAnswer);

        if (left == k - 1) { // k - 1 to convert it from 1 to 0 index
            ans[0] = root.val;
            foundAnswer[0] = true;
        }

        int right = inOrder3(root.right, k - left - 1, ans, foundAnswer);

        return left + right + 1; // Return the count of current node
    }

    public static void test() {
        KthSmallest k = new KthSmallest();

        TreeNode root1 = new TreeNode(1);
        assert k.kthSmallest(root1, 1) == 1;

        TreeNode root2 = new TreeNode(3);
        root2.left = new TreeNode(1);
        root2.right = new TreeNode(4);
        root2.left.right = new TreeNode(2);
        assert k.kthSmallest(root2, 1) == 1;

        TreeNode root3 = new TreeNode(5);
        root3.left = new TreeNode(3);
        root3.right = new TreeNode(6);
        root3.left.left = new TreeNode(2);
        root3.left.right = new TreeNode(4);
        root3.left.left.left = new TreeNode(1);
        assert k.kthSmallest(root3, 3) == 3;
    }
}
