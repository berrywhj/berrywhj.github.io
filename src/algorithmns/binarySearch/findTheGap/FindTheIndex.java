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
    System.out.println(findTheIndex.solution(testarr1));
    System.out.println(findTheIndex.solution(testarr2));
  }
}