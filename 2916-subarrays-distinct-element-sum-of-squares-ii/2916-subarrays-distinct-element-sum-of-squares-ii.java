class Solution {
    static final int MOD = 1_000_000_007;

    int[] sum;
    int[] sq;
    int[] lazy;

    public int sumCounts(int[] nums) {
        int n = nums.length;
        sum = new int[4 * n];
        sq = new int[4 * n];
        lazy = new int[4 * n];

        int[] last = new int[100001];
        long answer = 0;

        for (int i = 0; i < n; i++) {
            int left = last[nums[i]];
            update(1, 0, n - 1, left, i, 1);

            answer = (answer + sq[1]) % MOD;
            last[nums[i]] = i + 1;
        }

        return (int) answer;
    }

    void update(int node, int start, int end, int l, int r, int val) {
        if (l > end || r < start) return;

        if (l <= start && end <= r) {
            apply(node, start, end, val);
            return;
        }

        push(node, start, end);

        int mid = (start + end) / 2;
        update(node * 2, start, mid, l, r, val);
        update(node * 2 + 1, mid + 1, end, l, r, val);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;
        sq[node] = (int) (((long) sq[node * 2] + sq[node * 2 + 1]) % MOD);
    }

    void apply(int node, int start, int end, int val) {
        long len = end - start + 1L;

        sq[node] = (int) ((sq[node] + 2L * val * sum[node] + len * val * val) % MOD);
        sum[node] = (int) ((sum[node] + len * val) % MOD);
        lazy[node] += val;
    }

    void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) return;

        int mid = (start + end) / 2;
        apply(node * 2, start, mid, lazy[node]);
        apply(node * 2 + 1, mid + 1, end, lazy[node]);
        lazy[node] = 0;
    }
}