class Solution {
    int index = 0;

    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        return new ArrayList<>(new TreeSet<>(result));
    }

    Set<String> parse(String expression) {
        Set<String> result = new HashSet<>();
        Set<String> current = new HashSet<>();
        current.add("");

        while (index < expression.length() && expression.charAt(index) != '}') {
            char ch = expression.charAt(index);

            if (ch == '{') {
                index++;
                current = combine(current, parse(expression));
                index++;
            } 
            else if (ch == ',') {
                result.addAll(current);
                current = new HashSet<>();
                current.add("");
                index++;
            } 
            else {
                Set<String> letter = new HashSet<>();
                letter.add(String.valueOf(ch));
                current = combine(current, letter);
                index++;
            }
        }

        result.addAll(current);
        return result;
    }

    Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}