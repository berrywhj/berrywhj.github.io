---
layout: post
title:  "314. Binary Tree Vertical Order Traversal"
date:   2024-04-06 17:24:42 -0500
categories: algorithms binary-tree Meta
---

[The link to the question is here](https://leetcode.com/problems/binary-tree-vertical-order-traversal/description/).

We can use tree `level order travesal` to solve this problem. First, we define that the column number of the left child should be the column number of its parent minus 1. Similarly, the column number of right child should be the column number of its parent plus one. We can mark the `root` as `column 0`. Then instead of use `one queue` to store the TreeNodes in the normal fashion, we need `another queue` to store the `column number` mapping to the TreeNode.

We also need a Map, the key stores the `column number`, and the value store the `list` of the TreeNodes fall into the column of its key.

Since we are iterating `from top to bottom`, we should be able to guarantee that in each list of the column, the TreeNode from `upper level` appears `earlier` than `lowwer level`.

Since in each `level`, we traverse from `left to right`, we can guarantee that TreeNodes in the `same level` with `same column` has proper order.

{% highlight java %}
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
{% endhighlight %}

Time complexity: `O(n)`

We traverse the Tree only `once` and when dealing with each node, we manipulate these collections within constant time, so the time complexity should be O(n), n is the number of TreeNodes in that tree.

Space complexity: `O(n)`

The return list contains serveral lists, while the `total number of the TreeNodes` in these lists should be n. The map is kind of similar. We also utilize two queues to perform our level order traversal, the space complexity of these two queues is also O(n). So, the overall space complexity should be O(n).