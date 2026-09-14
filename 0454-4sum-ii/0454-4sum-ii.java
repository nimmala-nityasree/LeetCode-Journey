class Solution {
    public int fourSumCount(int[] A, int[] B, int[] C, int[] D) {

        HashMap<Integer, Integer> map = new HashMap<>();

        // Store sums of A + B
        for (int a : A) {
            for (int b : B) {
                int sum = a + b;
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }

        int count = 0;

        // Check sums of C + D
        for (int c : C) {
            for (int d : D) {
                int sum = c + d;
                int needed = -sum;

                count += map.getOrDefault(needed, 0);
            }
        }

        return count;
    }
}
