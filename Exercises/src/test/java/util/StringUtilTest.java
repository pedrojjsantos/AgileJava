package util;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {
    @Test
    public void testAppendNewLine() {
        final String str = "abcdefg";
        assertEquals(str + StringUtil.NEWLINE, StringUtil.appendNewLine(str));
    }

    @Test
    public void truncate() {
        final String str = "abcdefghij";

        assertEquals(str, StringUtil.truncate(str, 20));
        assertEquals("abcdefghij", StringUtil.truncate(str, 10));
        assertEquals("abcdefg..", StringUtil.truncate(str, 9));
        assertEquals("abcde..", StringUtil.truncate(str, 7));
    }

    @Test
    public void testSplitAndGetLast() {
        String str = "This is a simple test";

        assertEquals("test", StringUtil.splitAndGetLast(" ", str));
        assertEquals(str, StringUtil.splitAndGetLast("z", str));
        assertEquals("le test", StringUtil.splitAndGetLast("p", str));
        assertEquals("st", StringUtil.splitAndGetLast("[ e]", str));
        assertEquals("" , StringUtil.splitAndGetLast("1", ""));
    }
}
