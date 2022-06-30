package util;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class StringUtilTest {
    @Test
    public void testAppendNewLine() {
        final String str = "abcdefg";
        assertEquals(str + StringUtil.NEWLINE, StringUtil.appendNewLine(str));
    }

    @Test
    public void testJoin2Chars() {
        final char c1 = 'a';
        final char c2 = 'b';

        assertEquals("ab", StringUtil.join2Chars(c1, c2));
        assertEquals("ba", StringUtil.join2Chars(c2, c1));

        assertEquals("ab", StringUtil.join2Chars((int) c1, (int) c2));
        assertEquals("ba", StringUtil.join2Chars((int) c2, (int) c1));
    }

    @Test
    public void truncate() {
        final String str = "abcdefghij";

        assertEquals(str, StringUtil.truncate(str, 20));
        assertEquals("abcdefghij", StringUtil.truncate(str, 10));
        assertEquals("abcdefg..", StringUtil.truncate(str, 9));
        assertEquals("abcde..", StringUtil.truncate(str, 7));
    }
}
