class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];

        long[] dp = new long[k];

        for (int num : nums) {
            long[] newDp = new long[k];

            int val = num % k;

            // Start a new subarray with nums[i]
            newDp[val]++;

            // Extend previous subarrays
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRemainder = (int) ((long) r * val % k);
                    newDp[newRemainder] += dp[r];
                }
            }

            dp = newDp;

            // Every subarray is one possible remaining array
            for (int r = 0; r < k; r++) {
                result[r] += dp[r];
            }
        }

        return result;
    }
}