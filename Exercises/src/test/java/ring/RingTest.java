package ring;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class RingTest {
    private Ring<Integer> ring;

    @Before
    public void setUp() {
        ring = new Ring<>();
    }

    @Test
    public void testCreate() {
        assertEquals(0, ring.size());
        assertTrue(ring.isEmpty());
        assertNull(ring.get());
    }

    @Test
    public void testAdd() {
        ring.add(1);
        assertEquals(1, ring.size());
        assertEquals(1, ring.get().intValue());

        ring.add(2);
        assertEquals(2, ring.size());
        assertEquals(2, ring.get().intValue());
    }

    @Test
    public void testRemove() {
        ring.add(1);
        ring.add(2);
        ring.add(3);

        assertEquals(3, ring.get().intValue());
        assertEquals(3, ring.size());
        ring.remove();
        assertEquals(2, ring.get().intValue());
        assertEquals(2, ring.size());
        ring.remove();
        assertEquals(1, ring.get().intValue());
        assertEquals(1, ring.size());
        ring.remove();
        assertTrue(ring.isEmpty());
        assertNull(ring.get());

    }

    @Test
    public void testNextAndPrev() {
        ring.add(1);
        ring.add(2);
        ring.add(3);
        ring.add(4);
        ring.add(5);

        assertEquals(5, ring.get().intValue());
        assertEquals(5, ring.size());

        for (int i = 1; i <= 5; i++) {
            ring.next();
            assertEquals(i, ring.get().intValue());
        }

        for (int i = 5; i >= 1; i--) {
            assertEquals(i, ring.get().intValue());
            ring.prev();
        }
    }

    @Test
    public void testIterate() {
        ring.add(1);
        ring.add(2);
        ring.add(3);
        ring.add(4);
        ring.add(5);
        ring.add(6);
        ring.next();

        Iterator<Integer> it = ring.iterator();

        for (int i = 1; i < ring.size(); i++) {
            assertEquals(i, it.next().intValue());
        }

        ring.next();
        List<Integer> expected = List.of(new Integer[]{1, 2, 3, 4, 5, 6});
        List<Integer> result = new ArrayList<>();

        for (Integer i : ring)
            result.add(i);

        assertEquals(expected, result);
    }

    @Test
    public void testEmptyExceptions() {
        assertThrows("Expected exception for trying to use an empty ring",
                EmptyRingException.class, () -> ring.remove());

        assertThrows("Expected exception for trying to use an empty ring",
                EmptyRingException.class, () -> ring.next());

        assertThrows("Expected exception for trying to use an empty ring",
                EmptyRingException.class, () -> ring.prev());
    }

    @Test
    public void testAddNull() {
        assertThrows("Expected AssertionError for trying to add a null to the ring",
                AssertionError.class, () -> ring.add(null));
    }
}
