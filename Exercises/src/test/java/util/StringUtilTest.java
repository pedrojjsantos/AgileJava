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
    public void testJoin2Chars() {
        final char c1 = 'a';
        final char c2 = 'b';

        assertEquals("ab", StringUtil.join2Chars(c1, c2));
        assertEquals("ba", StringUtil.join2Chars(c2, c1));

        assertEquals("ab", StringUtil.join2Chars((int) c1, (int) c2));
        assertEquals("ba", StringUtil.join2Chars((int) c2, (int) c1));
    }
}
