package sis.report;

import org.junit.Test;
import sis.studentinfo.Course;
import sis.studentinfo.CourseSession;
import sis.studentinfo.Session;

import java.util.Date;

import static org.junit.Assert.assertEquals;
import static sis.report.ReportConstant.NEWLINE;


public class CourseReportTest {
    @Test
    public void testReport() {
        final Date date = new Date();
        CourseReport report = new CourseReport();
        report.add(create("ENGL", "101", date));
        report.add(create("CZEC", "200", date));
        report.add(create("ITAL", "410", date));
        report.add(create("CZEC", "220", date));
        report.add(create("ITAL", "330", date));

        assertEquals(
                "CZEC 200" + NEWLINE +
                        "CZEC 220" + NEWLINE +
                        "ENGL 101" + NEWLINE +
                        "ITAL 330" + NEWLINE +
                        "ITAL 410" + NEWLINE,
                report.text()
        );
    }

    private Session create(String department, String number, Date date) {
        return CourseSession.create(new Course(department, number), date);
    }
}
