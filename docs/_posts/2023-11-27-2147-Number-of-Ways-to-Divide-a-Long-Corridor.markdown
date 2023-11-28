---
layout: post
title:  "2147 Number of Ways to Divide a Long Corridor"
date:   2023-11-27 11:47:03 -0600
categories: algorithms greedy
---

Link to the problem: [LeetCode2147 Number of Ways to Divide a Long Corridor](https://leetcode.com/problems/number-of-ways-to-divide-a-long-corridor/) 

A very basic observation is that if there are no seats or odd number of seats in our Corridor, there could be no way to divide it, which means if the number of `'S'` in the input string `corridor` is 0 or odd, the return answer should be 0.

If we define the first seat we encounter as the `first seat`, which is an `odd` number, and the `second seat` tagged as `even`, we can found that the divider can be only placed between the even seat and the following odd seat. Further, the indices difference between these two seats, happen to be the number of possibilities that the divier can be put between these two seats.

Say we got `S(1), S(2), S(3) ... S(2n - 1), S(2n)` at total `2n` number of `'S'` in the input string  `corridor`. Since there have to be a divider after every 2 consecutive seats(expect the last two seats), we need to place `n - 1` divider in the long corridor. And from the conclusion above, the number of possible positions of placing a divider between `S(2)` and `S(3)` equal to the index of `S(3)` substract the index of `S(2)`, the number of possible positions of placing a divider between `S(4)` and `S(5)` equal to the index of `S(5)` substract the index of `S(4)` and so on. There are `n - 1` dividers to be placed, and the final number of posibilities equals to the product of the number of possibilities of each divider.

    In the language of math:
    P = P1 * P2 * ... * Pn-1
    
    Since:
    P1 = indexOf(S(3)) - indexOf(S(2))
    P2 = indexOf(S(5)) - indexOf(S(4))
    ...
    Pn-1 = indexOf(S(2n-1)) - indexOf(S(2n-2))

    We get:
    P = (indexOf(S(3)) - indexOf(S(2))) * (indexOf(S(5)) - indexOf(S(4))) * ... * (indexOf(S(2n-1)) - indexOf(S(2n-2)))

With this idea, we come up with the following solution:

{% highlight java %}
class Solution {
    int MOD = 1_000_000_007;
    public int numberOfWays(String corridor) {
        long ret = 1;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                list.add(i);
            }
        }
        if (list.size() == 0 || list.size() % 2 != 0) return 0;
        for (int i = 1; i + 1 < list.size(); i += 2) {
            ret = (ret * (list.get(i + 1) - list.get(i))) % MOD;
        }

        return (int)ret;
    }
}
{% endhighlight %}

Further, We can even spare the work of creating the list that we use to store the indices of `'S'`. Instead, we keep track of the lastIndex of the 'S' and the maintain a flag that shows the count of 'S' we encounter so far is odd or even while calculating the final product throught only one iteration of the input corridor string. In this way, we can optimize the space complexity to O(1). The algorithm is shown as follows:

{% highlight java %}
class Solution {
    int MOD = 1_000_000_007;
    public int numberOfWays(String corridor) {
        long ret = 1;
        boolean isEven = true;
        int lastIdx = -1;
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                isEven = !isEven;
                if (!isEven && lastIdx != -1) {
                    ret = (ret * (i - lastIdx)) % MOD;
                }
                lastIdx = i;
            }
        }

        return (isEven && lastIdx != -1) ? (int)ret : 0;
    }
}
{% endhighlight %}



