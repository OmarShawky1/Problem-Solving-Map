package tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class LevelOrder {
    public List<List<Integer>> levelOrder1(TreeNode root) {
        ArrayList<ArrayList<TreeNode>> queue = new ArrayList<>();
        if (root != null) {
            // For each level, do a preorder
            // Start with the root
            ArrayList<TreeNode> previousLevel = new ArrayList<>();
            previousLevel.add(root);
            queue.add(previousLevel);
            // While we still have more items (previous level is not empty as we still didn't reach leafs)
            while (!previousLevel.isEmpty()) {
                ArrayList<TreeNode> currentLevel = new ArrayList<>();
                // For each element in the previous nodes, add left and right nodes respectively
                for (TreeNode node : previousLevel) {
                    if (node.left != null) currentLevel.add(node.left);
                    if (node.right != null) currentLevel.add(node.right);
                }
                // Add Current level to queue and make it previous level and repeat
                queue.add(currentLevel);
                previousLevel = queue.get(queue.size() - 1);
            }
        }
        // Remove the last empty list
        if (!queue.isEmpty()) queue.remove(queue.size() - 1);

        return queue
                .stream() // create a stream of the outer ArrayList
                .map(innerList -> innerList.stream() // For each inner ArrayList<TreeNode> in the stream, we use .map(innerList -> ...) to transform it.
                        .map(node -> node.val) // Inside the inner mapping, we use .map(node -> node.val) to extract the val field from each TreeNode
                        .collect(Collectors.toList())) // Finally, we collect the results into a List<List<Integer>> using .collect(Collectors.toList()).
                .collect(Collectors.toList());
    }


    // Optimal
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            List<Integer> currentLevel = new ArrayList<>();

            for (int i = 0; i < levelSize; i++) {
                TreeNode currentNode = queue.poll();
                currentLevel.add(currentNode.val);

                if (currentNode.left != null) queue.offer(currentNode.left);
                if (currentNode.right != null) queue.offer(currentNode.right);
            }

            result.add(currentLevel);
        }

        return result;
    }

    // Most optimal
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

    public static void test() {

    }
}
