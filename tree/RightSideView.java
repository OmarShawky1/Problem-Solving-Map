package tree;

import java.util.ArrayList;
import java.util.List;

public class RightSideView {
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        List<List<Integer>> preAns = levelOrder(root);
        for (List<Integer> loi : preAns) {
            ans.add(loi.get(loi.size() - 1));
        }

        return ans;
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        //Using queues
        List<List<Integer>> ls = new ArrayList<>();
        preOrder(root, 0, ls);
        return ls;
    }

    public static void preOrder(TreeNode root, int level, List<List<Integer>> ls) {
        if (root == null) return;
        if (ls.size() == level) {
            List<Integer> li = new ArrayList<>();
            li.add(root.val);
            ls.add(li);
        } else ls.get(level).add(root.val); // If level is already available, just add to its list.
        preOrder(root.left, level + 1, ls);
        preOrder(root.right, level + 1, ls);
    }

    public List<Integer> rightSideView2(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        reversePreOrder(root, 0, ans);
        return ans;
    }

    public void reversePreOrder(TreeNode node, int level, List<Integer> ans) {
        if (node != null) {
            if (level == ans.size()) ans.add(node.val);
            reversePreOrder(node.right, level + 1, ans);
            reversePreOrder(node.left, level + 1, ans);
        }
    }

    public static void test() {

    }
}
