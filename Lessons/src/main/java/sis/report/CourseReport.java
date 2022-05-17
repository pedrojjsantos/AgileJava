package sis.report;

import sis.studentinfo.CourseSession;

import java.util.ArrayList;
import java.util.Collections;

import static sis.report.ReportConstant.NEWLINE;

public class CourseReport {
    private ArrayList<CourseSession> sessions = new ArrayList<>();

    public void add(CourseSession session) {
        sessions.add(session);
    }

    public String text() {
        Collections.sort(sessions);
        StringBuilder buffer = new StringBuilder();
        for (CourseSession s : sessions) {
            buffer.append(s.getDepartment())
                    .append(" ")
                    .append(s.getNumber())
                    .append(NEWLINE);
        }
        return buffer.toString();
    }
}
