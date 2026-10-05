class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Deque<Integer> deque = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i <= n; i++) {
            int curr = (i == n) ? 0 : heights[i];

            while (!deque.isEmpty() && curr < heights[deque.peekLast()]) {
                int height = heights[deque.removeLast()];
                int width = deque.isEmpty() ? i : i - deque.peekLast() - 1;
                maxArea = Math.max(maxArea, height * width);
            }

            deque.addLast(i);
        }

        return maxArea;
    }
}