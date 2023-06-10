import java.util.*;
public class Knapsack {
  public int getMaxValue(int[] w, int[] v, int limit) {
    int n = w.length;
    int[][] dp = new int[n + 1][limit + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= limit; j++) {
        if (w[i - 1] <= j) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i - 1]] + v[i - 1]);
        }
        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
      }
    }
    return dp[n][limit];
  }

  public Set getObjects(int[] w, int[] v, int limit) {
    int n = w.length;
    int[][] dp = new int[n + 1][limit + 1];
    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= limit; j++) {
        if (w[i - 1] <= j) {
          dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - w[i - 1]] + v[i - 1]);
        }
        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
      }
    }
    int i = n;
    int j = limit;
    Set<Integer> set = new HashSet<>();
    System.out.println(i);
    System.out.println(j);
    while (i != 0 && j != 0) {
      System.out.println(dp[i][j]);
      if (j >= w[i - 1] && dp[i][j] - v[i - 1] == dp[i - 1][j - w[i - 1]]) {
        System.out.println(dp[i - 1][j - w[i - 1]]);
        set.add(i);
        j = j- w[i - 1];
      }
      i--;
    }
    return set;
  }

  public static void main(String[] args) {
    Knapsack knapsack = new Knapsack();
    int[] w = {1,2,5,6,7};
    int[] v = {1,6,18,22,28};
    System.out.println(knapsack.getMaxValue(w, v, 11));
    System.out.println(knapsack.getObjects(w, v, 11));
  }
}