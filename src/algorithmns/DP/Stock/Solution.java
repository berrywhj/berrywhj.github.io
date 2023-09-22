import java.util.ArrayList;

class Solution {
    public int maxProfit(int k, int[] prices) {
        int ret = 0;
        ArrayList<int[]>[] dp = new ArrayList[prices.length];
        
        // dp[0][0] = {-prices[0], 0};
        ArrayList<int[]> init = new ArrayList<>();
        int[] initArr = { -prices[0], 0 };
        init.add(initArr);
        dp[0] = init;
        for (int i = 1; i < prices.length; i++) {
            // dp[i] = new ArrayList<>(dp[i-1]);

            dp[i] = new ArrayList<>();
            for (int it = 0; it < dp[i-1].size(); it++) {
              int[] arr = new int[2];
              arr[0] = dp[i-1].get(it)[0];
              arr[1] = dp[i-1].get(it)[1];
              dp[i].add(arr);
            }
            int arraySizeCache = dp[i].size();
            for (int j = 0; j < Math.min(k, arraySizeCache); j++) {
                if (j == 0) {
                    dp[i].get(j)[0] = Math.max(-prices[i], dp[i-1].get(j)[0]);
                    dp[i].get(j)[1] = Math.max(dp[i-1].get(j)[0] + prices[i], dp[i-1].get(j)[1]);
                    System.out.println("i: " + i + " j: " + j +". " + dp[i].get(j)[0]);
                    System.out.println("i: " + i + " j: " + j +". " + dp[i].get(j)[1]);
                    continue;
                }
                if (dp[i-1].get(j-1)[1] == 0) {
                    break;
                }
                dp[i].get(j)[0] = Math.max(dp[i-1].get(j)[0], dp[i-1].get(j-1)[1] - prices[i]);
                dp[i].get(j)[1] = Math.max(dp[i-1].get(j)[1], dp[i-1].get(j)[0] + prices[i]);
                System.out.println("i: " + i + " j: " + j +". " + dp[i].get(j)[0]);
                System.out.println("i: " + i + " j: " + j +". " + dp[i].get(j)[1]);
            }
            if (arraySizeCache < k && dp[i-1].get(arraySizeCache-1)[1] > 0) {
                int[] additionalArr = new int[2];
                additionalArr[0] = dp[i-1].get(arraySizeCache - 1)[1] - prices[i];
                dp[i].add(additionalArr);
            }

        }
        for (int i = 0; i < dp[prices.length - 1].size(); i++) {
            ret = Math.max(ret, dp[prices.length - 1].get(i)[1]);
        }
        return ret;
    }
    
    public static void main(String[] args) {
        Solution sl = new Solution();
        int[] prices = {3,2,6,5,0,3};
        System.out.println("my test:");
        System.out.println(sl.maxProfit(2, prices));
    }
}