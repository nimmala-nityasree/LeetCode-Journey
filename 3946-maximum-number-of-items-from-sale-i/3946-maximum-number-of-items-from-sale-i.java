
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {
        int n = items.length;

        int[] free = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    free[i]++;
                }
            }
        }

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;

        Arrays.sort(order, (a, b) ->
            items[b][1] - items[a][1]);

        int NEG = -1000000000;
        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        int ans = 0;
        int k = 0;

        while (k < n) {
            int p = items[order[k]][1];

            int[] with = new int[budget + 1];
            Arrays.fill(with, NEG);

            int end = k;

            while (end < n && items[order[end]][1] == p) {
                int i = order[end];
                int cost = p;
                int value = 1 + free[i];

                for (int b = budget; b >= cost; b--) {
                    if (dp[b - cost] != NEG) {
                        with[b] = Math.max(with[b],
                            dp[b - cost] + value);
                    }

                    if (with[b - cost] != NEG) {
                        with[b] = Math.max(with[b],
                            with[b - cost] + value);
                    }
                }

                end++;
            }

            for (int b = 0; b <= budget; b++) {
                if (with[b] != NEG) {
                    ans = Math.max(ans,
                        with[b] + (budget - b) / p);

                    dp[b] = Math.max(dp[b], with[b]);
                }
            }

            k = end;
        }

        return ans;
    }
}
