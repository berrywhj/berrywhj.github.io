import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

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