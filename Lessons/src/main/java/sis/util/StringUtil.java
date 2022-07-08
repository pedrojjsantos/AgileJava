package sis.util;

import java.util.List;

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

    public static String concatenate(List<?> list) {
        StringBuilder builder = new StringBuilder();
        for (Object element: list)
            builder.append("%s%n".formatted(element));
        return builder.toString();
    }

    public static String concatenateNumbers(List<? extends Number> list, int decimalPlaces) {
        String decimalFormat = "%." + decimalPlaces + "f";
        StringBuilder builder = new StringBuilder();
        for (Number element: list) {
            double value = element.doubleValue();
            builder.append(String.format(decimalFormat + "%n", value));
        }
        return builder.toString();
    }
}
