import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import java.util.Arrays;
import java.util.HashSet;

public class Solution {
  int ret;
  public int bestSumAnyTreePath(List<Integer> parent, List<Integer> values) {
    ret = Integer.MIN_VALUE;
    int n = parent.size();
    Set<Integer>[] graph = new HashSet[n + 1];
    for (int i = 0; i < n + 1; i++) graph[i] = new HashSet<>();
    for (int i = 0; i < n; i++) {
      int p = parent.get(i);
      if (p != -1) {
        graph[p].add(i);
      }
    }
    graph[n].add(0);
    dfs(graph, values, n);
    return ret;
  }

  public int dfs(Set<Integer>[] graph, List<Integer> values, int curr) {
    Set<Integer> children = graph[curr];
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    for (int child: children) {
      pq.add(dfs(graph, values, child));
    }
    if (curr == graph.length - 1) return 0;
    int childMax = pq.size() == 0 ? 0 : Math.max(pq.remove(), 0);
    int childSecondMax = pq.size() == 0 ? 0 : Math.max(pq.remove(), 0);
    int currValue = values.get(curr);
    ret = Math.max(ret, currValue + childMax + childSecondMax);
    return Math.max(currValue + childMax, 0);
  }

  public static void main(String[] args) {
    Solution solution1 = new Solution();
    System.out.println(solution1.bestSumAnyTreePath(Arrays.asList(-1, 0, 0), Arrays.asList(10, 10, 10)));;
    System.out.println(solution1.bestSumAnyTreePath(Arrays.asList(-1, 0, 0), Arrays.asList(10, -1, 10)));;
    System.out.println(solution1.bestSumAnyTreePath(Arrays.asList(-1, 0, 0), Arrays.asList(-1, 10, 10)));;
    System.out.println(solution1.bestSumAnyTreePath(Arrays.asList(-1, 0, 0), Arrays.asList(-2, -1, -3)));;
  }
}
