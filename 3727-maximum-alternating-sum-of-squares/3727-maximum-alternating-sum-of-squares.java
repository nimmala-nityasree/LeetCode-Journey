import java.util.Arrays;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        Integer[] arr = new Integer[nums.length];

        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }

        Arrays.sort(arr, (a, b) ->
            Long.compare(Math.abs((long) b), Math.abs((long) a))
        );

        long sum = 0;
        for (int i = 0; i < arr.length; i++) {
            long num = arr[i];
            long square = num * num;

            if (i < (arr.length + 1) / 2) {
                sum += square;
            } else {
                sum -= square;
            }
        }

        return sum;
    }
}