package sis.util;

import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class MultiHashMapTest {
    private static final Date today = new Date();
    private static final Date tomorrow = new Date(today.getTime() + 86400000);
    private static final String eventA = "wake up";
    private static final String eventB = "eat";
    private MultiHashMap<Date,String> events;

    @Before
    public void setUp() {
        events = new MultiHashMap<>();
    }

    @Test
    public void testCreate() {
        assertEquals(0, events.size());
    }

    @Test
    public void testSingleEntry() {
        events.put(today, eventA);
        assertEquals(1, events.size());
        assertEquals(eventA, getSoleEvent(today));
    }

    private String getSoleEvent(Date date) {
        Collection<String> retrievedEvents = events.get(date);
        assertEquals(1, retrievedEvents.size());
        Iterator<String> it = retrievedEvents.iterator();
        return it.next();
    }

    @Test
    public void testMultipleEntriesDifferentKey() {
        events.put(today, eventA);
        events.put(tomorrow, eventB);
        assertEquals(2, events.size());
        assertEquals(eventA, getSoleEvent(today));
        assertEquals(eventB, getSoleEvent(tomorrow));
    }

    @Test
    public void testMultipleEntriesSameKey() {
        events.put(today, eventA);
        events.put(today, eventB);
        assertEquals(1, events.size());
        Collection<String> retrievedEvents = events.get(today);
        assertEquals(2, retrievedEvents.size());
        assertTrue(retrievedEvents.contains(eventA));
        assertTrue(retrievedEvents.contains(eventB));
    }

    @Test
    public void testFilter() {
        MultiHashMap<String,java.sql.Date> meetings= new MultiHashMap<>();

        meetings.put("iteration start", createSqlDate(2005, 9, 12));
        meetings.put("iteration start", createSqlDate(2005, 9, 26));
        meetings.put("VP blather", createSqlDate(2005, 9, 12));
        meetings.put("brown bags", createSqlDate(2005, 9, 14));

        MultiHashMap<String,java.util.Date> mondayMeetings = new MultiHashMap<>();
        MultiHashMap.filter(mondayMeetings, meetings, (MultiHashMap.Filter<Date>) this::isMonday);
        assertEquals(2, mondayMeetings.size());
        assertEquals(2, mondayMeetings.get("iteration start").size());
        assertNull(mondayMeetings.get("brown bags"));
        assertEquals(1, mondayMeetings.get("VP blather").size());
    }

    private boolean isMonday(Date date) {
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        return calendar.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
    }

    private java.sql.Date createSqlDate(int year, int month, int day) {
        java.util.Date date = DateUtil.createDate(year, month, day);
        return new java.sql.Date(date.getTime());
    }
}