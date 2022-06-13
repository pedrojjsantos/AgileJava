package sis.studentinfo;

import org.junit.Test;
import sis.report.RosterReporter;
import sis.util.DateUtil;

import static org.junit.Assert.assertTrue;
import static sis.report.ReportConstant.NEWLINE;

import java.util.Date;

import static org.junit.Assert.assertEquals;


public class CourseSessionTest extends SessionTest {
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
        createSession(createCourse(), startDate);
        assertEquals(1, CourseSession.getCount());
        createSession(createCourse(), startDate);
        assertEquals(2, CourseSession.getCount());
    }

    @Override
    protected Session createSession(Course course, Date startDate) {
        return CourseSession.create(course, startDate);
    }

    private Course createCourse() {
        return new Course("ENGL", "101");
    }
}
