import java.util.Arrays;

class Solution {
    int MOD = 1_000_000_007;
    public int knightDialer(int n) {
        if (n == 1) return 10;
        long ret = 0;
        long[] arr = new long[10];
        long[] temp = new long[10];
        Arrays.fill(arr, 1);
        for (int i = 1; i < n; i++) {
            long[] tt;
            temp[0] = (arr[4] + arr[6]) % MOD;
            temp[1] = (arr[6] + arr[8]) % MOD;
            temp[2] = (arr[7] + arr[9]) % MOD;
            temp[3] = (arr[4] + arr[8]) % MOD;
            temp[4] = (arr[0] + arr[3] + arr[9]) % MOD;
            temp[5] = 0;
            temp[6] = (arr[0] + arr[1] + arr[7]) % MOD;
            temp[7] = (arr[2] + arr[6]) % MOD;
            temp[8] = (arr[1] + arr[3]) % MOD;
            temp[9] = (arr[2] + arr[4]) % MOD;
            tt = arr;
            arr = temp;
            temp =tt;
        }
        for (long num: arr) {
            ret = (ret + num) % MOD;
        }
        return (int)ret;
    }
}

// class Solution {
//     int MOD = 1_000_000_007;
//     public int knightDialer(int n) {
//         if (n == 1) return 10;
//         long a = 4;
//         long b = 2;
//         long c = 2;
//         long d = 1;
//         long ret = 0;
//         for (int i = 1; i < n; i++) {
//             long tempa = 2 * (b + c) % MOD;
//             long tempb = a % MOD;
//             long tempc = (a + 2 * d) % MOD;
//             long tempd = c % MOD;
//             a = tempa;
//             b = tempb;
//             c = tempc;
//             d = tempd;
//         }
//         ret = (ret + a) % MOD;
//         ret = (ret + b) % MOD;
//         ret = (ret + c) % MOD;
//         ret = (ret + d) % MOD;
//         return (int)ret;

//     }
// }