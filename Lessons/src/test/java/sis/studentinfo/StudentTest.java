package sis.studentinfo;

import org.junit.Before;
import org.junit.Test;

import java.util.logging.Handler;
import java.util.logging.Logger;

import static org.junit.Assert.*;

public class StudentTest {
    private Student student;
    @Before
    public void setUp() throws Exception {
        student = new Student("a");
    }

    @Test
    public void testCreate() {
        final String firstStudentName = "Jane Doe";
        Student firstStudent = new Student(firstStudentName);
        assertEquals(firstStudentName, firstStudent.getFullName());
        assertEquals("Jane", firstStudent.getFirstName());
        assertEquals("Doe", firstStudent.getLastName());
        assertEquals("", firstStudent.getMiddleName());

        final String secondStudentName = "Joe Blow";
        Student secondStudent = new Student(secondStudentName);
        assertEquals(secondStudentName, secondStudent.getFullName());
        assertEquals("Joe", secondStudent.getFirstName());
        assertEquals("Blow", secondStudent.getLastName());
        assertEquals("", secondStudent.getMiddleName());

        final String thirdStudentName = "Raymond Douglas Davies";
        Student thirdStudent = new Student(thirdStudentName);
        assertEquals(thirdStudentName, thirdStudent.getFullName());
        assertEquals("Raymond", thirdStudent.getFirstName());
        assertEquals("Davies", thirdStudent.getLastName());
        assertEquals("Douglas", thirdStudent.getMiddleName());
    }

    @Test
    public void testStudentStatus() {
        assertEquals(0, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(3);
        assertEquals(3, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(4);
        assertEquals(7, student.getCredits());
        assertFalse(student.isFullTime());

        student.addCredits(5);
        assertEquals(Student.CREDITS_REQUIRED_FOR_FULL_TIME, student.getCredits());
        assertTrue(student.isFullTime());
    }

    @Test
    public void testInState() {
        assertFalse(student.isInState());
        student.setState(Student.IN_STATE);
        assertTrue(student.isInState());
        student.setState("MD");
        assertFalse(student.isInState());
    }

    private static final double GRADE_TOLERANCE = 0.05;

    @Test
    public void testCalculateGpa() {
        assertGpa(0.0, student);
        student.addGrade(Student.Grade.A);
        assertGpa(4.0, student);
        student.addGrade(Student.Grade.B);
        assertGpa(3.5, student);
        student.addGrade(Student.Grade.C);
        assertGpa(3.0, student);
        student.addGrade(Student.Grade.D);
        assertGpa(2.5, student);
        student.addGrade(Student.Grade.F);
        assertGpa(2.0, student);
    }

    @Test
    public void testCalculateHonorsStudentGpa() {
        assertGpa(0.0, createHonorsStudent());
        assertGpa(5.0, createHonorsStudent(Student.Grade.A));
        assertGpa(4.0, createHonorsStudent(Student.Grade.B));
        assertGpa(3.0, createHonorsStudent(Student.Grade.C));
        assertGpa(2.0, createHonorsStudent(Student.Grade.D));
        assertGpa(0.0, createHonorsStudent(Student.Grade.F));
    }

    private Student createHonorsStudent(Student.Grade grade) {
        Student student = createHonorsStudent();
        student.addGrade(grade);
        return student;
    }

    private Student createHonorsStudent() {
        Student student = new Student("a");
        student.setGradingStrategy(new HonorsGradingStrategy());
        return student;
    }

    private void assertGpa(double expected, Student student) {
        assertEquals(expected, student.getGpa(), GRADE_TOLERANCE);
    }

    @Test
    public void testCharges() {
        Student student = new Student("a");
        student.addCharge(500);
        student.addCharge(200);
        student.addCharge(399);
        assertEquals(1099, student.totalCharges());
    }

    @Test
    public void testBadlyFormattedName() {
        Logger logger = Logger.getLogger(Student.class.getName());
        Handler handler = new TestHandler();
        logger.addHandler(handler);

        final String studentName = "a b c d";
        try {
            new Student(studentName);
            fail("expected exception from 4-part name");
        }
        catch (StudentNameFormatException expectedException) {
            final String msg = String.format(Student.TOO_MANY_NAME_PARTS_MSG,
                    studentName, Student.MAX_NAME_PARTS);
            assertEquals(msg, expectedException.getMessage());

            assertEquals(msg, ((TestHandler)handler).getMessage());
        }
    }

    @Test
    public void testFlags() {
        Student student = new Student("a");
        student.set(
                Student.Flag.ON_CAMPUS,
                Student.Flag.TAX_EXEMPT,
                Student.Flag.MINOR);
        assertTrue(student.isOn(Student.Flag.ON_CAMPUS));
        assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
        assertTrue(student.isOn(Student.Flag.MINOR));
        assertFalse(student.isOff(Student.Flag.ON_CAMPUS));
        assertTrue(student.isOff(Student.Flag.TROUBLEMAKER));
        student.unset(Student.Flag.ON_CAMPUS);
        assertTrue(student.isOff(Student.Flag.ON_CAMPUS));
        assertTrue(student.isOn(Student.Flag.TAX_EXEMPT));
        assertTrue(student.isOn(Student.Flag.MINOR));
    }
}
