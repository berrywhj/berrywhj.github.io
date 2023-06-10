class LongestIncreasingSubsequence {
  public int lengthOfLIS(int[] nums) {
      int n = nums.length;
      int[] dp = new int[n];
      int[] prev = new int[n];
      
      for (int i = 0; i < n; i++) {
          dp[i] = 1;
          prev[i] = -1;
      }
      for (int i = 1; i < n; i++) {
          for (int j = 0; j < i; j++) {
              if (nums[j] < nums[i] && dp[i] < dp[j] + 1) {
                  dp[i] = dp[j] + 1;
                  prev[i] = j;
              }
          }
      }
      int ret = -Integer.MIN_VALUE;
      int idx = 0;
      for (int i = 0; i < n; i++) {
        // System.out.println(dp[i]);
          if (ret < dp[i]) {
              ret = dp[i];
              idx = i;
          }
      }
      // for (int i: prev) {
      //   System.out.println(i);
      // }
      while (idx != -1) {
          System.out.println(nums[idx]);
          idx = prev[idx];
      }
      return ret;
  }

  public static void main(String[] args) {
    LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
    lis.lengthOfLIS(new int[]{7,4,6,5,8,2,10,3});
  }
}