public class InterspersingString {
  public boolean Solution(String strA, String strB, String strC) {
    int m = strA.length();
    char[] aArr = strA.toCharArray();
    char[] bArr = strB.toCharArray();
    char[] cArr = strC.toCharArray();
    int n = strB.length();
    boolean[][] dp = new boolean[m + 1][n + 1];
    dp[0][0] = true;
    for (int i = 1; i <= m; i++) {
      if (cArr[i-1] == aArr[i-1] && dp[i-1][0]) {
        dp[i][0] = true;
      }
    }
    for (int j = 1; j <= n; j++) {
      if (cArr[j - 1] == bArr[j - 1] && dp[0][j - 1]) {
        dp[0][j] = true;
      }
    }
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= n; j++) {
        if ((cArr[i+j-1] == aArr[i - 1] && dp[i-1][j]) || (cArr[i+j-1] == bArr[j - 1] && dp[i][j - 1])) {
          dp[i][j] = true;
        }
      }
    }
    return dp[m][n];
  }

  public static void main(String[] args) {
    String A = "DYNAMIC";
    String B = "PROGRAMMING";
    String C1 = "PRODGYRNAMAMMIINCG";
    String C2 = "DYPRONGARMAMMICING";
    InterspersingString ISS = new InterspersingString();
    System.out.println(ISS.Solution(A, B, C1));
    System.out.println(ISS.Solution(A, B, C2));
  }
}
