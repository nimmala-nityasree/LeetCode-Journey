class Solution {
    public int[] nextGreaterElements(int[] nums) {

        int n = nums.length;
        int[] res = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        for (int i = 2 * n - 1; i >= 0; i--) {

            int curr = nums[i % n];

            while (!stack.isEmpty() && nums[stack.peek()] <= curr) {
                stack.pop();
            }

            if (i < n) {
                if (stack.isEmpty()) {
                    res[i] = -1;
                } else {
                    res[i] = nums[stack.peek()];
                }
            }

            stack.push(i % n);
        }

        return res;
    }
}