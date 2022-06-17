package sis.studentinfo;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

abstract public class Session implements Comparable<Session>, Iterable<Student>, Serializable {
    private Course course;
    private transient List<Student> students = new ArrayList<>();
    private Date startDate;
    private int numberOfCredits;
    private URL url;

    protected Session(Course course, Date startDate) {
        this.course = course;
        this.startDate = startDate;
    }

    public Iterator<Student> iterator() {
        return students.iterator();
    }
    public int compareTo(Session that) {
        int compare =
                this.getDepartment().compareTo(that.getDepartment());
        if (compare != 0)
            return compare;
        return this.getNumber().compareTo(that.getNumber());
    }
    void setNumberOfCredits(int numberOfCredits) {
        this.numberOfCredits = numberOfCredits;
    }

    public String getDepartment() {
        return course.getDepartment();
    }
    public String getNumber() {
        return course.getNumber();
    }
    public int getNumberOfStudents() {
        return students.size();
    }
    public int getNumberOfCredits() {
        return numberOfCredits;
    }

    public void enroll(Student student) {
        student.addCredits(numberOfCredits);
        students.add(student);
    }
    Student get(int index) {
        return students.get(index);
    }
    protected Date getStartDate() {
        return startDate;
    }
    public List<Student> getAllStudents() {
        return students;
    }

    abstract protected int getSessionLength();
    public Date getEndDate() {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(getStartDate());
        final int daysInWeek = 7;
        final int daysFromFridayToMonday = 3;
        int numberOfDays =
                getSessionLength() * daysInWeek - daysFromFridayToMonday;
        calendar.add(Calendar.DAY_OF_YEAR, numberOfDays);
        return calendar.getTime();
    }

    public double averageGpaForPartTimeStudents() {
        int count = 0;
        double total = 0.0;

        for (Student student : students) {
            if (student.isFullTime())
                continue;
            count++;
            total += student.getGpa();
        }

        return (count > 0) ? total / count : 0.0;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(String urlString) throws SessionException {
        try {
            this.url = new URL(urlString);
        }
        catch (MalformedURLException e) {
            log(e);
            throw new SessionException(e);
        }
    }
    private void log(Exception e) {
        //e.printStackTrace();
    }

    @Serial
    private void writeObject(ObjectOutputStream output) throws IOException {
        output.defaultWriteObject();
        output.writeInt(students.size());
        for (Student student: students)
            output.writeObject(student.getLastName());
    }

    @Serial
    private void readObject(ObjectInputStream input)
            throws Exception {
        input.defaultReadObject();
        students = new ArrayList<>();
        int size = input.readInt();
        for (int i = 0; i < size; i++) {
            String lastName = (String)input.readObject();
            students.add(Student.findByLastName(lastName));
        }
    }
}
