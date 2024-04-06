import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    public TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        List<List<Integer>> ret = new ArrayList<>();
        Map<Integer, List<Integer>> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        Deque<TreeNode> q1 = new ArrayDeque<>();
        Deque<Integer> q2 = new ArrayDeque<>();
        q1.add(root);
        q2.add(0);
        while (!q1.isEmpty()) {
            TreeNode node = q1.remove();
            int row = q2.remove();
            min = Math.min(min, row);
            max = Math.max(max, row);
            if (!map.containsKey(row)) {
                map.put(row, new ArrayList<>());
            }
            map.get(row).add(node.val);
            if (node.left != null) {
                q1.add(node.left);
                q2.add(row - 1);
            }
            if (node.right != null) {
                q1.add(node.right);
                q2.add(row + 1);
            }
        }
        while (min <= max) {
            if (map.containsKey(min)) {
                ret.add(map.get(min));
            }
            min++;
        }

        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        test1(solution);

    }

    public static void test1(Solution solution) {
        // Input: root = [3,9,20,null,null,15,7]
        // Output: [[9],[3,15],[20],[7]]
        TreeNode root = new TreeNode(3);
        TreeNode node1 = new TreeNode(9);
        TreeNode node2 = new TreeNode(20);
        TreeNode node3 = new TreeNode(15);
        TreeNode node4 = new TreeNode(7);
        root.left = node1;
        root.right = node2;
        node2.left = node3;
        node2.right = node4;
        System.out.println(solution.verticalOrder(root));
    }

}