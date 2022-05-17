package sis.studentinfo;

import org.junit.Before;
import org.junit.Test;
import sis.report.RosterReporter;

import static org.junit.Assert.assertTrue;
import static sis.report.ReportConstant.NEWLINE;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class CourseSessionTest {
    private static final int CREDITS = 3;
    private CourseSession session;
    private Date startDate;

    @Before
    public void setUp() {
        startDate = DateUtil.createDate(2003, 1, 6);
        session = createCourseSession();
    }

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
    public void testCourseDates() {
        Date sixteenWeeksOut = DateUtil.createDate(2003, 4, 25);
        assertEquals(sixteenWeeksOut, session.getEndDate());
    }

    @Test
    public void testRosterReport() {
        Student studentA = new Student("A");
        Student studentB = new Student("B");
        session.enroll(studentA);
        session.enroll(studentB);

        String rosterReport = new RosterReporter(session).getReport();
        assertEquals(
                RosterReporter.ROSTER_REPORT_HEADER +
                        "A" + NEWLINE + "B" + NEWLINE +
                        RosterReporter.ROSTER_REPORT_FOOTER +
                        "2" + NEWLINE,
                rosterReport
        );
    }

    @Test
    public void testCount() {
        CourseSession.resetCount();
        createCourseSession();
        assertEquals(1, CourseSession.getCount());
        createCourseSession();
        assertEquals(2, CourseSession.getCount());
    }

    @Test
    public void testComparable() {
        final Date date = new Date();
        CourseSession sessionA =
                CourseSession.create("CMSC", "101", date);
        CourseSession sessionB =
                CourseSession.create("ENGL", "101", date);
        CourseSession sessionC =
                CourseSession.create("CMSC", "101", date);
        CourseSession sessionD =
                CourseSession.create("CMSC", "210", date);

        assertTrue(sessionA.compareTo(sessionB) < 0);
        assertTrue(sessionB.compareTo(sessionA) > 0);

        assertEquals(0, sessionA.compareTo(sessionC));

        assertTrue(sessionC.compareTo(sessionD) < 0);
        assertTrue(sessionD.compareTo(sessionC) > 0);
    }

    private CourseSession createCourseSession() {
        CourseSession session =
                CourseSession.create("ENGL", "101", startDate);
        session.setNumberOfCredits(CourseSessionTest.CREDITS);
        return session;
    }
}
