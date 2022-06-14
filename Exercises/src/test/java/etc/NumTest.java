package etc;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class NumTest {
    @Test
    public void testBigDecimalImmutability() {
        BigDecimal x = new BigDecimal("42.00");
        BigDecimal y = new BigDecimal("13.00");

        BigDecimal xPlusY = x.add(y);
        assertNotEquals(x, xPlusY);
        assertNotEquals(y, xPlusY);
    }

    @Test
    public void testScaleAndMultiplication() {
        BigDecimal x = new BigDecimal("10.00");
        BigDecimal y = new BigDecimal("1");
        assertNotEquals(x, y);

        BigDecimal equalsX = y.multiply(x);
        assertEquals(x, equalsX);

        BigDecimal equalsY = x.divideToIntegralValue(x);
        assertEquals(y, equalsY);
    }

    @Test
    public void testQ3() {
        double x = 0.9;
        double y = 0.005;
        double z = 2.0;

        assertNotEquals(x, y * z);

        assertEquals(x, y * z, 0.89);
    }

    // Q4
    public class CompilerError {
//        float  x = 0.01;
        float  x1 = 0.01f;
        // or
        double x2 = 0.01;
    }

    @Test
    public void testQ5() {
        Integer x = Integer.decode("0xDEAD");
        assertEquals(57005, x.intValue());
    }

    @Test
    public void testQ6() {
        Float x; Double y;
        x = Float.MAX_VALUE * Float.MAX_VALUE;
        y = Double.MAX_VALUE * Double.MAX_VALUE;
        assertTrue(x.isInfinite());
        assertTrue(y.isInfinite());

        x = 1.0f / 0;
        y = 1.0d / 0;
        assertTrue(x.isInfinite());
        assertTrue(y.isInfinite());

        x = -1.0f / 0;
        y = -1.0d / 0;
        assertTrue(x.isInfinite());
        assertTrue(y.isInfinite());

        x = 0f / 0;
        y = 0d / 0;
        assertTrue(x.isNaN());
        assertTrue(y.isNaN());
    }

    @Test // Q8
    public void testDivisibleBy3() {
        Integer[] numbers = {1,2,3,4,5,6,7,8,9,10};
        List<Integer> expected  = new ArrayList<>();
        expected.add(3);
        expected.add(6);
        expected.add(9);

        assertEquals(expected, isDivisibleBy3Mod(numbers));
        assertEquals(expected, isDivisibleBy3Div(numbers));
        assertEquals(expected, isDivisibleBy3ModF(numbers));
    }

    private List<Integer> isDivisibleBy3Mod(Integer ... numbers) {
        List<Integer> list = new ArrayList<>();

        for (int i : numbers) {
            if (i % 3 == 0)
                list.add(i);
        }

        return list;
    }
    private List<Integer> isDivisibleBy3Div(Integer ... numbers) {
        List<Integer> list = new ArrayList<>();

        for (int i : numbers) {
            if ((i / 3) * 3 == i)
                list.add(i);
        }

        return list;
    }
    private List<Integer> isDivisibleBy3ModF(Integer ... numbers) {
        return Arrays.stream(numbers).filter(i -> i % 3 == 0 ).toList();
    }

    @Test
    public void testQ10to13() {
            // Q10
        { float x = 1; }
//        float x = 1.0;
        { float x = (int) 1.0; }

        assertEquals(1, (int) 1.9);     // Q11
        assertEquals(2, Math.rint(1.9), 0.05);  // Q12
            // Q13
        assertEquals(2, Math.rint(1.5), 0.05);
        assertEquals(2, Math.rint(2.5), 0.05);


        assertEquals(34, 17 << 1);      // Q15
        assertEquals(Character.MAX_VALUE - 1,(char) ~1);    // Q16

        assertNotEquals(-128 >> 1, -128 >>> 1);
        System.out.printf("%d != %d%n", -128 >> 1, -128 >>> 1);
    }

    @Test
    public void testRng() {
        ArrayList<Integer> listRNG = new ArrayList<>();
        for (int i = 0; i < 10000; i++)
            listRNG.add(rng());
        int max = listRNG.stream().max(Integer::compareTo).orElse(-1);
        int min = listRNG.stream().min(Integer::compareTo).orElse(-1);

        assertTrue(max != -1 && min != -1);
        assertTrue(max <= 50);
        assertTrue(min >= 0);
    }

    private int rng() {
        return (int) Math.rint(Math.random() * 50);
    }
}

// 0001
// 1111