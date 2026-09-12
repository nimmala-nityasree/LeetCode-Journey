class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        Arrays.sort(a, (x, y) -> x[0] != y[0] ? x[0] - y[0] : x[3] - y[3]);

        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            int l = i + 1;
            int r = n - 1;
            next[i] = n;

            while (l <= r) {
                int m = (l + r) / 2;

                if (a[m][0] > a[i][1]) {
                    next[i] = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
        }

        long[][] dp = new long[n + 1][5];
        int[][] count = new int[n + 1][5];
        int[][][] best = new int[n + 1][5][4];

        for (int i = n - 1; i >= 0; i--) {
            for (int k = 1; k <= 4; k++) {

                long skipScore = dp[i + 1][k];

                int[] take = new int[4];
                int takeCount = count[next[i]][k - 1] + 1;

                take[0] = a[i][3];

                for (int j = 0; j < count[next[i]][k - 1]; j++) {
                    take[j + 1] = best[next[i]][k - 1][j];
                }

                for (int j = 1; j < takeCount; j++) {
                    int x = take[j];
                    int p = j - 1;

                    while (p >= 0 && take[p] > x) {
                        take[p + 1] = take[p];
                        p--;
                    }

                    take[p + 1] = x;
                }

                long takeScore = a[i][2] + dp[next[i]][k - 1];

                if (takeScore > skipScore ||
                    (takeScore == skipScore &&
                     smaller(take, takeCount, best[i + 1][k], count[i + 1][k]))) {

                    dp[i][k] = takeScore;
                    count[i][k] = takeCount;

                    for (int j = 0; j < takeCount; j++) {
                        best[i][k][j] = take[j];
                    }

                } else {
                    dp[i][k] = skipScore;
                    count[i][k] = count[i + 1][k];

                    for (int j = 0; j < count[i + 1][k]; j++) {
                        best[i][k][j] = best[i + 1][k][j];
                    }
                }
            }
        }

        return Arrays.copyOf(best[0][4], count[0][4]);
    }

    private boolean smaller(int[] a, int n, int[] b, int m) {
        int size = Math.min(n, m);

        for (int i = 0; i < size; i++) {
            if (a[i] != b[i]) {
                return a[i] < b[i];
            }
        }

        return n < m;
    }
}

