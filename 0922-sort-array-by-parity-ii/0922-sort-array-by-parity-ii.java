class Solution {
    public int[] sortArrayByParityII(int[] nums) {

        int even = 0;
        int odd = 1;

        while (even < nums.length && odd < nums.length) {

            if (nums[even] % 2 == 0) {
                even += 2;
            } else {
                while (odd < nums.length && nums[odd] % 2 == 1) {
                    odd += 2;
                }

                int temp = nums[even];
                nums[even] = nums[odd];
                nums[odd] = temp;

                even += 2;
                odd += 2;
            }
        }

        return nums;
    }
}