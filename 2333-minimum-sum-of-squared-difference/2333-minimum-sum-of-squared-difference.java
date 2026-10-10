class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int[] count = new int[100001];
        long operations = (long) k1 + k2;
        long total = 0;

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);
            count[diff]++;
            total += diff;
        }

        if (operations >= total) {
            return 0;
        }

        for (int diff = 100000; diff > 0 && operations > 0; diff--) {
            int move = (int) Math.min(operations, count[diff]);

            count[diff] -= move;
            count[diff - 1] += move;
            operations -= move;
        }

        long result = 0;

        for (int diff = 0; diff <= 100000; diff++) {
            result += (long) diff * diff * count[diff];
        }

        return result;
    }
}