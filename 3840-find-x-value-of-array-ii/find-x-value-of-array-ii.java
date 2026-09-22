class Solution {

    static class Node {
        int product;
        int[] count;

        Node(int k) {
            count = new int[k];
        }
    }

    int n, k;
    int[] nums;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {

        this.n = nums.length;
        this.k = k;
        this.nums = nums;

        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            nums[index] = value;
            update(1, 0, n - 1, index, value);

            // Query [start, n-1]
            Node res = query(1, 0, n - 1, start, n - 1);

            ans[q] = res.count[x];
        }

        return ans;
    }

    // ---------------- BUILD ----------------

    private void build(int node, int l, int r) {

        if (l == r) {

            tree[node] = new Node(k);

            int rem = nums[l] % k;

            tree[node].product = rem;
            tree[node].count[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        build(node * 2, l, mid);
        build(node * 2 + 1, mid + 1, r);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ---------------- MERGE ----------------

    private Node merge(Node left, Node right) {

        Node res = new Node(k);

        // Product of entire segment
        res.product = (left.product * right.product) % k;

        // Prefixes completely inside left
        for (int r = 0; r < k; r++) {
            res.count[r] += left.count[r];
        }

        // Prefixes that enter right
        for (int r = 0; r < k; r++) {

            int newRemainder = (left.product * r) % k;

            res.count[newRemainder] += right.count[r];
        }

        return res;
    }

    // ---------------- UPDATE ----------------

    private void update(int node, int l, int r, int index, int value) {

        if (l == r) {

            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].product = rem;
            tree[node].count[rem] = 1;

            return;
        }

        int mid = l + (r - l) / 2;

        if (index <= mid) {
            update(node * 2, l, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, r, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ---------------- QUERY ----------------

    private Node query(int node, int l, int r, int ql, int qr) {

        if (ql <= l && r <= qr) {
            return tree[node];
        }

        int mid = l + (r - l) / 2;

        if (qr <= mid) {
            return query(node * 2, l, mid, ql, qr);
        }

        if (ql > mid) {
            return query(node * 2 + 1, mid + 1, r, ql, qr);
        }

        Node left = query(node * 2, l, mid, ql, qr);
        Node right = query(node * 2 + 1, mid + 1, r, ql, qr);

        return merge(left, right);
    }
}