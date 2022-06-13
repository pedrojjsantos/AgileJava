package etc;

import java.util.*;

public class WordCount {
    private final String originalString;
    private final HashMap<String, Integer> count = new HashMap<>();

    public WordCount(String str) {
        this.originalString = str;

        String[] words = originalString.toLowerCase().split("\\W+");
        for (String word : words)
            incCount(word);
    }

    private void incCount(String word) {
        int n = count.getOrDefault(word, 0);
        count.put(word, n+1);
    }
    public Set<String> getSet() {
        Set<String> set = new HashSet<>();

        for (Map.Entry<String, Integer> entry : count.entrySet())
            set.add(entry.getKey() + ": " + entry.getValue());

        return set;
    }

    public String getString() {
        return originalString;
    }
}
