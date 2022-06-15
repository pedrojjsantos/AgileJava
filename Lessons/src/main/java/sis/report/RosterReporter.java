package sis.report;

import sis.studentinfo.Session;
import sis.studentinfo.Student;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

import static sis.report.ReportConstant.NEWLINE;

public class RosterReporter {
    public static final String REPORT_HEADER = "Student%n-%n";
    public static final String REPORT_FOOTER = "%n# students = %d%n";

    private final Session session;
    private Writer writer;

    public RosterReporter(Session session) {
        this.session = session;
    }

    public void writeReport(Writer writer) throws IOException {
        this.writer = writer;
        writeHeader();
        writeBody();
        writeFooter();
    }

    void writeReport(String filename) throws IOException {
        Writer bufferedWriter = new BufferedWriter(new FileWriter(filename));
        try {
            writeReport(bufferedWriter);
        }
        finally {
            bufferedWriter.close();
        }
    }

    private void writeHeader() throws IOException {
        writer.write(REPORT_HEADER.formatted());
    }
    private void writeBody() throws IOException {
        for (Student student: session.getAllStudents())
            writer.write(String.format("%s%n", student.getFullName()));
    }
    private void writeFooter() throws IOException {
        writer.write(REPORT_FOOTER.formatted(session.getNumberOfStudents()));
    }
}
