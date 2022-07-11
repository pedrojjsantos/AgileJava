package alarm;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class AlarmClockTest {
    private final String ALARM_FMT = "%s(%dms)";
    private AlarmClock alarmClock;
    private final Object monitor = new Object();
    List<String> alarmsRung = Collections.synchronizedList(new ArrayList<>());

    @Before
    public void setUp() {
        AlarmListener listener = alarmInfo -> {
            alarmsRung.add(alarmInfo);
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
        String name = "Test";
        long time = 200;
        long start = System.currentTimeMillis();
        alarmClock.add(name, time);
        synchronized (monitor) {
            monitor.wait(300);
        }
        long end = System.currentTimeMillis();
        assertTrue(time <= end - start);
        assertEquals(1, alarmsRung.size());
        assertEquals(ALARM_FMT.formatted(name, time), alarmsRung.get(0));
    }

    @Test
    public void testMultipleAlarms() throws Exception{
        String alarm1Name = "Alarm:1";
        long alarm1Time = 300;
        String alarm2Name = "Alarm:2";
        long alarm2Time = 150;
        long start, endAlarm1, endAlarm2;
        start = System.currentTimeMillis();

        alarmClock.add(alarm1Name, alarm1Time);
        alarmClock.add(alarm2Name, alarm2Time);

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

        assertTrue(alarm1Time <= endAlarm1 - start);
        assertTrue(alarm2Time <= endAlarm2 - start);

        assertEquals(ALARM_FMT.formatted(alarm2Name, alarm2Time), alarmsRung.get(0));
        assertEquals(ALARM_FMT.formatted(alarm1Name, alarm1Time), alarmsRung.get(1));

    }

    @Test
    public void testCancelAlarm() throws Exception {
        String alarm1Name = "Alarm:1";
        long alarm1Time = 300;
        String alarm2Name = "Alarm:2";
        long alarm2Time = 150;

        alarmClock.add(alarm1Name, alarm1Time);
        alarmClock.add(alarm2Name, alarm2Time);
        Thread.sleep(alarm2Time - 50);
        alarmClock.cancel(alarm2Name);

        Thread.sleep(60);
        assertEquals(0, alarmsRung.size());

        Thread.sleep(alarm1Time - alarm2Time);
        assertEquals(1, alarmsRung.size());
        assertEquals(ALARM_FMT.formatted(alarm1Name, alarm1Time), alarmsRung.get(0));
    }
}
