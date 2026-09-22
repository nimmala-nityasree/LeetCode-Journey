class Solution {
    static class Node {
        int product;
        int[] prefix;

        Node(int k) {
            prefix = new int[k];
        }
    }

    int k;
    Node[] tree;

    Node merge(Node left, Node right) {
        Node res = new Node(k);

        res.product = (left.product * right.product) % k;

        for (int r = 0; r < k; r++) {
            res.prefix[r] += left.prefix[r];
        }

        for (int r = 0; r < k; r++) {
            int newRemainder = (left.product * r) % k;
            res.prefix[newRemainder] += right.prefix[r];
        }

        return res;
    }

    void build(int node, int start, int end, int[] nums) {
        if (start == end) {
            tree[node] = new Node(k);

            int remainder = nums[start] % k;
            tree[node].product = remainder;
            tree[node].prefix[remainder] = 1;
            return;
        }

        int mid = (start + end) / 2;

        build(node * 2, start, mid, nums);
        build(node * 2 + 1, mid + 1, end, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = new Node(k);

            int remainder = value % k;
            tree[node].product = remainder;
            tree[node].prefix[remainder] = 1;
            return;
        }

        int mid = (start + end) / 2;

        if (index <= mid) {
            update(node * 2, start, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, end, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    Node query(int node, int start, int end, int left, int right) {
        if (left <= start && end <= right) {
            return tree[node];
        }

        int mid = (start + end) / 2;

        if (right <= mid) {
            return query(node * 2, start, mid, left, right);
        }

        if (left > mid) {
            return query(node * 2 + 1, mid + 1, end, left, right);
        }

        Node a = query(node * 2, start, mid, left, right);
        Node b = query(node * 2 + 1, mid + 1, end, left, right);

        return merge(a, b);
    }

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;

        int n = nums.length;
        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] result = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {
            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            update(1, 0, n - 1, index, value);

            Node answer = query(1, 0, n - 1, start, n - 1);

            result[q] = answer.prefix[x];
        }

        return result;
    }
}