package sis.studentinfo;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static sis.studentinfo.DateUtil.createDate;

abstract public class SessionTest {
    protected Session session;
    protected Date startDate;
    public static final int CREDITS = 3;

    @Before
    public void setUp() throws Exception {
        startDate = createDate(2003, 1, 6);
        session = createSession("ENGL", "101", startDate);
        session.setNumberOfCredits(CREDITS);
    }

    abstract protected Session createSession(String department, String number, Date startDate);

    @Test
    public void testCreate() {
        assertEquals("ENGL", session.getDepartment());
        assertEquals("101", session.getNumber());
        assertEquals(0, session.getNumberOfStudents());
        assertEquals(startDate, session.getStartDate());
    }

    @Test
    public void testEnrollStudents() {
        Student student1 = new Student("Cain DiVoe");
        session.enroll(student1);
        assertEquals(CREDITS, student1.getCredits());
        assertEquals(1, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));
        Student student2 = new Student("Coralee DeVaughn");
        session.enroll(student2);
        assertEquals(CREDITS, student2.getCredits());
        assertEquals(2, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));
        assertEquals(student2, session.get(1));
    }

    @Test
    public void testComparable() {
        final Date date = new Date();
        Session sessionA = createSession("CMSC", "101", date);
        Session sessionB = createSession("ENGL", "101", date);
        assertTrue(sessionA.compareTo(sessionB) < 0);
        assertTrue(sessionB.compareTo(sessionA) > 0);
        Session sessionC = createSession("CMSC", "101", date);
        assertEquals(0, sessionA.compareTo(sessionC));
        Session sessionD = createSession("CMSC", "210", date);
        assertTrue(sessionC.compareTo(sessionD) < 0);
        assertTrue(sessionD.compareTo(sessionC) > 0);
    }
}
