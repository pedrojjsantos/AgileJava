package etc;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.Assert.*;

public class NumTest {
    @Test   // Q1
    public void testBigDecimalImmutability() {
        BigDecimal x = new BigDecimal("42.00");
        BigDecimal y = new BigDecimal("13.00");

        BigDecimal xPlusY = x.add(y);
        assertNotEquals(x, xPlusY);
        assertNotEquals(y, xPlusY);
    }

    @Test   // Q2
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

            // Q11
        assertEquals(1, (int) 1.9);
            // Q12
        assertEquals(2, Math.rint(1.9), 0.05);
            // Q13
        assertEquals(2, Math.rint(1.5), 0.05);
        assertEquals(2, Math.rint(2.5), 0.05);


            // Q15
        assertEquals(34, 17 << 1);
            // Q16
        assertEquals(Character.MAX_VALUE - 1,(char) ~1);

        assertNotEquals(-128 >> 1, -128 >>> 1);
//        System.out.printf("%d != %d%n", -128 >> 1, -128 >>> 1);
    }

    @Test
    public void testRng() {
        ArrayList<Integer> listRand = new ArrayList<>();
        int min = 0;
        int max = 1;

        for (int i = 0; i < 100000; i++)
            listRand.add(rng(min, max));

        int maxOfList = listRand.stream().max(Integer::compareTo).orElse(-1);
        int minOfList = listRand.stream().min(Integer::compareTo).orElse(-1);

        assertTrue(maxOfList != -1 && minOfList != -1);
        assertTrue(max >= maxOfList);
        assertTrue(min <= minOfList);
    }

    private int rng(int min, int max) {
        return min + (int) Math.rint(Math.random() * max);
    }

    @Test
    public void testSwapper() {
        int[] list = new int[100];

        for (int i = 1; i <= 100; i++)
            list[i-1] = i;

        verifySwap(list);
        verifySwap(list);
        verifySwap(list);
        verifySwap(list);
        verifySwap(list);
        verifySwap(list);
    }

    private void swap(int[] list, int i, int j) {
        if (i == j) return;

        list[i] ^= list[j];
        list[j] ^= list[i];
        list[i] ^= list[j];
    }

    private void verifySwap(int[] list) {
        int i = rng(0, 99);
        int j = rng(0, 99);


        int xi = list[i];
        int xj = list[j];
//        System.out.printf("i:\t%d\t\tj:\t%d%nxi:\t%d\t\txj:\t%d%n", i, j, xi, xj);
        swap(list, i, j);

        assertEquals(100, list.length);

        assertEquals(xi , list[j]);
        assertEquals(xj , list[i]);
    }

    @Test
    public void testSwapWithoutTmp() {
        int x = 13;
        int y = 42;

        x ^= y;
        y ^= x;
        x ^= y;

        assertEquals(42, x);
        assertEquals(13, y);
    }

    @Test
    public void testRandomSeed() {
        Random rngWithNoSeed = new Random();
        Random rngWithSeed1 = new Random(1);

        assertNotEquals(rngWithNoSeed.nextDouble(), rngWithSeed1.nextDouble());
    }

    @Test
    public void testIntegralSize() {
        assertEquals(8,  getNumOfBits(Type.BYTE));
        assertEquals(16, getNumOfBits(Type.CHAR));
        assertEquals(16, getNumOfBits(Type.SHORT));
        assertEquals(32, getNumOfBits(Type.INT));
        assertEquals(64, getNumOfBits(Type.LONG));
    }
    enum Type {BYTE, CHAR, SHORT, INT, LONG};

    int getNumOfBits(Type type) {
        switch (type) {
            case BYTE -> {
                for (int i = 1; i < 128; i++) {
                    if ((byte) (1 << i) <= 1) return i+1;
                }
            }
            case CHAR -> {
                for (int i = 1; i < 128; i++) {
                    if ((char) (1 << i) <= 1) return i;
                }
            }
            case SHORT -> {
                for (int i = 1; i < 128; i++) {
                    if ((short) (1 << i) <= 1) return i+1;
                }
            }
            case INT -> {
                for (int i = 1; i < 128; i++) {
                    if ((int) (1 << i) <= 1) return i+1;
                }
            }
            case LONG -> {
                for (int i = 1; i < 128; i++) {
                    if ((1L << i) <= 1) return i+1;
                }
            }
        }

        return -1;
    }
}