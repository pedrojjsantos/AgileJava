package sis.report;

import sis.studentinfo.CourseSession;
import sis.studentinfo.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static sis.report.ReportConstant.NEWLINE;

public class CourseReport {
    private List<Session> sessions = new ArrayList<>();

    public void add(Session session) {
        sessions.add(session);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder buffer = new StringBuilder();
        for (Session s : sessions) {
            buffer.append(s.getDepartment())
                    .append(" ")
                    .append(s.getNumber())
                    .append(NEWLINE);
        }
        return buffer.toString();
    }
}
