package sis.util;

public class StringUtil {
    static public int occurrences(String string, String substring) {
        int occurrences = 0;
        int length = substring.length();
        final boolean ignoreCase = true;
        for (int i = 0; i < string.length() - substring.length() + 1; i++)
            if (string.regionMatches(ignoreCase, i, substring, 0, length))
                occurrences++;
        return occurrences;
    }
}
