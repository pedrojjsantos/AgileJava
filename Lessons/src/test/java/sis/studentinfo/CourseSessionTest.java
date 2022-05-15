package sis.studentinfo;

import org.junit.Before;
import org.junit.Test;
import sis.report.RosterReporter;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class CourseSessionTest {
    private CourseSession session;
    private Date startDate;

    @Before
    public void setUp() {
        startDate = new DateUtil().createDate(2003, 1, 6);
        session = new CourseSession("ENGL", "101", startDate);
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
        assertEquals(1, session.getNumberOfStudents());

        assertEquals(1, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));

        Student student2 = new Student("Coralee DeVaughn");
        session.enroll(student2);
        assertEquals(2, session.getNumberOfStudents());

        assertEquals(2, session.getNumberOfStudents());
        assertEquals(student1, session.get(0));
        assertEquals(student2, session.get(1));
    }

    @Test
    public void testCourseDates() {
        CourseSession session = new CourseSession("ABCD", "200", startDate);
        Date sixteenWeeksOut = new DateUtil().createDate(2003, 4, 25);
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
                CourseSession.ROSTER_REPORT_HEADER +
                        "A" + CourseSession.NEWLINE +
                        "B" + CourseSession.NEWLINE +
                        CourseSession.ROSTER_REPORT_FOOTER +
                        "2" + CourseSession.NEWLINE, rosterReport
        );
    }
}
