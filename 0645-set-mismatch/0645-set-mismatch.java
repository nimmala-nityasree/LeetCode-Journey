class Solution {
    public int[] findErrorNums(int[] nums) {

        int duplicate = -1;
        int missing = -1;

        for (int i = 0; i < nums.length; i++) {

            int num = Math.abs(nums[i]);
            int index = num - 1;

            if (nums[index] < 0) {
                duplicate = num;
            } else {
                nums[index] = -nums[index];
            }
        }

        for (int i = 0; i < nums.length; i++) {

            if (nums[i] > 0) {
                missing = i + 1;
                break;
            }
        }

        return new int[]{duplicate, missing};
    }
}
