package chess;

import org.junit.Test;

import java.util.Arrays;
import java.util.Enumeration;
import java.util.Vector;

import static org.junit.Assert.*;

public class LoopTest {
    @Test
    public void testFactorial() {
        verifyFactorialWhile();
        verifyFactorialFor();
        verifyFactorialDoWhile();
        verifyFactorialWhileTrue();
    }

    private int factorialWhile(int n) {
        int count = n;
        int result = 1;

        while (count > 0)
            result *= count--;
        return result;
    }
    private int factorialFor(int n) {
        int count = n;
        int result;

        for (result = 1; count > 0; count--)
            result *= count;
        return result;
    }
    private int factorialDoWhile(int n) {
        int count = (n > 0) ? n : 1;
        int result = 1;

        do result *= count;
        while (--count > 0);
        return result;
    }
    private  int factorialWhileTrue(int n) {
        int count = n;
        int result = 1;

        while (true) {
            if (count <= 0) break;
            result *= count--;
        }
        return result;
    }


    private void verifyFactorialWhile() {
        assertEquals(1, factorialWhile(0));
        assertEquals(1, factorialWhile(1));
        assertEquals(6, factorialWhile(3));
        assertEquals(24, factorialWhile(4));
        assertEquals(120, factorialWhile(5));
        assertEquals(720, factorialWhile(6));
    }
    private void verifyFactorialFor() {
        assertEquals(1, factorialFor(0));
        assertEquals(1, factorialFor(1));
        assertEquals(6, factorialFor(3));
        assertEquals(24, factorialFor(4));
        assertEquals(120, factorialFor(5));
        assertEquals(720, factorialFor(6));
    }
    private void verifyFactorialDoWhile() {
        assertEquals(1, factorialDoWhile(0));
        assertEquals(1, factorialDoWhile(1));
        assertEquals(6, factorialDoWhile(3));
        assertEquals(24, factorialDoWhile(4));
        assertEquals(120, factorialDoWhile(5));
        assertEquals(720, factorialDoWhile(6));
    }
    private void verifyFactorialWhileTrue() {
        assertEquals(1, factorialWhileTrue(0));
        assertEquals(1, factorialWhileTrue(1));
        assertEquals(6, factorialWhileTrue(3));
        assertEquals(24, factorialWhileTrue(4));
        assertEquals(120, factorialWhileTrue(5));
        assertEquals(720, factorialWhileTrue(6));
    }

    @Test
    public void testSequence() {
        assertEquals("1 2 3 4 5* 6 7 8 9 10* 11 12", sequence(12));
        assertEquals("1 2 3 4 5* 6 7 8 9 10* 11 12 13 14 15*", sequence(15));
    }

    private String sequence(int n) {
        StringBuilder buffer = new StringBuilder("1");

        for (int i = 2; i <= n; i++) {
            buffer.append(' ').append(i);
            if (i % 5 != 0) continue;
            buffer.append('*');
        }

        return buffer.toString();
    }

    @Test
    public void testSplit() {
        String sequence = sequence(7);
        Vector splitSequence = new Vector(Arrays.asList(sequence.split(" ")));
        Vector expected =
                new Vector(Arrays.asList("1", "2", "3", "4", "5*", "6", "7"));

        assertEquals(expected, splitSequence);
    }

    @Test
    public void testJoin() {
        String sequence = sequence(7);
        Vector splitSequence = new Vector(Arrays.asList(sequence.split(" ")));
        String joinedSequence = join(splitSequence);

        assertEquals(sequence, joinedSequence);
    }

    private String join(Vector splitSequence) {
        StringBuilder buffer = new StringBuilder();

        for (Enumeration it = splitSequence.elements();
             it.hasMoreElements(); ) {
            String str = (String) it.nextElement();
            buffer.append(str).append(' ');
        }

        buffer.setLength(buffer.length() - 1);
        return buffer.toString();
    }
}
