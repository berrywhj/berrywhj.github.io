---
layout: post
title:  "2982. Find Longest Special Substring That Occurs Thrice II
"
date:   2024-01-01 17:10:55 -0600
categories: algorithms greedy
---

Link to the problem: [2982. Find Longest Special Substring That Occurs Thrice II
](https://leetcode.com/problems/find-longest-special-substring-that-occurs-thrice-ii/) 

A `special` string only contains one kind of character. In this problem, We are expected to return the `longest special subtring` of s which occurs `at least thrice`.

Since the input string s only contains lowercase English letters. We can divide this problem into 26 sub-problems: Find the longest substring composed only by a/b/c.../z that occurs thrice. The final answer would be the max value among all these sub-problems. To solve each sub-problem, we shall iterate the input string s once, and utilize a min priority queue to get the largest three lengths that the target character consecutively exist. In this way we are going to iterate the input string s for 26 times, as 26 is a constant number, we can assume that the time complexity would still be O(n) (or we could say linear time complexity). In each sub-problem, we can reuse the min priority queue. As we shall add at most 4 numbers into this queue, we can assume that the space complexity would be O(1) (or we could say constant space complexity)

To get the answer for each sub-probelm, we need to process the lengths we store in the min priority queue.

    When there are three length in queue, and we remove the min length out of the queue:
    Since this is a min PQ, we can expect that the remaining two lengths in the queue are equal or larger than the length we just removed, which means that this min length occurs at least three times. So, the min length could be our possible answer.

    If we found that there is only one length in the queue after we retrive a length (here we can call it medium sized length) from it. For example we get 4 out of the queue, and there is a 5 remains in the queue. 1. If the one remains is strictly larger than the medium sized length, the medium sized one could be our possible answer. (Since the one remains is larger than the medium sized length, we can expect the medium sized length occurs at least twice in the larger sized length. And after we add the medium sized length itself, it would be 3 times that the length occurs at least.) In the case of 4 and 5, we know that 4 could be a possible answer. 2. If the one remains is equal to the medium sized length and if they are both larger than 1, say 2 and 2, we can expect that the length of `medium sized length - 1` occurs at least 3 times (or 4 times to be more specific, but 3 is sufficient for our probelm).

    For each length, as long as it strictly exceeds 2,  the result that `the length - 2` could also be our possible answer.


    

To put the idea above into solid Java code:

{% highlight java %}
class Solution {
    public int maximumLength(String s) {
        int ret = -1;
        for (int i = 0; i < 26; i++) {
            ret = Math.max(ret, check(s, (char)('a' + i)));
        }

        return ret;
    }
    
    public int check(String s, char ch) {
        int ret = -1;
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        int cnt = 0;
        for (char nowCh: s.toCharArray()) {
            if (nowCh == ch) {
                cnt++;
            } else if (cnt > 0) {
                pq.add(cnt);
                if (pq.size() > 3) pq.remove();
                cnt = 0;
            }
        }
        if (cnt > 0) {
            pq.add(cnt);
            if (pq.size() > 3) pq.remove();
        }
        while (!pq.isEmpty()) {
            int count = pq.remove();
            if (pq.size() == 2) {
                ret = Math.max(ret, count);
            } else if (pq.size() == 1) {
                if (pq.peek() > count) {
                    ret = Math.max(ret, count);
                } else if (pq.peek() == count && count >= 2) {
                    ret = Math.max(ret, count - 1);
                }
            }
            if (count > 2) ret = Math.max(ret, count - 2);
        }
        return ret;
    }
}
{% endhighlight %}



