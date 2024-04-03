---
layout: post
title:  "1762.Buildings With an Ocean View"
date:   2024-04-02 23:01:00 -0600
categories: algorithms array Meta
---
[The link to the question is here](https://leetcode.com/problems/buildings-with-an-ocean-view/description/).


The key observation is, A building can have the Ocean View only when it is `higher` than any other buildings on its right side. That is
$$ \text{ if } n_i \text{ represents the height of the ith building, } $$
$$ \forall j > i, \,\text{ we have } n_i > n_j $$

To solve this problem. We can iterate through the input array from right to left.

We maintain an variable called `max` to mark the largest number we encountered so far.

Each time we iterate a height, we compare it with `max`. If it is larger than `max`, we update `max` and add the index of this height to our list.

Finally we must not forget to reserve the order the list since it is asked to return the index array in increasing order.

{% highlight java %}
class Solution {
    public int[] findBuildings(int[] heights) {
        List<Integer> list = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        for (int i = heights.length - 1; i >= 0; i--) {
            if (heights[i] > max) {
                list.add(i);
                max = heights[i];
            }
        }
        int n = list.size();
        int[] ret = new int[n];
        for (int i = 0; i < n; i++) {
            ret[i] = list.get(n - 1 - i);
        }
        return ret;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] ans1 = solution.findBuildings(new int[]{4,3,2,1});
        System.out.println(Arrays.toString(ans1)); // [0,1,2,3]
        int[] ans2 = solution.findBuildings(new int[]{4,2,3,1});
        System.out.println(Arrays.toString(ans2)); // [0,2,3]
    }
}
{% endhighlight %}

Time complexity: O(n)

We iterate the input array once, and put indices into the list. This operation is O(n). Later, we reverse the list, which is also O(n). Overall, the time complexity should be O(n).

Space complexity: O(n)

The list we instantiated to temperaraly store the indices is O(n). The return array is also O(n). So, the overall space complexity should be O(n).