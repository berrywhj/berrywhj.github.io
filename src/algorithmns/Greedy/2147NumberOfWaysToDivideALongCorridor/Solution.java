class Solution {
    int MOD = 1_000_000_007;
    // public int numberOfWays(String corridor) {
    //     long ret = 1;
    //     List<Integer> list = new ArrayList<>();
    //     for (int i = 0; i < corridor.length(); i++) {
    //         if (corridor.charAt(i) == 'S') {
    //             list.add(i);
    //         }
    //     }
    //     if (list.size() == 0 || list.size() % 2 != 0) return 0;
    //     for (int i = 1; i + 1 < list.size(); i += 2) {
    //         ret = (ret * (list.get(i + 1) - list.get(i))) % MOD;
    //     }

    //     return (int)ret;
    // }
    public int numberOfWays(String corridor) {
        long ret = 1;
        boolean isEven = true;
        int lastIdx = -1;
        for (int i = 0; i < corridor.length(); i++) {
            if (corridor.charAt(i) == 'S') {
                isEven = !isEven;
                if (!isEven && lastIdx != -1) {
                    ret = (ret * (i - lastIdx)) % MOD;
                }
                lastIdx = i;
            }
        }

        return (isEven && lastIdx != -1) ? (int)ret : 0;
    }
}