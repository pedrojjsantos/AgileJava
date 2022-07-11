package ring;

import org.junit.Before;
import org.junit.Test;

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
    }

    @Test
    public void testAdd() {
        ring.add(1);
        assertEquals(1, ring.size());
        assertEquals(1, ring.current().intValue());

        ring.add(2);
        assertEquals(2, ring.size());
        assertEquals(1, ring.current().intValue());
    }

    @Test
    public void testRemove() {
        ring.add(3);
        ring.add(2);
        ring.add(1);

        assertEquals(3, ring.current().intValue());
        assertEquals(3, ring.size());
        ring.remove();
        assertEquals(2, ring.current().intValue());
        assertEquals(2, ring.size());
        ring.remove();
        assertEquals(1, ring.current().intValue());
        assertEquals(1, ring.size());
        ring.remove();
        assertNull(ring.current());
        assertEquals(0, ring.size());

    }

    @Test
    public void testNextAndPrev() {
        ring.add(1);
        ring.add(5);
        ring.add(4);
        ring.add(3);
        ring.add(2);

        assertEquals(5, ring.size());

        for (int i = 1; i <= 5; i++) {
            assertEquals(i, ring.current().intValue());
            ring.next();
        }

        for (int i = 5; i >= 1; i--) {
            ring.prev();
            assertEquals(i, ring.current().intValue());
        }
    }
}
