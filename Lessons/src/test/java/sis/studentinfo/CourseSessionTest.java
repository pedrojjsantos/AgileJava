package sis.studentinfo;

import org.junit.Before;
import org.junit.Test;
import sis.report.RosterReporter;

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
        createSession("a", "1", startDate);
        assertEquals(1, CourseSession.getCount());
        createSession("a", "1", startDate);
        assertEquals(2, CourseSession.getCount());
    }

    @Override
    protected Session createSession(String department, String number, Date startDate) {
        return CourseSession.create(department, number, startDate);
    }
}
