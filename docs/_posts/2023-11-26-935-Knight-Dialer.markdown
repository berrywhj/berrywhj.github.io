---
layout: post
title:  "935 Knight Dialer"
date:   2023-11-26 11:52:03 -0600
categories: algorithms
---

Link to the problem: [LeetCode 935 Knight Dialer](https://leetcode.com/problems/knight-dialer/) 

My intuitive way of solving this problem is to use `backtrack` to calculate all possible phone number combinations. And with the help of the `set`, we can `avoid repetitiveness`. However, both the `scale` of n which is larger than 1 and smaller than 5000 and the description that the return value might be very large so we need to return the answer `modulo` 10^9 + 7 are suggesting that the usage of backtrack is going to cause `time limit exceed`.

We can try to start with n = 1. In this situation, all numbers can form a unique phone number at the length of 1. So our return value is 10.

If n = 2, we can divide all possible combinations into different categories. If n >= 2, There are actually 9 different categories (There can be no phone number start with 5 if the length of the phone number is expected to be larger than 1, since that there are no  possible movements after we move to 5)

    0. the phone numbers end with 0
    1. the phone numbers end with 1
    ... ... ... ...
    8. the phone numbers end with 8
    9. the phone numbers end with 9

| From      | Can Jump To |
| ----------- | ----------- |
|0|	4, 6|
|1|	6, 8|
|2|	7, 9|
|3|	4, 8|
|4|	3, 9, 0|
|5| |
|6|	1, 7, 0|
|7|	2, 6|
|8|	1, 3|
|9|	2, 4|

Suppose the number of uique phone number combinations of length `m` (1 < m < n) end with `0` equals to `x0`, number of those end with `1` equals to `x1` and so on. Accroding to the table, we can know the the number of uique phone number combinations of length `m + 1` end with `0` equals to `x4 + x6` (as we know that only when the `previous digit` is `4 or 6`, then the `next digit` could be `0`). From the same logic, the number of uique phone number combinations of length `m + 1` end with `1` equals to `x6 + x9`.

Now we found the pattern, we can define the transfer equation

    Coubinations(n, 0) = Coubinations(n - 1, 4) + Coubinations(n - 1, 6) n >= 2
    Coubinations(n, 1) = Coubinations(n - 1, 6) + Coubinations(n - 1, 8) n >= 2
    Coubinations(n, 2) = Coubinations(n - 1, 7) + Coubinations(n - 1, 9) n >= 2
    Coubinations(n, 3) = Coubinations(n - 1, 4) + Coubinations(n - 1, 8) n >= 2
    Coubinations(n, 4) = Coubinations(n - 1, 0) + Coubinations(n - 1, 3) + Coubinations(n - 1, 9) n >= 2
    Coubinations(n, 5) = 0 n >= 2
    Coubinations(n, 6) = Coubinations(n - 1, 0) + Coubinations(n - 1, 1) + Coubinations(n - 1, 7) n >= 2
    Coubinations(n, 7) = Coubinations(n - 1, 2) + Coubinations(n - 1, 6) n >= 2
    Coubinations(n, 8) = Coubinations(n - 1, 1) + Coubinations(n - 1, 3) n >= 2
    Coubinations(n, 9) = Coubinations(n - 1, 2) + Coubinations(n - 1, 4) n >= 2

    Coubinations(1, x) = 1

    All possible combitions at the length of n = Coubinations(n, 0) + Coubinations(n, 1) + ... + Coubinations(n, 9)

The solution below is insipred by this thought. Notice that we reuse two arrays of long type to optimize the space complexity to O(1).

{% highlight java %}
class Solution {
    int MOD = 1_000_000_007;
    public int knightDialer(int n) {
        if (n == 1) return 10;
        long ret = 0;
        long[] arr = new long[10];
        long[] temp = new long[10];
        Arrays.fill(arr, 1);
        for (int i = 1; i < n; i++) {
            long[] tt;
            temp[0] = (arr[4] + arr[6]) % MOD;
            temp[1] = (arr[6] + arr[8]) % MOD;
            temp[2] = (arr[7] + arr[9]) % MOD;
            temp[3] = (arr[4] + arr[8]) % MOD;
            temp[4] = (arr[0] + arr[3] + arr[9]) % MOD;
            temp[5] = 0;
            temp[6] = (arr[0] + arr[1] + arr[7]) % MOD;
            temp[7] = (arr[2] + arr[6]) % MOD;
            temp[8] = (arr[1] + arr[3]) % MOD;
            temp[9] = (arr[2] + arr[4]) % MOD;
            tt = arr;
            arr = temp;
            temp =tt;
        }
        for (long num: arr) {
            ret = (ret + num) % MOD;
        }
        return (int)ret;
    }
}
{% endhighlight %}

Further, if we take advantage of the symmetry of the dial board, we can categorize the digits into groups, and come up with such solution:

{% highlight java %}
class Solution {
    int MOD = 1_000_000_007;
    public int knightDialer(int n) {
        if (n == 1) return 10;
        long[] records = new long[]{4,2,2,1};
        long a = 4;
        long b = 2;
        long c = 2;
        long d = 1;
        long ret = 0;
        for (int i = 1; i < n; i++) {
            long tempa = 2 * (b + c) % MOD;
            long tempb = a % MOD;
            long tempc = (a + 2 * d) % MOD;
            long tempd = c % MOD;
            a = tempa;
            b = tempb;
            c = tempc;
            d = tempd;
        }
        ret = (ret + a) % MOD;
        ret = (ret + b) % MOD;
        ret = (ret + c) % MOD;
        ret = (ret + d) % MOD;
        return (int)ret;
    }
}

{% endhighlight %}



