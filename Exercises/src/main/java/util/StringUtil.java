package util;

public class StringUtil {
    public static final String NEWLINE = System.getProperty("line.separator");
    private StringUtil() {}

    public static String appendNewLine(String str) {
        return str + NEWLINE;
    }
    public static String join2Chars(int c1, int c2) {
        return "" + (char) c1 + (char) c2;
    }

    public static String truncate(String str, int i) {
        if (str.length() <= i)
            return str;
        String truncated = str.substring(0, i - 2);
        return truncated + "..";
    }
}
