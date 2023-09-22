package algorithmns.DP.LongestArithmeticSubsequence;

import java.util.HashMap;
import java.util.Map;

class LongestArithmeticSubsequence {
    public int longestSubsequence(int[] arr, int difference) {
        int maxLen = 0;
        int ret = 0;
        int n = arr.length;
        Map<Integer, Integer>[] dp = new HashMap[n];
        for (int i = 0; i < n; i++) {
            dp[i] = new HashMap<Integer, Integer>();
            dp[i].put(0, 1);
        }
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int localDifference = arr[i] - arr[j];
                if (dp[j].containsKey(localDifference)) {
                    int len = dp[j].get(localDifference);
                    if (!dp[i].containsKey(localDifference)) {
                        dp[i].put(localDifference, 1 + len);
                    } else {
                        dp[i].put(localDifference, Math.max(dp[i].get(i), 1 + len));
                    }
                } else {
                    dp[i].put(localDifference, 2);
                }
                if (dp[i].get(localDifference) > maxLen) {
                    ret = localDifference;
                }
            }
        }
        return ret;
    }
}
