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
    public void testWords() {
        final String str = "asd as abc assass";
        List<String> expected = new ArrayList<>();
        expected.add("asd");
        expected.add("as");
        expected.add("abc");
        expected.add("assass");

        List<String> words = StringUtil.words(str);

        assertEquals(expected, words);
    }

    @Test
    public void testGetInitials() {
        final String str = "asd as abc sass";
        List<Character> expected = new ArrayList<>();
        expected.add('a');
        expected.add('a');
        expected.add('a');
        expected.add('s');

        List<Character> initials = StringUtil.getInitials(str);

        assertEquals(expected, initials);
    }
}
