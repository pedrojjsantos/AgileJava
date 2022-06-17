package sis.studentinfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class Student implements Serializable {
    final static Logger logger =
            Logger.getLogger(Student.class.getName());

    private int settings = 0x0;
    public static final int CREDITS_REQUIRED_FOR_FULL_TIME = 12;
    public static final String IN_STATE = "CO";
    public static final int MAX_NAME_PARTS = 3;
    public static final String TOO_MANY_NAME_PARTS_MSG =
            "Student name '%s' contains more than %d parts";

    private final String fullName;
    private String firstName  = "";
    private String middleName = "";
    private String lastName   = "";

    private String state = "";
    private int credits;
    private final List<Grade> grades = new ArrayList<>();
    private final List<Integer> charges = new ArrayList<>();
    private GradingStrategy gradingStrategy =
            new BasicGradingStrategy();
    private String id;

    public enum Grade {
        A(4),
        B(3),
        C(2),
        D(1),
        F(0);

        private final int points;

        Grade(int points) {
            this.points = points;
        }

        int getPoints() {
            return points;
        }

    }
    public enum Flag {
        ON_CAMPUS   (1 << 0),
        TAX_EXEMPT  (1 << 1),
        MINOR       (1 << 2),
        TROUBLEMAKER(1 << 3);
        private final int mask;

        Flag(int mask) {
            this.mask = mask;
        }

    }
    public Student(String fullName) {
        this.fullName = fullName;
        credits = 0;
        final int maximumNumberOfNameParts = 3;
        List<String> nameParts = split(fullName);
        if (nameParts.size() > maximumNumberOfNameParts) {
            String msg =
                String.format(Student.TOO_MANY_NAME_PARTS_MSG, fullName, MAX_NAME_PARTS);
            Student.logger.info(msg);
            throw new StudentNameFormatException(msg);
        }
        setName(nameParts);
    }

    public void addCharge(int charge) {
        charges.add(charge);
    }

    public int totalCharges() {
        int total = 0;
        for (int charge : charges)
            total += charge;

        return total;
    }

    private List<String> split(String str) {
        return new ArrayList<>(Arrays.asList(str.split(" ")));
    }

    public boolean isFullTime() {
        return credits >= CREDITS_REQUIRED_FOR_FULL_TIME;
    }

    public boolean isInState() {
        return state.equals(Student.IN_STATE);
    }

    public void setGradingStrategy(GradingStrategy strategy) {
        this.gradingStrategy = strategy;
    }

    public void setState(String state) {
        this.state = state.toUpperCase();
    }

    private void setName(List<String> nameParts) {
        this.lastName = pop(nameParts);

        String name = pop(nameParts);
        if (nameParts.isEmpty())
            this.firstName = name;
        else {
            this.middleName = name;
            this.firstName = pop(nameParts);
        }
    }

    private String pop(List<String> list) {
        if (list.isEmpty())
            return "";
        return list.remove(list.size() - 1);
    }

    public void addCredits(int credits) {
        this.credits += credits;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public String getFullName() {
        return fullName;
    }

    public String getFirstName() {
        return firstName;
    }
    public String getMiddleName() {
        return middleName;
    }
    public String getLastName() {
        return lastName;
    }

    public int getCredits() {
        return credits;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }

    public double getGpa() {
        Student.logger.fine("begin getGpa " + System.currentTimeMillis());
        if (grades.isEmpty()) return 0.0;

        double total = 0;

        for (Grade grade : grades) {
            total += gradingStrategy.getGradePointsFor(grade);
        }
        Student.logger.fine("end getGpa " + System.currentTimeMillis());
        return total / grades.size();
    }

    public void set(Flag... flags) {
        for (Flag flag: flags)
            settings |= flag.mask;
    }
    public void unset(Flag... flags) {
        for (Flag flag: flags)
            settings &= ~flag.mask;
    }
    public boolean isOn(Flag flag) {
        return (settings & flag.mask) == flag.mask;
    }
    public boolean isOff(Flag flag) {
        return !isOn(flag);
    }

    public static Student findByLastName(String lastName) {
        return new Student(lastName);
    }
}
