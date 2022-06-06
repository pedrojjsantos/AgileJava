package sis.studentinfo;

import org.junit.Before;
import org.junit.Test;

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
}
