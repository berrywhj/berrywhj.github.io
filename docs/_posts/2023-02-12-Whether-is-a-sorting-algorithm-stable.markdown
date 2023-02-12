---
layout: post
title:  "Whether is a sorting algorithm stable"
date:   2023-02-12 01:35:00 -0600
categories: algorithms sort
---
When we can call a sorting algorithm `stable`? A sorting algorithm is said to be `stable` if two equal elements appear in the same order after being sorted as they appear in the input.

We know that `bubble sort`, `insert sort` are all `stable` sorting algorithms.
`Quick sort`, `selection sort`, `shell sort`, `heap sort`, they are `unstable`.

Merge sort is also stable. There is a merge phase in merge sort algorithm, try to change one line, to make it from stable to unstalble.

{% highlight java %}
public int[] merge(int[] nums1, int[] nums2) {
    int ll = nums1.length;
    int rl = nums2.length;
    // System.out.println(ll);
    // System.out.println(rl);
    int l = 0;
    int r = 0;
    int[] mergedNums = new int[ll + rl];
    int i = 0;
    while (l < ll && r < rl) {
        if (nums1[l] <= nums2[r]) {
            mergedNums[i] = nums1[l];
            l++;
        } else {
            mergedNums[i] = nums2[r];
            r++;
        }
        i++;
    }
    while (l < ll) {
        mergedNums[i++] = nums1[l++];
    }
    while (r < rl) {
        mergedNums[i++] = nums2[r++];
    }
    return mergedNums;
}
{% endhighlight %}

The trick is to change the line `if (nums1[l] <= nums2[r])` to `if (nums1[l] < nums2[r])`. After that, equal elements would appear in the `reverse order` after being sorted as they appear in the input, which makes the merge sort algorithm `unstable`.

Count sort is unstable, which we shall talk a little bit later.