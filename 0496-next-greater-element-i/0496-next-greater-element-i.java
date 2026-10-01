class Solution {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        int n = nums2.length;
        Deque<Integer> st = new ArrayDeque<>();
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int i = n - 1; i >= 0; i--) {
            int curr = nums2[i];

            while (!st.isEmpty() && curr > st.peek()) {
                st.pop();
            }

            if (st.isEmpty()) {
                map.put(curr, -1);
            } else {
                map.put(curr, st.peek());
            }

            st.push(curr);
        }

        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }

        return ans;
    }
}