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
    public void truncate() {
        final String str = "abcdefghij";

        assertEquals(str, StringUtil.truncate(str, 20));
        assertEquals("abcdefghij", StringUtil.truncate(str, 10));
        assertEquals("abcdefg..", StringUtil.truncate(str, 9));
        assertEquals("abcde..", StringUtil.truncate(str, 7));
    }
}
