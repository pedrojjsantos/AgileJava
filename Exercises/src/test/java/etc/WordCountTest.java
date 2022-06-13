package etc;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.*;

public class WordCountTest {
    @Test
    public void testWordCount() {
        String literal = """
                Create a String literal using the first two sentences of this exercise. You will create a
                WordCount class to parse through the text and count the number of instances of each
                word.""";
        String[] words = literal.toLowerCase().split("\\W+");
        Arrays.sort(words);

        WordCount wordCount = new WordCount(literal);

        assertEquals(literal, wordCount.getString());

        Set<String> expected = new HashSet<>();

        int i = 0;
        while (i < words.length) {
            int currCount = 1;
            String currWord = words[i++];

            while (i < words.length && words[i].equals(currWord)) {
                currCount++;
                i++;
            }

            expected.add(currWord + ": " + currCount);
        }

        Set<String> result = wordCount.getSet();

        assertEquals(expected.size(), result.size());
        assertTrue(expected.containsAll(result));
    }
}
