package sis.util;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

public class StringUtilTest {
    private static final String TEXT = "this is it, isn't it";

    @Test
    public void testOccurrencesOne() {
        assertEquals(1, StringUtil.occurrences(TEXT, "his"));
    }

    @Test
    public void testOccurrencesNone() {
        assertEquals(0, StringUtil.occurrences(TEXT, "smelt"));
    }

    @Test
    public void testOccurrencesMany() {
        assertEquals(3, StringUtil.occurrences(TEXT, "is"));
        assertEquals(2, StringUtil.occurrences(TEXT, "it"));
    }

    @Test
    public void testOccurrencesSearchStringTooLarge() {
        assertEquals(0, StringUtil.occurrences(TEXT, TEXT + "sdfas"));
    }

    @Test
    public void testConcatenateList() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        String output = StringUtil.concatenate(list);
        assertEquals(String.format("a%nb%n"), output);
    }

    @Test
    public void testConcatenateFormattedDecimals() {
        List<BigDecimal> list = new ArrayList<>();
        list.add(new BigDecimal("3.1416"));
        list.add(new BigDecimal("-1.4142"));
        String output = StringUtil.concatenateNumbers(list, 3);
        assertEquals(String.format("3,142%n-1,414%n"), output);
    }

    @Test
    public void testConcatenateFormattedIntegers() {List<Integer> list = new ArrayList<>();
        list.add(12);
        list.add(17);
        String output = StringUtil.concatenateNumbers(list, 0);
        assertEquals(String.format("12%n17%n"), output);
    }

    @Test
    public void testWildcardCapture() {
        List<String> names = new ArrayList<>();
        names.add("alpha");
        names.add("beta");
        inPlaceReverse(names);
        assertEquals("beta", names.get(0));
        assertEquals("alpha", names.get(1));
    }

    static void inPlaceReverse(List<?> list) {
        int size = list.size();
        for (int i = 0; i < size / 2; i++)
            swap(list, i, size - 1 - i);
    }

    private static <T> void swap(List<T> list, int i, int j) {
        T temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);
    }
}
