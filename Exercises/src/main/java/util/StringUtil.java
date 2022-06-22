package util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class StringUtil {
    public static final String NEWLINE = System.getProperty("line.separator");
    private StringUtil() {}

    public static String appendNewLine(String str) {
        return str + NEWLINE;
    }
    public static String join2Chars(int c1, int c2) {
        return "" + (char) c1 + (char) c2;
    }

    public static List<String> words(String str) {
        return Arrays.stream(str.split(" ")).collect(Collectors.toList());
    }

    public static List<Character> getInitials(String str) {
        List<String> words = words(str);

        return words.stream().map(s -> s.charAt(0)).collect(Collectors.toList());
    }
}
