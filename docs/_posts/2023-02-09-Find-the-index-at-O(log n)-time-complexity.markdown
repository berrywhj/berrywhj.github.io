---
layout: post
title:  "Find the index at O(log n) time complexity"
date:   2023-02-10 01:37:00 -0600
categories: algorithms binary-search
---
There is an array A\[1,2...n\], it is guaranteed that there exists a position k, 1 <= k < n, where the items indexd from 1 to k <= 32768, and items indexed from k to n > 32768, you need to come up with an O(log n) time complexity algorithm.

We shall try to tackle this problem with `real-world` programming languages, thus our array starts at the `index of 0`.

Code written in Java:
{% highlight java %}
public class FindTheIndex {
  int NUM = 32768;
  public int solution(int[] arr) {
    return helper(arr, 0, arr.length - 1);
  }

  private int helper(int[] arr, int l, int r) {
    if (l == r) return l;
    int mid = (r - l) / 2 + l;
    if (arr[mid] <= NUM) {
      if (arr[mid + 1] > NUM) {
        return mid;
      } else {
        return helper(arr, mid + 1 , r);
      }
    } else {
      return helper(arr, l, mid - 1);
    }
  }

  public static void main(String[] args) {
    FindTheIndex findTheIndex = new FindTheIndex();
    int[] testarr1 = {231, 32768, 32769};
    int[] testarr2 = {231, 2, 5, 40000};
    System.out.println(findTheIndex.solution(testarr1)); // 1
    System.out.println(findTheIndex.solution(testarr2)); // 2
  }
}
{% endhighlight %}

Code written in JavaScript:
{% highlight javascript %}
const findTheIndex = (arr) => {
  const NUM = 32768;
  const helper = (arr, l, r) => {
    if (l === r) return l;
    const mid = ((r - l) >> 1) + l;
    if (arr[mid] <= NUM) {
      if (arr[mid + 1] > NUM) {
        return mid;
      } else {
        return helper(arr, mid + 1, r);
      }
    } else {
      return helper(arr, l, mid - 1);
    }
  };
  return helper(arr, 0, arr.length - 1);
};

const testarr1 = [231, 32768, 32769];
const testarr2 = [231, 2, 5, 40000];

console.log(findTheGap(testarr1)); // 1
console.log(findTheGap(testarr2)); // 2
{% endhighlight %}