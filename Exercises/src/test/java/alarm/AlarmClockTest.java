package alarm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AlarmClockTest {
    private AlarmClock alarmClock;
    private final Object monitor = new Object();
    List<String> alarmsRung = Collections.synchronizedList(new ArrayList<>());

    @Before
    public void setUp() {
        AlarmListener listener = alarmInfo -> {
            alarmsRung.add(alarmInfo.toString());
            synchronized (monitor){
                monitor.notifyAll();
            }
        };
        alarmClock = new AlarmClock(listener);
    }

    @After
    public void tearDown() throws Exception {
        if (alarmClock.isAlive()) {
            alarmClock.shutDown();
            alarmClock.join(3000);
        }
        assertFalse(alarmClock.isAlive());
    }

    @Test
    public void testAlarm() throws Exception {
        AlarmInfo alarm = new AlarmInfo("Test", 200);
        long start = System.currentTimeMillis();
        alarmClock.add(alarm);
        synchronized (monitor) {
            monitor.wait(300);
        }
        long end = System.currentTimeMillis();
        assertTrue(alarm.getTime()<= end - start);
        assertEquals(1, alarmsRung.size());
        assertEquals(alarm.toString(), alarmsRung.get(0));
    }

    @Test
    public void testMultipleAlarms() throws Exception{
        AlarmInfo alarm1 = new AlarmInfo("Alarm:1", 300);
        AlarmInfo alarm2 = new AlarmInfo("Alarm:2", 150);
        long start, endAlarm1, endAlarm2;
        start = System.currentTimeMillis();

        alarmClock.add(alarm1);
        alarmClock.add(alarm2);

        synchronized (monitor) {
            monitor.wait(200);
        }
        endAlarm2 = System.currentTimeMillis();
        assertEquals(1, alarmsRung.size());

        synchronized (monitor) {
            monitor.wait(200);
        }
        endAlarm1 = System.currentTimeMillis();
        assertEquals(2, alarmsRung.size());

        assertTrue(alarm1.getTime() <= endAlarm1 - start);
        assertTrue(alarm2.getTime() <= endAlarm2 - start);

        assertEquals(alarm2.toString(), alarmsRung.get(0));
        assertEquals(alarm1.toString(), alarmsRung.get(1));

    }

    @Test
    public void testCancelAlarm() throws Exception {
        AlarmInfo alarm1 = new AlarmInfo("Alarm:1", 300);
        AlarmInfo alarm2 = new AlarmInfo("Alarm:2", 150);

        alarmClock.add(alarm1);
        alarmClock.add(alarm2);
        Thread.sleep(alarm2.getTime() - 50);
        alarmClock.cancel(alarm2.getName());

        Thread.sleep(60);
        assertEquals(0, alarmsRung.size());

        Thread.sleep(alarm1.getTime() - alarm2.getTime());
        assertEquals(1, alarmsRung.size());
        assertEquals(alarm1.toString(), alarmsRung.get(0));
    }
}
