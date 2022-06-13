package sis.summer;

import org.junit.Test;
import sis.studentinfo.Course;
import sis.util.DateUtil;
import sis.studentinfo.Session;
import sis.studentinfo.SessionTest;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class SummerCourseSessionTest extends SessionTest {
    @Test
    public void testEndDate() {
        Date eightWeeksOut = DateUtil.createDate(2003, 2, 28);
        assertEquals(eightWeeksOut, session.getEndDate());
    }

    @Override
    protected Session createSession(Course course, Date date) {
        return SummerCourseSession.create(course, date);
    }
}
