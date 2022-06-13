package sis.studentinfo;

public class Course {
    private final String department;
    private final String number;

    public Course(String department, String number) {
        this.department = department;
        this.number = number;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass())
            return false;

        Course that = (Course) obj;
        return this.department.equals(that.department) &&
               this.number.equals(that.number);
    }

    @Override
    public int hashCode() {
        final int hashMultiplier = 41;
        int result = 7;
        result = result * hashMultiplier + department.hashCode();
        result = result * hashMultiplier + number.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return department + " " + number;
    }

    public String getDepartment() {
        return department;
    }

    public String getNumber() {
        return number;
    }
}
