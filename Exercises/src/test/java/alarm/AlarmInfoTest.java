package alarm;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AlarmInfoTest {
    @Test
    public void testCreate() {
        AlarmInfo alarm = new AlarmInfo("test", 300);

        assertEquals("test", alarm.getName());
        assertEquals(300, alarm.getTime());
        assertEquals("Ready!", alarm.getStatus());

        assertEquals("test(300ms) Ready!", alarm.toString());
    }

    @Test
    public void testUpdateStatus() {
        AlarmInfo alarm = new AlarmInfo("test", 300);
        assertEquals("Ready!", alarm.getStatus());

        alarm.finish();
        assertEquals("Finished!", alarm.getStatus());
    }
}
