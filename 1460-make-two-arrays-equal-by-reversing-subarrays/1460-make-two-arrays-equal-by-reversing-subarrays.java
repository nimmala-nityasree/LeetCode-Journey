class Solution {
    public boolean canBeEqual(int[] target, int[] arr) {

        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : target) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        for (int num : arr) {

            if (!map.containsKey(num)) {
                return false;
            }

            map.put(num, map.get(num) - 1);
        }

        for (int count : map.values()) {
            if (count != 0) {
                return false;
            }
        }

        return true;
    }
}