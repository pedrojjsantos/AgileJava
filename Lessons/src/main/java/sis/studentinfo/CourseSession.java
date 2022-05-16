package sis.studentinfo;

import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Provides a representation of a single-semester
 * session of a specific university course.
 * @author Administrator
 */
public class CourseSession {
    public static final String NEWLINE = System.getProperty("line.separator");
    public static final String ROSTER_REPORT_HEADER = "Student" + NEWLINE + '-' + NEWLINE;
    public static final String ROSTER_REPORT_FOOTER = NEWLINE + "# students = ";
    private static int count;
    private String department;
    private String number;
    private Date startDate;
    private ArrayList<Student> students = new ArrayList<>();

    public CourseSession(String department, String number, Date startDate) {
        this.department = department;
        this.number = number;
        this.startDate = startDate;
        CourseSession.incrementCount();
    }

    public static void resetCount() {
        count = 0;
    }

    private static void incrementCount() {
        count++;
    }

    public static int getCount() {
        return count;
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }

    public int getNumberOfStudents() {
        return students.size();
    }

    public void enroll(Student s) {
        students.add(s);
    }

    public Student get(int i) {
        return students.get(i);
    }

    public ArrayList<Student> getAllStudents() {
        return students;
    }

    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(startDate);

        // weeks * days per week - 3 days (Friday to Sunday)
        int numberOfDays = 16 * 7 - 3;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    public Date getStartDate() {
        return startDate;
    }
}
