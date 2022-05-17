package util;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StringUtilTest {
    @Test
    public void testAppendNewLine() {
        final String str = "abcdefg";
        assertEquals(str + StringUtil.NEWLINE, StringUtil.appendNewLine(str));
    }
}
