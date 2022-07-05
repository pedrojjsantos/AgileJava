package sis.clock;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.locks.*;

import static org.junit.Assert.*;

public class ClockTest {
    private Clock clock;
    private Object monitor = new Object();
    private final long PRECISION = 2;

    private Lock lock;
    private Condition receivedEnoughTics;

    @Before
    public void setUp() {
        lock = new ReentrantLock();
        receivedEnoughTics = lock.newCondition();
    }

    @Test
    public void testClock() throws Exception {
        final int maxTics = 3;
        final long clockInterval = 100; //millis
        final List<Date> tics = new ArrayList<>();

        ClockListener listener = createClockListener(tics, maxTics);
        clock = new Clock(listener, clockInterval);

        lock.lock();
        try {
            receivedEnoughTics.await();
        }
        finally {
            lock.unlock();
        }

        clock.stop();
        verify(tics, maxTics, clockInterval);
    }

    private void verify(List<Date> tics, int maxTics, long clockInterval) {
        assertEquals(maxTics, tics.size());
        for (int i = 1; i < maxTics; i++)
            assertTrue(clockInterval + PRECISION >= getDiffFromLast(tics, i) &&
                    clockInterval - PRECISION <= getDiffFromLast(tics, i));

    }

    private long getDiffFromLast(List<Date> tics, int i) {
        return tics.get(i).getTime() - tics.get(i-1).getTime();
    }

    private ClockListener createClockListener(final List<Date> tics, final int maxTics) {
        return new ClockListener() {
            private int count = 0;
            public void update(Date date) {
                tics.add(date);
                if (++count == maxTics) {
                    lock.lock();
                    try {
                        receivedEnoughTics.signalAll();
                    }
                    finally {
                        lock.unlock();
                    }
                }
            }
        };
    }
}
