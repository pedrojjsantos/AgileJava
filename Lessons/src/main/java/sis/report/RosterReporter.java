package sis.report;

import sis.studentinfo.CourseSession;
import sis.studentinfo.Student;
import static sis.report.ReportConstant.NEWLINE;

public class RosterReporter {
    public static final String ROSTER_REPORT_HEADER = "Student" + NEWLINE + '-' + NEWLINE;
    public static final String ROSTER_REPORT_FOOTER = NEWLINE + "# students = ";

    private CourseSession session;

    public RosterReporter(CourseSession session) {
        this.session = session;
    }

    public String getReport() {
        StringBuilder buffer = new StringBuilder();

        writeHeader(buffer);
        writeBody(buffer);
        writeFooter(buffer);

        return buffer.toString();
    }

    private void writeHeader(StringBuilder buffer) {
        buffer.append(ROSTER_REPORT_HEADER);
    }
    private void writeBody(StringBuilder buffer) {
        for (Student student: session.getAllStudents())
            buffer.append(student.getName())
                    .append(NEWLINE);
    }
    private void writeFooter(StringBuilder buffer) {
        buffer.append(ROSTER_REPORT_FOOTER)
                .append(session.getNumberOfStudents())
                .append(NEWLINE);
    }
}
