class Solution {
    public void sortColors(int[] nums) {

        int left = 0;
        int i = 0;
        int right = nums.length - 1;

        while (i <= right) {

            if (nums[i] == 0) {
                swap(nums, left, i);
                left++;
                i++;

            } else if (nums[i] == 1) {
                i++;

            } else {
                swap(nums, i, right);
                right--;
            }
        }
    }

    public void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }
}