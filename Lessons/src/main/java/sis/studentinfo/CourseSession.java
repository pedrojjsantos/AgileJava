package sis.studentinfo;

import java.util.*;

/**
 * Provides a representation of a single-semester
 * session of a specific university course.
 * @author Administrator
 */
public class CourseSession extends Session {
    private static int count;

    public static CourseSession create(String department, String number, Date startDate) {
        return new CourseSession(department, number, startDate);
    }

    protected CourseSession(String department, String number, Date startDate) {
        super(department, number, startDate);
        incrementCount();
    }

    private static void incrementCount() {
        count++;
    }

    public static void resetCount() {
        count = 0;
    }

    public static int getCount() {
        return count;
    }

    @Override
    protected int getSessionLength() {
        return 16;
    }
}
